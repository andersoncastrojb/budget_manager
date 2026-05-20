package co.com.soft.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserDashboardDTO {
    private UserDTO user;
    private List<AccountDTO> accounts;
    private List<LoanDTO> loans;
    private List<FixedExpenseDTO> fixedExpenses;
    private List<IncomeDTO> incomes;
}