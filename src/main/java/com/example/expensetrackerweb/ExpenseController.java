package com.example.expensetrackerweb;

import java.security.Principal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @GetMapping("/expenses")
    public List<Expense> expenses(Principal principal) {
        int userId = Database.findUser(principal.getName()).id();
        return Database.getExpenses(userId);
    }
}