package com.haidar.budgetplanner.controller;

import com.haidar.budgetplanner.entity.BudgetItem;
import com.haidar.budgetplanner.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String index(Model model,
                        @RequestParam(required = false) Boolean tambahAnggaran,
                        @RequestParam(required = false) Double jumlah) {

        List<BudgetItem> items = budgetService.getAll();
        double totalAnggaran = budgetService.getTotalAnggaran();
        double sisaAnggaran = budgetService.getSisaAnggaran();

        model.addAttribute("items", items);
        model.addAttribute("totalAnggaran", totalAnggaran);
        model.addAttribute("sisaAnggaran", sisaAnggaran);

        if (tambahAnggaran != null && tambahAnggaran && jumlah != null) {
            model.addAttribute("tambahAnggaran", true);
            model.addAttribute("jumlah", jumlah);
        }

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}