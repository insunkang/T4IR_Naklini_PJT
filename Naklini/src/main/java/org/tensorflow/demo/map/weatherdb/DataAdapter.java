package org.tensorflow.demo.map.weatherdb;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter {
    protected static final String TAG = "DataAdapter";

    // table 이름을 명시해야함
    //protected static final String TABLE_NAME = "Onboard";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public List getTableData() {
        try {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + "location";

            // 모델 넣을 리스트 생성
            List locationlist = new ArrayList();

            // TODO : 모델 선언
            location location = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    location = new location();

                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    location.setSi(mCur.getString(2));
                    location.setGu(mCur.getString(3));
                    location.setDong(mCur.getString(4));
                    location.setX(mCur.getString(5));
                    location.setY(mCur.getString(6));

                    // 리스트에 넣기
                    locationlist.add(location);
                }

            }
            return locationlist;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

}