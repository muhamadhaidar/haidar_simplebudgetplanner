package com.haidar.budgetplanner.controller;

import com.haidar.budgetplanner.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/anggaran")
public class AnggaranController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/atur")
    public String formAturAnggaran() {
        return "atur-anggaran";
    }

    @PostMapping("/atur")
    public String tambahAnggaran(@RequestParam("nominal") double nominal) {
        budgetService.setTotalAnggaran(nominal);
        budgetService.save("TAMBAH ANGGARAN", nominal, "Penambahan anggaran");
        return "redirect:/home?tambahAnggaran=true&jumlah=" + nominal;
    }

    @PostMapping("/reset")
    public String resetAnggaran() {
        budgetService.resetAnggaran();
        return "redirect:/home";
    }
}