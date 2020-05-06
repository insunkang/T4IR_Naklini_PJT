package org.tensorflow.demo.map.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.tensorflow.demo.Yolo.YoloDataBaseHelper;
import org.tensorflow.demo.pointdetail.Fish_Detail_InfoList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter {
    protected static final String TAG = "DataAdapter";

    // table 이름을 명시해야함
    //protected static final String TABLE_NAME = "Onboard";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private YoloDataBaseHelper mDbHelper;

    public DataAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new YoloDataBaseHelper(mContext);
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
            String sql ="SELECT * FROM " + "Onboard";

            // 모델 넣을 리스트 생성
            List onboardlist = new ArrayList();

            // TODO : 모델 선언
            Onboard onboard = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    onboard = new Onboard();

                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    onboard.setName(mCur.getString(1));
                    onboard.setPoint_nm(mCur.getString(2));
                    onboard.setLatitude(mCur.getString(7));
                    onboard.setLongitude(mCur.getString(8));

                    // 리스트에 넣기
                    onboardlist.add(onboard);
                }

            }
            return onboardlist;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List FreshWaterData() {
        try {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + "Freshwater";

            // 모델 넣을 리스트 생성
            List freshlist = new ArrayList();

            // TODO : 모델 선언
            Freshwater freshwater = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    freshwater = new Freshwater();

                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    freshwater.setName(mCur.getString(1));
                    freshwater.setAddress(mCur.getString(2));
                    freshwater.setFish(mCur.getString(3));

                    // 리스트에 넣기
                    freshlist.add(freshwater);
                }

            }
            return freshlist;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List getRockData() {
        try {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + "Rock";

            // 모델 넣을 리스트 생성
            List rocklist = new ArrayList();

            // TODO : 모델 선언
            Rock rock = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    rock = new Rock();

                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    rock.setName(mCur.getString(1));
                    rock.setPoint_nm(mCur.getString(2));
                    rock.setLatitude(mCur.getString(7));
                    rock.setLongitude(mCur.getString(8));

                    // 리스트에 넣기
                    rocklist.add(rock);
                }

            }
            return rocklist;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public List SeaData() {
        try {
            // Table 이름 -> antpool_bitcoin 불러오기
            String sql ="SELECT * FROM " + "Sea";

            // 모델 넣을 리스트 생성
            List sealist = new ArrayList();

            // TODO : 모델 선언
            Sea sea = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    sea = new Sea();

                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    sea.setName(mCur.getString(1));
                    sea.setAddress(mCur.getString(2));
                    sea.setFish(mCur.getString(3));

                    // 리스트에 넣기
                    sealist.add(sea);
                }

            }
            return sealist;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public List YoloResultView(String afterName) {
        try {

            // Table 이름 -> antpool_bitcoin 불러오기
            String sql = "select * from SPECIES where field2 ="+"'"+afterName+"'";

            // 모델 넣을 리스트 생성
            List freshlist = new ArrayList();

            // TODO : 모델 선언
            Fish_Detail_InfoList freshwater = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    // TODO : 커스텀 모델 생성
                    freshwater = new Fish_Detail_InfoList();

                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment
                    freshwater.setFish_name(mCur.getString(1));
                    freshwater.setDistribution(mCur.getString(2));
                    freshwater.setHabitat(mCur.getString(3));

                    // 리스트에 넣기
                    freshlist.add(freshwater);
                }

            }
            return freshlist;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }


}