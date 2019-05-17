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

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", userId=" + userId +
                ", pId=" + pId +
                '}';
    }
}
