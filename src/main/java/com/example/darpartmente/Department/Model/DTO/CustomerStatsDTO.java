package com.example.darpartmente.Department.Model.DTO;

public class CustomerStatsDTO {
    private String customerName;
    private double totalAmount; // for top 5
    private long purchaseCount;

    @Override
    public String toString() {
        return "CustomerStatsDTO{" +
                "customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                ", purchaseCount=" + purchaseCount +
                '}';
    }

    public CustomerStatsDTO() {
    }

    public CustomerStatsDTO(String customerName, double totalAmount, long purchaseCount) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.purchaseCount = purchaseCount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(long purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
}
