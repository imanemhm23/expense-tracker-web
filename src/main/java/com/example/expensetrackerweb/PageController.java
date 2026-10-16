package com.example.expensetrackerweb;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private int currentUserId(Principal principal) {
        return Database.findUser(principal.getName()).id();
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        List<Expense> list = Database.getExpenses(currentUserId(principal));

        double total = 0;
        for (Expense e : list) {
            total = total + e.getAmount();
        }

        model.addAttribute("expenses", list);
        model.addAttribute("total", total);

        return "index";
    }

        @PostMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("amount") double amount,
                      Principal principal) {
        Database.addExpense(name, amount, currentUserId(principal));
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Principal principal) {
        Database.deleteExpense(id, currentUserId(principal));
        return "redirect:/";
    }
}