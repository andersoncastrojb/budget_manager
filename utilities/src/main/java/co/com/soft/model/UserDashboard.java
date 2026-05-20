package co.com.soft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDashboard {
    private User user;
    private List<Account> accounts;
    private List<Loan> loans;
    private List<FixedExpense> fixedExpenses;
    private List<Income> incomes;
}