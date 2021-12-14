package com.example.appsales26082021.model;

import java.io.Serializable;

public class OrderModel implements Serializable {
    public String orderId;
    public int total;

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId='" + orderId + '\'' +
                ", total=" + total +
                '}';
    }
}
