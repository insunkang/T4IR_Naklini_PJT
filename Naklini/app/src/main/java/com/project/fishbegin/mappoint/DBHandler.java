package com.project.fishbegin.mappoint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

// DAO
public class DBHandler {
    static ExamDBHelper dbHelper;
    static SQLiteDatabase db;

    public static DBHandler open(Context context) {
        // 1. DBhelper 생성
        dbHelper = new ExamDBHelper(context);
        // 2. SQLiteDatabase객체 생성
        db = dbHelper.getWritableDatabase();
        return new DBHandler();
    }


    // TODO: ============================================================================
    // TODO: 시도 리스트
    public ArrayList<SIDO> SIDOList(){
        ArrayList<SIDO> sidoList = new ArrayList<SIDO>();
        SIDO sido = null;

        Cursor cursor = db.query("SIDO", null, null,
                null, null, null, null);
        int count = cursor.getCount(); //레코드 개수를 반환
        Log.d("시도lsit", "조회된 row: " + count);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String SIDO = cursor.getString(1);

            sido = new SIDO(_id, SIDO);
            sidoList.add(sido);
        }
        return sidoList;
    }

    //  TODO:시군구 리스트
    public ArrayList<SIGUNGU> SIGUNGUList(int SIDO_id){
        ArrayList<SIGUNGU> sigunguList = new ArrayList<SIGUNGU>();
        SIGUNGU sigungu = null;

        Cursor cursor = db.query("SIGUNGU", null, "SIDO_id=?",
                new String[]{SIDO_id+""}, null, null, null);
        int count = cursor.getCount(); //레코드 개수를 반환
        Log.d("시군구lsit", "조회된 row: " + count);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String SIGUNGU = cursor.getString(1);
            int SIDOid = cursor.getInt(2);
            sigungu = new SIGUNGU(_id, SIGUNGU,SIDOid);
            sigunguList.add(sigungu);
        }
        return sigunguList;
    }


    // TODO: ==============================================================================
    // TODO: 선상낚시 리스트
    public ArrayList<OnBoard> OnBoardList(String SIDO, String SIGUNGU) {
        ArrayList<OnBoard> onBoardList = new ArrayList<OnBoard>();
        OnBoard onBoard = null;
        Cursor cursor = null;
        Log.d("SIDO", SIDO+","+SIGUNGU);
        if(SIDO.equals("")){ //시도 선택 안했을 때
            Log.d("case", "case1");
            cursor = db.query("OnBoard", null, null,
                    null, null, null, null);
        }else{ // 시도 선택
            if(SIGUNGU.equals("")) { // 시도 선택하고 시군구 선택 안했을 때
                Log.d("case", "case2");
                cursor = db.query("OnBoard", null, "ADR_KNM like ?",
                        new String[]{"%" + SIDO + "%"}, null, null, null);
            }
            else{ // 시도 선택하고 시군구 선택
                Log.d("case", "case3");
                 cursor = db.query("OnBoard", null, "ADR_KNM like ? and ADR_KNM like ?",
                            new String[]{"%" + SIDO + "%", "%"+SIGUNGU+"%"}, null, null, null);

            }
        }

        int count = cursor.getCount(); //레코드 개수를 반환
        Log.d("OnBoardlsit", "조회된 row: " + count);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String name = cursor.getString(1);
            String point_nm = cursor.getString(2);
            String dpwt = cursor.getString(3); // 깊이
            String material = cursor.getString(4);
            String tide_time = cursor.getString(5);
            String target = cursor.getString(6);
            String latitude = cursor.getString(7);
            String longitude = cursor.getString(8);
            String adr_knm = cursor.getString(9); // 주소

            onBoard = new OnBoard(_id, name, point_nm, dpwt, material, tide_time, target, latitude, longitude, adr_knm);
            onBoardList.add(onBoard);
        }
        return onBoardList;
    }

    // TODO: 갯바위낚시 리스트
    public ArrayList<Rock> RockList(String SIDO, String SIGUNGU) {
        ArrayList<Rock> rockList = new ArrayList<Rock>();
        Rock rock = null;
        Cursor cursor = null;
        if(SIDO.equals("")){ //시도 선택 안했을 때
            cursor = db.query("Rock", null, null,
                    null, null, null, null);
        }else{ // 시도 선택
            if(SIGUNGU.equals("")) { // 시도 선택하고 시군구 선택 안했을 때
                cursor = db.query("Rock", null, "ADR_KNM like ?",
                        new String[]{"%" + SIDO + "%"}, null, null, null);
            }
            else{ // 시도 선택하고 시군구 선택
                cursor = db.query("Rock", null, "ADR_KNM like ? and ADR_KNM like ?",
                        new String[]{"%" + SIDO + "%", "%"+SIGUNGU+"%"}, null, null, null);

            }
        }

        int count = cursor.getCount(); //레코드 개수를 반환
        Log.d("Rock", "조회된 row: " + count);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String name = cursor.getString(1);
            String point_nm = cursor.getString(2);
            String dpwt = cursor.getString(3); // 깊이
            String material = cursor.getString(4);
            String tide_time = cursor.getString(5);
            String target = cursor.getString(6);
            String latitude = cursor.getString(7);
            String longitude = cursor.getString(8);
            String adr_knm = cursor.getString(9); // 주소

            rock = new Rock(_id, name, point_nm, dpwt, material, tide_time, target, latitude, longitude, adr_knm);
            rockList.add(rock);
        }
        return rockList;
    }

    // TODO: 바다낚시 리스트
    public ArrayList<Sea> SeaList(String SIDO, String SIGUNGU) {
        ArrayList<Sea> seaList = new ArrayList<Sea>();
        Sea sea = null;
        Cursor cursor = null;
        Log.d("SIDO", SIDO+","+SIGUNGU);
        if(SIDO.equals("")){ //시도 선택 안했을 때
            Log.d("case", "case1");
            cursor = db.query("Sea", null, null,
                    null, null, null, null);
        }else{ // 시도 선택
            if(SIGUNGU.equals("")) { // 시도 선택하고 시군구 선택 안했을 때
                Log.d("case", "case2");
                cursor = db.query("Sea", null, "addr like ?",
                        new String[]{"%" + SIDO + "%"}, null, null, null);
            }
            else{ // 시도 선택하고 시군구 선택
                Log.d("case", "case3");
                cursor = db.query("Sea", null, "addr like ? and addr like ?",
                        new String[]{"%" + SIDO + "%", "%"+SIGUNGU+"%"}, null, null, null);

            }
        }

        int count = cursor.getCount(); //레코드 개수를 반환
        Log.d("Sea", "조회된 row: " + count);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String name = cursor.getString(1);
            String addr = cursor.getString(2);
            String target = cursor.getString(3);

            sea = new Sea(_id, name, addr, target);
            seaList.add(sea);
        }
        return seaList;
    }

    // TODO: 민물낚시 리스트
    public ArrayList<FreshWater> FreshwaterList(String SIDO, String SIGUNGU) {
        ArrayList<FreshWater> freshWaterList = new ArrayList<FreshWater>();
        FreshWater freshWater = null;
        Cursor cursor = null;
        Log.d("SIDO", SIDO+","+SIGUNGU);
        if(SIDO.equals("")){ //시도 선택 안했을 때
            Log.d("case", "case1");
            cursor = db.query("FreshWater", null, null,
                    null, null, null, null);
        }else{ // 시도 선택
            if(SIGUNGU.equals("")) { // 시도 선택하고 시군구 선택 안했을 때
                Log.d("case", "case2");
                cursor = db.query("FreshWater", null, "addr like ?",
                        new String[]{"%" + SIDO + "%"}, null, null, null);
            }
            else{ // 시도 선택하고 시군구 선택
                Log.d("case", "case3");
                cursor = db.query("FreshWater", null, "addr like ? and addr like ?",
                        new String[]{"%" + SIDO + "%", "%"+SIGUNGU+"%"}, null, null, null);

            }
        }

        int count = cursor.getCount(); //레코드 개수를 반환
        Log.d("FreshWater", "조회된 row: " + count);

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String name = cursor.getString(1);
            String addr = cursor.getString(2);
            String target = cursor.getString(3);

            freshWater = new FreshWater(_id, name, addr, target);
            freshWaterList.add(freshWater);
        }
        return freshWaterList;
    }
    // TODO: 자세히 보기 페이지 ===========================================================

    public Parcelable find(String category, String marker_title) {
        Parcelable point = null;
        Cursor cursor = null;
        Log.d("DB find", "호출됨"+category);

        switch (category){
            case "Onboard":
            case "Rock":
                cursor = db.query(category, null, "POINT_NM = ?",
                        new String[]{marker_title}, null, null, null);
                Log.d("DB find", cursor.getCount()+"");
                if (cursor.moveToNext()) {
                    int _id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String point_nm = cursor.getString(2);
                    String dpwt = cursor.getString(3); // 깊이
                    String material = cursor.getString(4);
                    String tide_time = cursor.getString(5);
                    String target = cursor.getString(6);
                    String latitude = cursor.getString(7);
                    String longitude = cursor.getString(8);
                    String adr_knm = cursor.getString(9); // 주소

                    if(category.equals("Onboard")){
                        point = new OnBoard(_id, name, point_nm, dpwt, material, tide_time, target, latitude, longitude, adr_knm);
                        Log.d("DB find", point.toString());
                    }else{
                        point = new Rock(_id, name, point_nm, dpwt, material, tide_time, target, latitude, longitude, adr_knm);
                    }
                }
                break;
            case "Freshwater":
            case "Sea":
                cursor = db.query(category, null, "name = ?",
                        new String[]{marker_title}, null, null, null);
                Log.d("DB find", cursor.getCount()+"");
                if(cursor.moveToNext()) {
                    int _id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String addr = cursor.getString(2);
                    String target = cursor.getString(3);
                    if(category.equals("Freshwater")){
                        point = new FreshWater(_id, name, addr, target);
                    }else{
                        point = new Sea(_id, name, addr, target);
                    }
                }
                break;
        }
        return point;
    }
}
