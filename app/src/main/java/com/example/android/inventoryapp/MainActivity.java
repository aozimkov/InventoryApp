package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.inventoryapp.data.ItemContract.ItemEntry;
import com.example.android.inventoryapp.data.ItemDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ItemDbHelper mItemDbHelper;
    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemDbHelper = new ItemDbHelper(this);
        insertData();
        queryData();
    }

    /**
     * Main data insert
     */
    private void insertData(){

        List<Item> items = new ArrayList<>();
        items.add(new Item("A Triple Life", 2080, 3,
                "Read and sleep", "+17757575752"));
        items.add(new Item("Moon and growing", 1100, 1,
                "Copiers", "+1234972934"));
        items.add(new Item("Travelers secrets", 4450, 10,
                "United publishers", "+7594405477"));
        items.add(new Item("Development in a kitchen", 11100, 1,
                "Ready books", "+484309547202"));

        SQLiteDatabase database = mItemDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for(int i = 0; i < items.size(); i++){

            Item currentItem = items.get(i);

            contentValues.put(ItemEntry.COLUMN_PRODUCT_NAME,
                    currentItem.getmName());
            contentValues.put(ItemEntry.COLUMN_PRODUCT_PRICE,
                    currentItem.getmPrice());
            contentValues.put(ItemEntry.COLUMN_PRODUCT_QUANTITY,
                    currentItem.getmQuantity());
            contentValues.put(ItemEntry.COLUMN_PRODUCT_SUPPLIER,
                    currentItem.getmSupplierName());
            contentValues.put(ItemEntry.COLUMN_PRODUCT_SUPPLIER_PHONE,
                    currentItem.getmSupplierPhone());

            long newRow = database.insert(ItemEntry.TABLE_NAME, null, contentValues);

            if (newRow != -1){
                Log.v(LOG_TAG, "New row created, ID: " + newRow);
            } else {
                Log.e(LOG_TAG, "Error new row creation !");
            }
        }

        Log.v(LOG_TAG, "Table created successful");

    }

    /**
     * SELECT * FROM items query
     * @return cursor
     */
    private void queryData(){

        //Create or open db
        SQLiteDatabase database = mItemDbHelper.getReadableDatabase();

        //Create Projection *
        String[] projection = {
                ItemEntry._ID,
                ItemEntry.COLUMN_PRODUCT_NAME,
                ItemEntry.COLUMN_PRODUCT_PRICE,
                ItemEntry.COLUMN_PRODUCT_QUANTITY,
                ItemEntry.COLUMN_PRODUCT_SUPPLIER,
                ItemEntry.COLUMN_PRODUCT_SUPPLIER_PHONE
        };

        // Set Cursor
        Cursor cursor = database.query(
                ItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        Log.v(LOG_TAG, "Cursor created");
        Log.v(LOG_TAG, "Start reading from database:");

        Log.v(LOG_TAG, "Item: " +
                ItemEntry._ID + " | " +
                ItemEntry.COLUMN_PRODUCT_NAME + " | " +
                ItemEntry.COLUMN_PRODUCT_PRICE + " | " +
                ItemEntry.COLUMN_PRODUCT_QUANTITY + " | " +
                ItemEntry.COLUMN_PRODUCT_SUPPLIER + " | " +
                ItemEntry.COLUMN_PRODUCT_SUPPLIER_PHONE);

        try {

            int idColumnIndex = cursor.getColumnIndex(ItemEntry._ID);
            int productColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_PRODUCT_SUPPLIER);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_PRODUCT_SUPPLIER_PHONE);

            Log.v(LOG_TAG, "Total items: " + cursor.getCount());

            while(cursor.moveToNext()){
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(productColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

                Log.v(LOG_TAG, "Item: " +
                        currentId + " | " +
                        currentName + " | " +
                        currentPrice + " | " +
                        currentQuantity + " | " +
                        currentSupplier + " | " +
                        currentSupplierPhone);
            }

        } finally {
            cursor.close();
            Log.v(LOG_TAG, "Cursor has been closed");
        }
    }

}
