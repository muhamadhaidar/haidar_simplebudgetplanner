package com.haidar.budgetplanner.service.impl;

import com.haidar.budgetplanner.entity.BudgetItem;
import com.haidar.budgetplanner.exception.ResourceNotFoundException;
import com.haidar.budgetplanner.repository.BudgetItemRepository;
import com.haidar.budgetplanner.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetItemRepository repository;

    private double totalAnggaran = 0.0;

    @Override
    public List<BudgetItem> getAll() {
        return repository.findAll();
    }

    @Override
    public BudgetItem getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item tidak ditemukan: " + id));
    }

    @Override
    public BudgetItem save(BudgetItem item) {
        return repository.save(item);
    }

    @Override
    public BudgetItem save(String kategori, double jumlah, String keterangan) {
        BudgetItem item = new BudgetItem(kategori, jumlah, keterangan);
        return save(item);
    }

    @Override
    public BudgetItem update(Long id, BudgetItem updated) {
        BudgetItem existing = getById(id);
        existing.setKategori(updated.getKategori());
        existing.setJumlah(updated.getJumlah());
        existing.setKeterangan(updated.getKeterangan());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.delete(getById(id));
    }

    @Override
    public void hapusSemua() {
        repository.deleteAll();
    }

    @Override
    public double getTotalAnggaran() {
        return totalAnggaran;
    }

    @Override
    public void setTotalAnggaran(double nilai) {
        this.totalAnggaran += nilai;
    }

    @Override
    public void resetAnggaran() {
        this.totalAnggaran = 0;
    }

    @Override
    public double getSisaAnggaran() {
        double totalPengeluaran = repository.findAll()
                .stream()
                .filter(i -> "pengeluaran".equals(i.getJenis()))
                .mapToDouble(BudgetItem::getJumlah)
                .sum();
        return totalAnggaran - totalPengeluaran;
    }
}
