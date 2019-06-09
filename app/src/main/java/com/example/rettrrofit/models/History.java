package com.example.rettrrofit.models;

import com.google.gson.annotations.SerializedName;

public class History {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public History(int userId, int cartId, int quantity, String productName, int pId) {
        this.userId = userId;
        this.cartId = cartId;
        this.quantity = quantity;
        this.productName = productName;
        this.pId = pId;
    }

    public History() {

    }
}
