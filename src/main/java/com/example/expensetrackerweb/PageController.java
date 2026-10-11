package com.example.expensetrackerweb;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model) {
        List<Expense> list = Database.getAllExpenses();
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
                      @RequestParam("amount") double amount) {
        Database.addExpense(name, amount);
        return "redirect:/";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        Database.deleteExpense(id);
        return "redirect:/";
    }
}