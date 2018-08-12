package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.inventoryapp.data.ItemContract.ItemEntry;


public class ItemDbHelper extends SQLiteOpenHelper {

    /**
     * IMPORTANT! If you make any changes in db schema
     * you must increment DATABASE_VERSION
     */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "inventory.db";

    public ItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create database schema
        // Creation SQL:
        // CREATE TABLE `items` (
        //     `_id` INTEGER PRIMARY KEY AUTOINCREMENT,
        //     `product` TEXT,
        //     `price` INTEGER,
        //     `quantity` INTEGER,
        //     `supplier` TEXT,
        //     `phone` TEXT);

        String SQL_CREATE_ENTRIES = "CREATE TABLE `" + ItemEntry.TABLE_NAME + "` (`" +
                ItemEntry._ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `" +
                ItemEntry.COLUMN_PRODUCT_NAME + "` TEXT, `" +
                ItemEntry.COLUMN_PRODUCT_PRICE + "` INTEGER, `" +
                ItemEntry.COLUMN_PRODUCT_QUANTITY + "` INTEGER, `" +
                ItemEntry.COLUMN_PRODUCT_SUPPLIER + "` TEXT, `" +
                ItemEntry.COLUMN_PRODUCT_SUPPLIER_PHONE + "` TEXT);";

        // Exec
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String SQL_DELETE_ENTRIES = "DROP TABLE " + ItemEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}
