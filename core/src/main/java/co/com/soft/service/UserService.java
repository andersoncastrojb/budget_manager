package co.com.soft.service;

import co.com.soft.model.*;
import co.com.soft.usecase.*;
import co.com.soft.adapters.UserAdapter;
import co.com.soft.entity.UserEntity;
import co.com.soft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class UserService implements UserUseCase {
    private final UserRepository userRepository;

    private final AccountUseCase accountUseCase;
    private final LoanUseCase loanUseCase;
    private final FixedExpenseUseCase fixedExpenseUseCase;
    private final IncomeUseCase incomeUseCase;

    @Autowired
    public UserService(UserRepository userRepository, AccountUseCase accountUseCase,
                       LoanUseCase loanUseCase, FixedExpenseUseCase fixedExpenseUseCase,
                       IncomeUseCase incomeUseCase) {
        this.userRepository = userRepository;
        this.accountUseCase = accountUseCase;
        this.loanUseCase = loanUseCase;
        this.fixedExpenseUseCase = fixedExpenseUseCase;
        this.incomeUseCase = incomeUseCase;
    }

    @Override
    public Mono<User> createUser(User user) {
        UserEntity entity = UserAdapter.toEntity(user);
        return userRepository.save(entity)
                .map(UserAdapter::toModel);
    }

    @Override
    public Mono<User> updateUser(Long id, User user) {
        return userRepository.findById(id)
                .flatMap(existing -> {
                    UserEntity updated = UserAdapter.toEntity(user);
                    updated.setId(id);
                    return userRepository.save(updated);
                })
                .map(UserAdapter::toModel);
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserAdapter::toModel);
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll()
                .map(UserAdapter::toModel);
    }

    @Override
    public Mono<UserDashboard> getUserDashboard(Long id) {
        Mono<User> userMono = getUserById(id);
        Mono<List<Account>> accountsMono = accountUseCase.getAccountsByUserId(id).collectList();
        Mono<List<Loan>> loansMono = loanUseCase.getLoansByUserId(id).collectList();
        Mono<List<FixedExpense>> expensesMono = fixedExpenseUseCase.getFixedExpensesByUserId(id).collectList();
        Mono<List<Income>> incomesMono = incomeUseCase.getIncomesByUserId(id).collectList();

        return Mono.zip(userMono, accountsMono, loansMono, expensesMono, incomesMono)
                .map(tuple -> new UserDashboard(
                        tuple.getT1(),
                        tuple.getT2(),
                        tuple.getT3(),
                        tuple.getT4(),
                        tuple.getT5()
                ));
    }
}
