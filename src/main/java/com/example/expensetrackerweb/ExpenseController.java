package com.example.expensetrackerweb;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @GetMapping("/expenses")
    public List<Expense> expenses() {
        return Database.getAllExpenses();
    }
}