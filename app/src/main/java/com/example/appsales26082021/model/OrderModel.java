package com.example.appsales26082021.model;

public class OrderModel {
    String orderId;
    int total;

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId='" + orderId + '\'' +
                ", total=" + total +
                '}';
    }
}
