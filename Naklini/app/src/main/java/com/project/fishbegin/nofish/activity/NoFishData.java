package com.project.fishbegin.nofish.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.fishbegin.R;
import com.project.fishbegin.nofish.adapter.NoFishAdapter;
import com.project.fishbegin.nofish.adapter.NoFishDetailAdapter;
import com.project.fishbegin.nofish.item.Calendar_data;
import com.project.fishbegin.nofish.item.Date_data;
import com.project.fishbegin.nofish.item.Detail_data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NoFishData extends AppCompatActivity implements View.OnClickListener {
        Context mContext;
        TextView currentMonth;
        ImageView prevMonth;
        ImageView nextMonth;
        GridView calendarView;
        Calendar cal;
        int month, year;
        ArrayList<Calendar_data> calList = new ArrayList<>();
        ArrayList<Date_data> dateList = new ArrayList<>();
        ArrayList<Detail_data> detailList = new ArrayList<>();

        int lastDay;
        NoFishAdapter adapter;
        NoFishDetailAdapter adapter2;
        public static final String ROOT_DIR = "/data/data/com.project.fishbegin/databases/";
        public SQLiteDatabase db;
        public Cursor cursor;
        DBHelper mHelper;
        RecyclerView detail;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_no_fish_data);
                mContext = NoFishData.this;
                Intent intent = getIntent();
                //달력
                cal = Calendar.getInstance(Locale.getDefault());
                month = cal.get(Calendar.MONTH) + 1;
                year = cal.get(Calendar.YEAR);
                prevMonth = findViewById(R.id.cal_prev_btn);
                nextMonth = findViewById(R.id.cal_next_btn);
                currentMonth = findViewById(R.id.cal_date);
                currentMonth.setText(year + "년 " + month + "월");
                calendarView = findViewById(R.id.calendar);
                CalendarList(month, year);
                //금어기 달력데이터
                setDB(this);
                mHelper = new DBHelper(this);
                db = mHelper.getWritableDatabase();
                DateList();
                db.close();
                //금어기 상세 데이터
                detail = findViewById(R.id.detail);
                for(int i = 0; i<dateList.size(); i++){
                        Detail_data detail_data = new Detail_data();
                        detail_data.setTitle("어종 : "+dateList.get(i).getTitle());
                        Log.d("gettitle","어종 : "+dateList.get(i).getTitle());
                        detail_data.setStart_date("금어기 시작 : "+dateList.get(i).getStart_month()+" / "+dateList.get(i).getStart_day());
                        detail_data.setFinish_date("금어기 종료 : "+dateList.get(i).getFinish_month()+" / "+dateList.get(i).getFinish_day());
                        detailList.add(detail_data);
                }
                //어댑터
                adapter2 = new NoFishDetailAdapter(mContext,R.layout.detail,detailList);
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                detail.setLayoutManager(manager);
                detail.setAdapter(adapter2);

                adapter = new NoFishAdapter(mContext, calList, dateList);
                adapter.notifyDataSetChanged();
                calendarView.setAdapter(adapter);
        }
        public void DateList(){
            cursor = db.rawQuery("SELECT * FROM test",null);
            startManagingCursor(cursor);
            while (cursor.moveToNext()){
                    Date_data date = new Date_data();
                    date.setTitle(cursor.getString(0));
                    date.setStart_month(cursor.getString(1));
                    date.setStart_day(cursor.getString(2));
                    date.setFinish_month(cursor.getString(3));
                    date.setFinish_day(cursor.getString(4));

                dateList.add(date);
            }
            cursor.close();
        }
        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                        case R.id.cal_prev_btn:
                                if (month <= 1) {
                                        month = 12;
                                        year--;
                                } else {
                                        month--;
                                }
                                setNoFishAdapter(month, year);
                                break;
                        case R.id.cal_date:
                                cal = Calendar.getInstance(Locale.getDefault());
                                month = cal.get(Calendar.MONTH) + 1;
                                year = cal.get(Calendar.YEAR);
                                setNoFishAdapter(month, year);
                                break;
                        case R.id.cal_next_btn:
                                if (month > 11) {
                                        month = 1;
                                        year++;
                                } else {
                                        month++;
                                }
                                setNoFishAdapter(month, year);

                }
        }

        private void setNoFishAdapter(int month, int year) {
                CalendarList(month, year);
                adapter = new NoFishAdapter(mContext, calList,dateList);
                adapter.notifyDataSetChanged();
                calendarView.setAdapter(adapter);
                currentMonth.setText(year + "년 " + month + "월");
        }

        public void CalendarList(int CurrentMonth, int year) {
                int preMonth = 0;
                int preYear = 0;
                int nextMonth = 0;
                int nextYear = 0;

                calList.clear();
                Calendar cal = Calendar.getInstance();
                int nowMonth = cal.get(Calendar.MONTH);
                int nowDay = cal.get(Calendar.DAY_OF_MONTH);
                cal.set(year, CurrentMonth - 1, 1);
                lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                if (CurrentMonth == 12) {
                        preMonth = CurrentMonth - 1;
                        nextMonth = CurrentMonth + 1;
                        preYear = year;
                        nextYear = year + 1;

                } else if (CurrentMonth == 1) {
                        preMonth = 12;
                        nextMonth = CurrentMonth + 1;
                        preYear = year - 1;
                        nextYear = year;
                } else {
                        preMonth = CurrentMonth - 1;
                        nextMonth = CurrentMonth + 1;
                        preYear = year;
                        nextYear = year;
                }

                //데이터 리스트 추가
                int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
                for (int i = 0; i < currentWeekDay; i++) {
                        addCalendarData("", "", "", "", "");
                }
                for (int i = 1; i <= lastDay; i++) {
                        String key = String.format("%02d", CurrentMonth) + String.format("%02d", i);
                        addCalendarData(String.valueOf(year), String.valueOf(CurrentMonth), String.valueOf(i), "", key);
                        if (CurrentMonth - 1 == nowMonth && i == nowDay) {
                                int index = getIndexOfCalList(key);
                                Calendar_data data = calendarData(String.valueOf(year), String.valueOf(CurrentMonth), String.valueOf(i), "", key);
                                calList.set(index, data);
                        }
                }
        }

        private Calendar_data calendarData(String year, String month, String day, String name, String key) {
                Calendar_data data = new Calendar_data();
                data.setYear(year);
                data.setMonth(month);
                data.setDay(day);
                data.setEvent(name);
                data.setKey(key);
                return data;
        }

        public void addCalendarData(String year, String month, String day, String name, String key) {
                Calendar_data data = calendarData(year, month, day, name, key);
                calList.add(data);
        }

        private int getIndexOfCalList(String search_key) {
                for (int s = 0; s < calList.size(); s++) {
                        String key = calList.get(s).getKey();
                        if (key != null && key.equals(search_key)) {
                                return s;
                        }
                }
                return -1;
        }
        public static void setDB(Context context){
                File folder = new File(ROOT_DIR);
                if(folder.exists()){
                }else {
                        folder.mkdirs();
                }
                AssetManager assetManager = context.getResources().getAssets();
                File outfile = new File(ROOT_DIR+"test.db");
                InputStream input = null;
                FileOutputStream fos = null;
                long filesize = 0;
                try {
                        input = assetManager.open("test.db",AssetManager.ACCESS_BUFFER);
                        filesize = input.available();
                        if(outfile.length()<=0){
                                byte[] tempdata = new byte[(int)filesize];
                                input.read(tempdata);
                                input.close();
                                outfile.createNewFile();
                                fos = new FileOutputStream(outfile);
                                fos.write(tempdata);
                                fos.close();
                        }
                }catch (IOException e){
                        e.printStackTrace();
                }
        }

}
class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
                super(context, "test.db", null,1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
}



