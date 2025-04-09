package com.example.darpartmente.Department.repositery;

import com.example.darpartmente.Department.Model.DTO.PaymentSummaryDTO;
import com.example.darpartmente.Department.Model.SaleDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long> {
    // In SaleDetailsRepository
    List<SaleDetails> findByProduct_NameContainingIgnoreCase(String itemName);
    List<SaleDetails> findByCustNameContainingIgnoreCase(String custName);
    List<SaleDetails> findByMobileNoContaining(String mobileNo);
    List<SaleDetails> findByPayAmountGreaterThanEqual(Double amount);

    @Query("SELECT s FROM SaleDetails s WHERE " +
            "(:itemName IS NULL OR LOWER(s.product.name) LIKE LOWER(CONCAT('%', :itemName, '%'))) AND " +
            "(:custName IS NULL OR LOWER(s.custName) LIKE LOWER(CONCAT('%', :custName, '%'))) AND " +
            "(:mobileNo IS NULL OR s.mobileNo LIKE CONCAT('%', :mobileNo, '%')) AND " +
            "(:amount IS NULL OR s.payAmount = :amount)")
    Page<SaleDetails> customSearch(@Param("itemName") String itemName,
                                   @Param("custName") String custName,
                                   @Param("mobileNo") String mobileNo,
                                   @Param("amount") Double amount,
                                   Pageable pageable);


    @Query("""
    SELECT new com.example.darpartmente.Department.Model.DTO.PaymentSummaryDTO(
    sd.product.name,
    sd.custName,
    LAST_DAY(CURRENT_DATE),
    SUM(CASE WHEN MONTH(sd.shopDate) < MONTH(CURRENT_DATE) OR YEAR(sd.shopDate) < YEAR(CURRENT_DATE) THEN sd.payAmount ELSE 0 END),
    SUM(CASE WHEN MONTH(sd.shopDate) = MONTH(CURRENT_DATE) AND YEAR(sd.shopDate) = YEAR(CURRENT_DATE) THEN sd.payAmount ELSE 0 END),
    SUM(sd.payAmount))
    FROM SaleDetails sd
    GROUP BY sd.product.name, sd.custName """)
    List<PaymentSummaryDTO> getItemCustomerMonthlyPayments();

    @Query("""
    SELECT sd.custName, SUM(sd.payAmount) as totalAmount
    FROM SaleDetails sd
    GROUP BY sd.custName
    ORDER BY totalAmount DESC
    LIMIT 5 """)
    List<Object[]> findTop5CustomersByTotalPayment();

    @Query("""
    SELECT sd.custName, COUNT(sd.id) as purchaseCount
    FROM SaleDetails sd
    GROUP BY sd.custName
    ORDER BY purchaseCount DESC
    LIMIT 10 """)
    List<Object[]> findTop10CustomersByPurchaseCount();







}

