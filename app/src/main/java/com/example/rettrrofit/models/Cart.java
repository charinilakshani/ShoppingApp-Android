package com.example.rettrrofit.models;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("userId")
    private  int userId;

    @SerializedName("cartId")
    private int cartId;


    @SerializedName("quantity")
    private int quantity;
    @SerializedName("productName")
    private String productName;

    @SerializedName("pId")
    private int pId;

    @SerializedName("productImage")
    private String productImage;



    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", cartId=" + cartId +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", pId=" + pId +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}
