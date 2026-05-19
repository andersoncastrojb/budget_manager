# Budget Manager

A reactive personal finance management system built with **Java 21** and **Spring Boot 4.0 (Snapshot)**. This application allows users to track their accounts, incomes, loans, and recurring expenses, providing a comprehensive monthly financial balance.

## 🚀 Purpose

The **Budget Manager** project is designed to help individuals take control of their financial health. It provides a centralized platform to:
*   Manage multiple financial **Accounts**.
*   Track **Incomes** and associate them with specific accounts.
*   Monitor **Loans** and their repayment status.
*   Register **Fixed Expenses** (recurring costs like rent or subscriptions).
*   Generate automated **Monthly Balances** that aggregate all financial activities and calculate the final standing.

---

## 🛠️ Technical Details

### Architecture
The project follows a **Multi-Module Gradle** structure, inspired by Clean Architecture principles to separate concerns and improve maintainability:

1.  **`configuration`**: The main application entry point. Handles Spring Boot configuration and dependency injection.
2.  **`entrypoints`**: The interface layer. Contains REST Controllers, DTOs, and Mappers to transform data between the API and the core logic.
3.  **`core`**: The business logic layer. Contains Use Cases and Services that implement the core financial rules.
4.  **`infrastructure`**: The data access layer. Implements R2DBC repositories, database entities, and adapters for persistence.
5.  **`utilities`**: Shared domain models and enums used across all modules.

### Tech Stack
*   **Language**: Java 21 (utilizing modern features).
*   **Framework**: Spring Boot 4.0.0-SNAPSHOT.
*   **Programming Model**: Reactive Programming (Project Reactor).
*   **Web Layer**: Spring WebFlux.
*   **Persistence**: Spring Data R2DBC with MySQL (JAsync driver).
*   **Documentation**: SpringDoc OpenAPI (Swagger UI).
*   **Utility**: Project Lombok for reducing boilerplate.

---

## 💼 Business Logic & Features

### Core Entities
*   **User**: The owner of the financial data.
*   **Account**: Represents a physical or digital place where money is kept (e.g., Savings Account, Cash, Credit Card).
*   **Income**: Record of money received. Each income is linked to an account.
*   **Fixed Expense**: Recurring payments that the user must pay periodically.
*   **Loan**: Tracking of borrowed money, lender details, and due dates.
*   **Monthly Balance**: A snapshot of a user's financial status for a specific month.

### Monthly Balance Calculation
The system features an automated balance processing engine (`ProcessBalance`) that:
1.  Filters all incomes, expenses, and loans within a specific month.
2.  Calculates total incomes and adds them to the respective accounts.
3.  Aggregates total deductions (Fixed Expenses + Loans).
4.  **Proportional Deduction**: Distributes the total deductions across all accounts based on their current balance proportion. This ensures that accounts with more funds contribute more to the expenses.
5.  Persists the final calculation for historical tracking.

---

## 🚦 Getting Started

### Prerequisites
*   JDK 21
*   MySQL Database (or Docker)

### Database Setup
The schema is defined in `infrastructure/src/main/resources/schema.sql`. Ensure you have a database named `budget_manager` and the tables are created.

### Configuration
Update `configuration/src/main/resources/application.yml` with your database credentials:

```yaml
spring:
  r2dbc:
    url: "r2dbc:mysql://[user]:[password]@[host]:3306/budget_manager"
```

### Running the Application
Run the root Gradle task:
```bash
./gradlew bootRun
```

Access the API documentation at:
`http://localhost:8080/swagger-ui.html`
