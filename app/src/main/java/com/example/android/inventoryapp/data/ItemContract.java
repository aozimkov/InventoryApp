package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public class ItemContract {

    private ItemContract(){}

    public static final class ItemEntry implements BaseColumns{

        //table name
        public static final String TABLE_NAME = "items";

        //Columns

        /**
         * Unique item ID number
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * product name - String (TEXT) value contains name of the product
         */
        public static final String COLUMN_PRODUCT_NAME = "product";

        /**
         * price - int (INTEGER) value contains product price x100
         * for example: value for a product with price 9.99 will equals 999
         */
        public static final String COLUMN_PRODUCT_PRICE = "price";

        /**
         * quantity - int (INTEGER) value contains Item quantity
         */
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";

        /**
         * supplier name - String (TEXT) value contains Supplier name
         */
        public static final String COLUMN_PRODUCT_SUPPLIER = "supplier";

        /**
         * supplier phone number - String (TEXT)
         */
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE = "phone";

    }
}
