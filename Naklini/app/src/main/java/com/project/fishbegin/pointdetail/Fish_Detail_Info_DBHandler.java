package com.project.fishbegin.pointdetail;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.fishbegin.mappoint.ExamDBHelper;

import java.util.ArrayList;

public class Fish_Detail_Info_DBHandler {
    static ExamDBHelper DBHelper;
    static SQLiteDatabase DB;

    public static Fish_Detail_Info_DBHandler open(Context context){
        DBHelper = new ExamDBHelper(context);
        DB = DBHelper.getWritableDatabase();
        return new Fish_Detail_Info_DBHandler();
    }

    public ArrayList<Fish_Detail_InfoList> InfoList(){
        ArrayList<Fish_Detail_InfoList> infoList = new ArrayList<>();
        Fish_Detail_InfoList info = null;

        Cursor cursor = DB.query("SPECIES", null, null,
                null, null, null, null);
        int count = cursor.getCount();
        while(cursor.moveToNext()){
            int num = cursor.getInt(0);
            String fish_name = cursor.getString(1);
            String distribution = cursor.getString(2);
            String habitat = cursor.getString(3);
            String img = cursor.getString(4);
            info = new Fish_Detail_InfoList(num,fish_name,distribution,habitat,img);
            infoList.add(info);
        }
        return infoList;
    }
}
