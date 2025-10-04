package co.com.soft.service;

import co.com.soft.adapters.AccountAdapter;
import co.com.soft.adapters.FixedExpenseAdapter;
import co.com.soft.adapters.IncomeAdapter;
import co.com.soft.adapters.LoanAdapter;
import co.com.soft.adapters.MonthlyBalanceAdapter;
import co.com.soft.model.Account;
import co.com.soft.model.FixedExpense;
import co.com.soft.model.Income;
import co.com.soft.model.Loan;
import co.com.soft.model.MonthlyBalance;
import co.com.soft.entity.MonthlyBalanceEntity;
import co.com.soft.repository.AccountRepository;
import co.com.soft.repository.IncomeRepository;
import co.com.soft.repository.LoanRepository;
import co.com.soft.repository.FixedExpenseRepository;
import co.com.soft.repository.MonthlyBalanceRepository;
import co.com.soft.usecase.MonthlyBalanceUseCase;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ProcessBalance implements MonthlyBalanceUseCase {
    private final AccountRepository accountRepository;
    private final IncomeRepository incomeRepository;
    private final LoanRepository loanRepository;
    private final FixedExpenseRepository fixedExpenseRepository;
    private final MonthlyBalanceRepository monthlyBalanceRepository;

    @Autowired
    public ProcessBalance(AccountRepository accountRepository,
                          IncomeRepository incomeRepository,
                          LoanRepository loanRepository,
                          FixedExpenseRepository fixedExpenseRepository,
                          MonthlyBalanceRepository monthlyBalanceRepository) {
        this.accountRepository = accountRepository;
        this.incomeRepository = incomeRepository;
        this.loanRepository = loanRepository;
        this.fixedExpenseRepository = fixedExpenseRepository;
        this.monthlyBalanceRepository = monthlyBalanceRepository;
    }

    @Override
    public Mono<MonthlyBalance> processMonthlyBalance(Long userId, Integer month, Integer year) {
        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime end = start.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);

        Mono<List<Account>> accountsMono = accountRepository.findAllByUserId(userId)
                .map(AccountAdapter::toModel)
                .collectList();

        Mono<List<Income>> incomesMono = accountRepository.findAllByUserId(userId)
                .flatMap(acc -> incomeRepository.findAllByAccountId(acc.getId())
                        .map(IncomeAdapter::toModel)
                        .filter(in -> in.getDate() != null && !in.getDate().isBefore(start) && !in.getDate().isAfter(end))

                )
                .collectList();

        Mono<List<Loan>> loansMono = loanRepository.findAllByUserId(userId)
                .map(LoanAdapter::toModel)
                .filter(l -> l.getLoanDate() != null && !l.getLoanDate().isBefore(start) && !l.getLoanDate().isAfter(end))
                .collectList();

        Mono<List<FixedExpense>> fixedExpensesMono = fixedExpenseRepository.findAllByUserId(userId)
                .map(FixedExpenseAdapter::toModel)
                .filter(fe -> {
                    LocalDateTime s = fe.getStartDate();
                    LocalDateTime e = fe.getEndDate();
                    if (s != null && s.isAfter(end)) return false;
                    if (e != null && e.isBefore(start)) return false;
                    return true;
                })
                .collectList();

        return Mono.zip(accountsMono, incomesMono, loansMono, fixedExpensesMono)
                .flatMap(tuple -> {
                    List<Account> accounts = tuple.getT1();
                    List<Income> incomes = tuple.getT2();
                    List<Loan> loans = tuple.getT3();
                    List<FixedExpense> fixedExpenses = tuple.getT4();

                    long totalIncomes = incomes.stream().mapToLong(i -> i.getValue() == null ? 0L : i.getValue()).sum();
                    long totalLoans = loans.stream().mapToLong(l -> l.getAmount() == null ? 0L : l.getAmount()).sum();
                    long totalExpenses = fixedExpenses.stream().mapToLong(f -> f.getAmount() == null ? 0L : f.getAmount()).sum();

                    for (Account acc : accounts) {
                        long accIncomes = incomes.stream().filter(i -> i.getIdAccount() != null && i.getIdAccount().equals(acc.getId()))
                                .mapToLong(i -> i.getValue() == null ? 0L : i.getValue()).sum();
                        Long bal = acc.getBalance() == null ? 0L : acc.getBalance();
                        acc.setBalance(bal + accIncomes);
                    }

                    long sumAccounts = accounts.stream().mapToLong(a -> a.getBalance() == null ? 0L : a.getBalance()).sum();
                    long totalDeductions = totalLoans + totalExpenses;

                    // distribute deductions proportionally across accounts
                    if (totalDeductions > 0 && sumAccounts > 0) {
                        for (Account acc : accounts) {
                            long accBal = acc.getBalance() == null ? 0L : acc.getBalance();
                            double proportion = (double) accBal / (double) sumAccounts;
                            long ded = Math.round(proportion * totalDeductions);
                            acc.setBalance(accBal - ded);
                        }
                    } else if (totalDeductions > 0 && sumAccounts == 0) {
                        // if no balances, deduct evenly
                        int n = Math.max(1, accounts.size());
                        long per = totalDeductions / n;
                        for (Account acc : accounts) {
                            Long bal = acc.getBalance() == null ? 0L : acc.getBalance();
                            acc.setBalance(bal - per);
                        }
                    }

                    long finalBalance = accounts.stream().mapToLong(a -> a.getBalance() == null ? 0L : a.getBalance()).sum();

                    MonthlyBalance mb = new MonthlyBalance(null, userId, month, year, totalIncomes, totalExpenses, totalLoans, finalBalance);

                    return monthlyBalanceRepository.findByUserIdAndMonthAndYear(userId, month, year)
                            .flatMap(existing -> {
                                MonthlyBalanceEntity updated = MonthlyBalanceAdapter.toEntity(mb);
                                updated.setId(existing.getId());
                                return monthlyBalanceRepository.save(updated)
                                        .map(MonthlyBalanceAdapter::toModel);
                            })
                            .switchIfEmpty(Mono.defer(() -> {
                                MonthlyBalanceEntity entity = MonthlyBalanceAdapter.toEntity(mb);
                                return monthlyBalanceRepository.save(entity)
                                        .map(MonthlyBalanceAdapter::toModel);
                            }));

                });
    }
}
