package com.example.darpartmente.Department.Service.InterImp;

import com.example.darpartmente.Department.Model.DTO.CustomerStatsDTO;
import com.example.darpartmente.Department.Model.DTO.PaymentSummaryDTO;
import com.example.darpartmente.Department.Model.Product;
import com.example.darpartmente.Department.Model.SaleDetails;
import com.example.darpartmente.Department.repositery.ProductRepository;
import com.example.darpartmente.Department.repositery.SaleDetailsRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional
public class SaleDetailsServiceImp {
    @Autowired
    private SaleDetailsRepository repository;

    @Autowired
    private ProductRepository itemRepository;

    @Autowired
    private SaleDetailsRepository saleDetailsRepository;

    public SaleDetails calculateAndSave(SaleDetails saleDetails) {
        // Check age
        int age = Period.between(saleDetails.getDob(), LocalDate.now()).getYears();
        saleDetails.setMinor(age < 18);

        // Get item price
        Product item = itemRepository.findById(saleDetails.getProduct().getId()).orElseThrow();
        saleDetails.setPrice(item.getPrice());
        double amount = item.getPrice() * saleDetails.getQuantity();

        // Apply discount for Maharashtra
        if ("Maharashtra".equalsIgnoreCase(saleDetails.getState())) {
            amount *= 0.8;
        }

        // Restrict if minor
//        if (saleDetails.isMinor() && amount > 1000) {
//            throw new IllegalArgumentException("Minors can shop up to ₹1000 only.");
//        }

        saleDetails.setPayAmount(amount);
        return repository.save(saleDetails);
    }

    public void cancelSale(Long id) {
        repository.deleteById(id);
    }

    public SaleDetails getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    public Page<SaleDetails> searchSales(String itemName, String custName, String mobileNo, Double amount, Pageable pageable) {
        return saleDetailsRepository.customSearch(itemName, custName, mobileNo, amount, pageable);
    }

    public List<PaymentSummaryDTO> getItemCustomerPaymentSummary() {
        return saleDetailsRepository.getItemCustomerMonthlyPayments();
    }


    public List<CustomerStatsDTO> getTop5ByPayment() {
        return repository.findTop5CustomersByTotalPayment()
                .stream()
                .map(obj -> new CustomerStatsDTO((String) obj[0], (Double) obj[1], 0))
                .toList();
    }

    public List<CustomerStatsDTO> getTop10ByPurchases() {
        return repository.findTop10CustomersByPurchaseCount()
                .stream()
                .map(obj -> new CustomerStatsDTO((String) obj[0], 0, (Long) obj[1]))
                .toList();
    }





}

