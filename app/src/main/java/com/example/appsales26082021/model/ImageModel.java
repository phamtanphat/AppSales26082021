package com.example.appsales26082021.model;

import java.io.Serializable;

public class ImageModel implements Serializable {
    public String imageUrl;

    @Override
    public String toString() {
        return "ImageModel{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}