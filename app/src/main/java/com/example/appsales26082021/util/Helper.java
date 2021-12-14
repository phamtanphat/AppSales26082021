package com.example.appsales26082021.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Helper {
    public static String formatPrice(int totalPrice){
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(totalPrice);
    }
}
