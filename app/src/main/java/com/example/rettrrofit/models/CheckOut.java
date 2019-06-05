package com.example.rettrrofit.models;

import com.google.gson.annotations.SerializedName;

public class CheckOut {

    @SerializedName("cartId")
    private int cartId;

    @SerializedName("quantity")
    private int quantity;
    @SerializedName("userId")
    private  int userId;
    @SerializedName("pId")
    private int pId;

    @SerializedName("productName")
    private  String productName;

    @SerializedName("price")
    private  int price;



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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CheckOut{" +
                "cartId=" + cartId +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", pId=" + pId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }

    public CheckOut(int cartId, int quantity, int userId, int pId, String productName, int price) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.userId = userId;
        this.pId = pId;
        this.productName = productName;
        this.price = price;
    }

    public CheckOut() {

    }
}
