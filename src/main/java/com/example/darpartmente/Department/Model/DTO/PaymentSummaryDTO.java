package com.example.darpartmente.Department.Model.DTO;

import java.sql.Date;

public class PaymentSummaryDTO {

    private String itemName;
    private String customerName;
    private Date monthEndDate;
    private double totalTillLastMonth;
    private double currentMonthPayment;
    private double totalTillCurrentMonth;

    public PaymentSummaryDTO() {}

    public PaymentSummaryDTO(String itemName, String customerName, Date monthEndDate,
                             double totalTillLastMonth, double currentMonthPayment, double totalTillCurrentMonth) {
        this.itemName = itemName;
        this.customerName = customerName;
        this.monthEndDate = monthEndDate;
        this.totalTillLastMonth = totalTillLastMonth;
        this.currentMonthPayment = currentMonthPayment;
        this.totalTillCurrentMonth = totalTillCurrentMonth;
    }

    // Getters and setters...

    @Override
    public String toString() {
        return "PaymentSummaryDTO{" +
                "itemName='" + itemName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", monthEndDate=" + monthEndDate +
                ", totalTillLastMonth=" + totalTillLastMonth +
                ", currentMonthPayment=" + currentMonthPayment +
                ", totalTillCurrentMonth=" + totalTillCurrentMonth +
                '}';
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getMonthEndDate() {
        return monthEndDate;
    }

    public void setMonthEndDate(Date monthEndDate) {
        this.monthEndDate = monthEndDate;
    }

    public double getTotalTillLastMonth() {
        return totalTillLastMonth;
    }

    public void setTotalTillLastMonth(double totalTillLastMonth) {
        this.totalTillLastMonth = totalTillLastMonth;
    }

    public double getCurrentMonthPayment() {
        return currentMonthPayment;
    }

    public void setCurrentMonthPayment(double currentMonthPayment) {
        this.currentMonthPayment = currentMonthPayment;
    }

    public double getTotalTillCurrentMonth() {
        return totalTillCurrentMonth;
    }

    public void setTotalTillCurrentMonth(double totalTillCurrentMonth) {
        this.totalTillCurrentMonth = totalTillCurrentMonth;
    }
}
