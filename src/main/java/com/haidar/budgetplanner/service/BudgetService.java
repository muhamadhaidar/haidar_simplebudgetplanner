package com.haidar.budgetplanner.service;

import com.haidar.budgetplanner.entity.BudgetItem;
import java.util.List;

public interface BudgetService {
    List<BudgetItem> getAll();
    BudgetItem getById(Long id);
    BudgetItem save(BudgetItem item);
    BudgetItem save(String kategori, double jumlah, String keterangan);
    BudgetItem update(Long id, BudgetItem updated);
    void delete(Long id);

    double getTotalAnggaran();
    double getSisaAnggaran();
    void setTotalAnggaran(double nilai);
    void resetAnggaran();
    void hapusSemua();
}