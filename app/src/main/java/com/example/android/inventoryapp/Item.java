package com.example.android.inventoryapp;

public class Item {

    private String mName, mSupplierName, mSupplierPhone;
    private int mPrice, mQuantity;

    public Item(String name, int price, int quantity, String supplierName, String supplierPhone){
        mName = name;
        mPrice = price;
        mQuantity = quantity;
        mSupplierName = supplierName;
        mSupplierPhone = supplierPhone;
    }

    public String getmName() {
        return mName;
    }

    public String getmSupplierName() {
        return mSupplierName;
    }

    public String getmSupplierPhone() {
        return mSupplierPhone;
    }

    public int getmPrice() {
        return mPrice;
    }

    public int getmQuantity() {
        return mQuantity;
    }
}
