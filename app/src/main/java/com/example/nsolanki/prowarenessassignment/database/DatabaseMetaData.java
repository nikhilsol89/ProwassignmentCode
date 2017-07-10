package com.example.nsolanki.prowarenessassignment.database;

/**
 * Created by nsolanki on 7/10/2017.
 */

public class DatabaseMetaData {
    // Database Version
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "prowareness.db";


    public static class CONTACTS_LIST {
        public static final String TABLE_NAME = "CONTACTS";
        public static final String CONTACT_NAME = "NAME";
        public static final String CONTACT_UID = "UID";
        public static final String CONTACT_ISDELETED = "ISDELETED";
        public static final String[] COLUMNS = {CONTACT_NAME, CONTACT_UID, CONTACT_ISDELETED};
        public static final String TABLE_CREATING_QUERY = "CREATE TABLE " + TABLE_NAME + " ( " + CONTACT_NAME + " TEXT, " + CONTACT_UID + " INTEGER PRIMARY KEY UNIQUE," + CONTACT_ISDELETED + " INTEGER" + " )";
    }

}
