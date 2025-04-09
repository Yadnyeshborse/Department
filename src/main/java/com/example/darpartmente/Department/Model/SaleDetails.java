package com.example.darpartmente.Department.Model;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "Sale_Details_NetwWin")
public class SaleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;
    private String custName;
    private String mobileNo;
    private String address;
    private String state;
    private LocalDate dob;
    private boolean minor;
    private int quantity;
    private double price;
    private double payAmount;
    private String emailId;
    private LocalDate shopDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;



    @Override
    public String toString() {
        return "SaleDetails{" +
                "item_id=" + item_id +
                ", product=" + product +
                ", custName='" + custName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", dob=" + dob +
                ", minor=" + minor +
                ", quantity=" + quantity +
                ", price=" + price +
                ", payAmount=" + payAmount +
                ", emailId='" + emailId + '\'' +
                ", shopDate=" + shopDate +
                '}';
    }

    public SaleDetails() {
    }

    public SaleDetails(Long item_id, Product product, String custName, String mobileNo, String address, String state, LocalDate dob, boolean minor, int quantity, double price, double payAmount, String emailId, LocalDate shopDate) {
        this.item_id = item_id;
        this.product = product;
        this.custName = custName;
        this.mobileNo = mobileNo;
        this.address = address;
        this.state = state;
        this.dob = dob;
        this.minor = minor;
        this.quantity = quantity;
        this.price = price;
        this.payAmount = payAmount;
        this.emailId = emailId;
        this.shopDate = shopDate;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isMinor() {
        return minor;
    }

    public void setMinor(boolean minor) {
        this.minor = minor;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getShopDate() {
        return shopDate;
    }

    public void setShopDate(LocalDate shopDate) {
        this.shopDate = shopDate;
    }
}

