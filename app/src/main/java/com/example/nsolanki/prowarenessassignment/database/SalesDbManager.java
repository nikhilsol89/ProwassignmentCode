package com.example.nsolanki.prowarenessassignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nsolanki.prowarenessassignment.interfaces.UpdateListener;
import com.example.nsolanki.prowarenessassignment.model.ContactDetailsModel;

import java.util.ArrayList;
import java.util.List;


public class SalesDbManager {
    private static final String TAG = SalesDbManager.class.getSimpleName();
    private static SalesDbManager instance;
    private MySqliteDBHelper salesDbHelper;
    private SQLiteDatabase database;
    private Context context;

    public SalesDbManager(Context context) {
        this.context = context;
    }

    public SalesDbManager() {
    }

    public static SalesDbManager getInstance(Context context) {
        if (instance == null) {
            instance = new SalesDbManager(context);
        }
        return instance;
    }

    public void openWritableDB() {
        salesDbHelper = MySqliteDBHelper.getInstance(context);
        database = salesDbHelper.getWritableDatabase();
    }

    public void openReadableDB() {
        salesDbHelper = MySqliteDBHelper.getInstance(context);
        database = salesDbHelper.getReadableDatabase();
    }

    public void closeDB() {
        salesDbHelper.close();
        database.close();
    }

    public List<ContactDetailsModel> getContactDetailsModelList() {
        List<ContactDetailsModel> contactDetailsModels = null;
        openReadableDB();
        if (database == null) {
            Log.e(TAG, "Database is not craeted or null");
            return null;
        }
        Cursor cursor = null;
        try {
            String whereClause = DatabaseMetaData.CONTACTS_LIST.CONTACT_ISDELETED + "=" + 0;
            cursor = database.query(true, DatabaseMetaData.CONTACTS_LIST.TABLE_NAME, DatabaseMetaData.CONTACTS_LIST.COLUMNS, whereClause, null, null, null, null, null);
            Log.e(TAG, "Cursor count : " + cursor.getCount());
            contactDetailsModels = getContactDetailsFromCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return contactDetailsModels;
    }

    private List<ContactDetailsModel> getContactDetailsFromCursor(Cursor cursor) {
        List<ContactDetailsModel> detailsModelList = new ArrayList<>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    ContactDetailsModel detailsModel = new ContactDetailsModel();
                    detailsModel.setName(cursor.getString(cursor.getColumnIndex(DatabaseMetaData.CONTACTS_LIST.CONTACT_NAME)));
                    detailsModel.setUid(cursor.getInt(cursor.getColumnIndex(DatabaseMetaData.CONTACTS_LIST.CONTACT_UID)));
                    detailsModel.setIsDeleted(cursor.getInt(cursor.getColumnIndex(DatabaseMetaData.CONTACTS_LIST.CONTACT_ISDELETED)));
                    detailsModelList.add(detailsModel);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return detailsModelList;
    }

    public boolean insertContactsList(List<ContactDetailsModel> contactDetailsModelsList) {
        openWritableDB();
        long count = 0;
        try {
            if (database == null) {
                Log.e(TAG, "insertAvatarList database is null");
                count = 0;
            }
            for (ContactDetailsModel model : contactDetailsModelsList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseMetaData.CONTACTS_LIST.CONTACT_NAME, model.getName());
                contentValues.put(DatabaseMetaData.CONTACTS_LIST.CONTACT_UID, model.getUid());
                contentValues.put(DatabaseMetaData.CONTACTS_LIST.CONTACT_ISDELETED, model.getIsDeleted());
                count = database.insert(DatabaseMetaData.CONTACTS_LIST.TABLE_NAME, null, contentValues);
                Log.e(TAG, "" + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateContactList(ContactDetailsModel detailsModel, UpdateListener listener) {
        boolean updateStatus = false;
        try {
            if (database == null) {
                Log.e(TAG, "Inside the db is null");
                return false;
            }
            Log.e(TAG, "Inside" + detailsModel.getUid());
            String whereClause = DatabaseMetaData.CONTACTS_LIST.CONTACT_UID + "=" + detailsModel.getUid();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseMetaData.CONTACTS_LIST.CONTACT_ISDELETED, 1);
            int count = database.update(DatabaseMetaData.CONTACTS_LIST.TABLE_NAME, contentValues, whereClause, null);
            if (count >= 1) updateStatus = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e(TAG, "Inside" + updateStatus);
        listener.updateListener(updateStatus);
        return updateStatus;
    }

    public long getCount() {
        openReadableDB();
        long count = 0;
        try {
            if (database == null) {
                Log.e(TAG, "insertAvatarList database is null");
                count = 0;
            }
            Cursor cursor = database.query(true, DatabaseMetaData.CONTACTS_LIST.TABLE_NAME, DatabaseMetaData.CONTACTS_LIST.COLUMNS, null, null, null, null, null, null);
            count = cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}

