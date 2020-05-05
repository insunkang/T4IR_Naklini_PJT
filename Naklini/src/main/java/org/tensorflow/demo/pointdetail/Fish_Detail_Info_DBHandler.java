package org.tensorflow.demo.pointdetail;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.GridLayout;

import org.tensorflow.demo.Fish_Detail_InfoList;
import org.tensorflow.demo.mappoint.ExamDBHelper;

import java.util.ArrayList;

public class Fish_Detail_Info_DBHandler {
    static ExamDBHelper DBHelper;
    static SQLiteDatabase DB;

    public static Fish_Detail_Info_DBHandler open(Context context){
        DBHelper = new ExamDBHelper(context);
        DB = DBHelper.getWritableDatabase();
        return new Fish_Detail_Info_DBHandler();
    }

    public ArrayList<Species> SpeciesList(ArrayList<String> target){
        ArrayList<Species> speciesList = new ArrayList<>();
        Species species = null;

        int listSize = target.size();
        for(int i=0; i< listSize; ++i){
            Log.d("species String넘어옴:", target.get(i));
            Cursor cursor = DB.query("SPECIES", null, "name=?",
                    new String[]{target.get(i)}, null, null, null);
            // DB에 어종정보 있으면
            if(cursor.moveToNext()){
                int _id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dist = cursor.getString(2);
                String living = cursor.getString(3);
                String size = cursor.getString(4);
                String picture = cursor.getString(5);
                species = new Species(_id, name, dist, living, size, picture);
                speciesList.add(species);
                Log.d("species", species.toString());
            }else{ // DB에 없으면 _id에 없는 아이디번호(-1)를 저장하고 if(_id == -1)이면 "정보가 없습니다" 출력
                species = new Species(-1, target.get(i));
                speciesList.add(species);
                Log.d("species", species.name);
            }
        }
        return speciesList;
    }
}
