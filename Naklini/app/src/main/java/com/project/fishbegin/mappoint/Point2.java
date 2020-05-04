package com.project.fishbegin.mappoint;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.fishbegin.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Point2 extends AppCompatActivity {
    public static final String ROOT_DIR = "/data/data/org.tensorflow.demo/databases/";
    DBHandler handler;
    // 화면 하나당 프래그먼트 1개씩 필요
    TextView categoryView;
    // 위- 검색바
    Spinner dropdown1;
    Spinner dropdown2;
    // 센터- DB에서 불러온 리스트뷰
    ListView listview;

    // 객체에서 일부는 뷰로 표시하고 뷰에 표시 하지 않은 나머지를 사용하고 싶으면
    // position을 받을 수 있으므로 list.get(position)으로 객체를 다시 받아와서 나머지 정보에 접근하면 된다.
    ArrayList<SIDO> SIDO_list;
    SIDO sido;
    ArrayList<SIGUNGU> SIGUNGU_list;
    SIGUNGU sigungu;

    ArrayList<OnBoard> Onboardlist;
    ArrayList<Sea> Sealist;
    ArrayList<Rock> Rocklist;
    ArrayList<FreshWater> Freshwaterlist;
    ArrayList<HashMap<String, String>> list;
    Intent intent;
    String category;

    ArrayList<HashMap<String, String>> emptylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point2);
        categoryView = findViewById(R.id.category);
        dropdown1 = findViewById(R.id.dropdown1);
        dropdown2 = findViewById(R.id.dropdown2);
        listview = findViewById(R.id.listview);

        handler = DBHandler.open(this);
        intent = getIntent();
        category = intent.getStringExtra("button");
        viewSetText(category);


        // 빈 포인트
        emptylist = new ArrayList<>();
        HashMap<String, String> emptymap = new HashMap<>();
        emptymap.put("msg", "검색한 포인트가 없습니다.");
        emptylist.add(emptymap);

        // 앱이 실행될 때 딱 한번만 데이터를 받아야할 때 onCreate에서 함 -> 시도, 시군구 데이터
        // TODO: =====================================================================
        // TODO: 검색바 - 액티비티에 포함
        // TODO: 시도
        SIDO_list = handler.SIDOList();
        ArrayList<String> dropdown1_list = new ArrayList<String>();
        int size = SIDO_list.size();
        for (int i = 0; i < size; ++i) {
            dropdown1_list.add(SIDO_list.get(i).SIDO);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, dropdown1_list);
        dropdown1.setAdapter(adapter);
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                // 시도 데이터
                sido = SIDO_list.get(position);
                // 1.시도에 맞는 리스트 출력
                printListView(sido.SIDO, "");
                // 2. 시도에 맞는 시군구 드롭다운 메뉴 출력
                //TODO: 시군구
                SIGUNGU_list = handler.SIGUNGUList(sido._id);
                ArrayList<String> dropdown2_list = new ArrayList<String>();
                int size = SIGUNGU_list.size();
                for (int i = 0; i < size; ++i) {
                    dropdown2_list.add(SIGUNGU_list.get(i).SIGUNGU);
                }

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Point2.this,
                        android.R.layout.simple_spinner_dropdown_item, dropdown2_list);
                dropdown2.setAdapter(adapter2);
                dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        // 시군구 데이터
                        sigungu = SIGUNGU_list.get(position);
                        // 1. 시군구에 맞는 리스트 출력
                        printListView(sido.SIDO, sigungu.SIGUNGU);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 전체 출력하기에는 양이 너무 많으니까 출력X
            }
        });
        // TODO: =========================================================================
    }

    private void viewSetText(String category) {
        switch (category){
            case "Onboard":
                categoryView.setText("선상낚시");
                break;
            case "Freshwater":
                categoryView.setText("민물낚시");
                break;
            case "Rock":
                categoryView.setText("갯바위낚시");
                break;
            case "Sea":
                categoryView.setText("바다낚시");
                break;
        }
    }

    // 어차피 드롭다운 메뉴 선택할때마다 DB에서 새로 불러와야 하므로 onCreate에서 category데이터
    // 미리 불러올 필요 없다.

    public void printListView(String SIDO, String SIGUNGU) {
        switch (category) {
            case "Onboard":
                printOnboard(SIDO, SIGUNGU);
                break;
            case "Freshwater":
                printFreshwater(SIDO, SIGUNGU);
                break;
            case "Rock":
                printRock(SIDO, SIGUNGU);
                break;
            case "Sea":
                printSea(SIDO, SIGUNGU);
                break;
        }
    }

    // TODO: ====================================================================
    // TODO: 리스트뷰(): NAME + POINT_NM

    public void printOnboard(String SIDO, String SIGUNGU) {
        // DB에서 조건에 맞는 데이터 가져옴
        Onboardlist = handler.OnBoardList(SIDO, SIGUNGU);
        if (Onboardlist.size() == 0) {
            SimpleAdapter adapter = new SimpleAdapter(this,
                    emptylist, android.R.layout.simple_list_item_1,
                    new String[]{"msg"},
                    new int[]{android.R.id.text1}
            );
            listview.setAdapter(adapter);
            listview.setEnabled(false); // 리스트뷰 클릭 못함
            return;
        }
        // 리스트뷰 출력용
        list = new ArrayList<>();
        HashMap<String, String> map = null;
        for (OnBoard point : Onboardlist) {
            map = new HashMap<String, String>();
            map.put("id", point._id + "");
            map.put("name", point.point_nm + "");
            map.put("rest", point.name + "");
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                list, android.R.layout.simple_list_item_2,
                new String[]{"name", "rest"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listview.setAdapter(adapter);
        listview.setEnabled(true);
        listview.setOnItemClickListener(new ListItemClick());
    }

    public void printRock(String SIDO, String SIGUNGU) {
        // DB에서 조건에 맞는 데이터 가져옴
        Rocklist = handler.RockList(SIDO, SIGUNGU);
        if (Rocklist.size() == 0) {
            SimpleAdapter adapter = new SimpleAdapter(this,
                    emptylist, android.R.layout.simple_list_item_1,
                    new String[]{"msg"},
                    new int[]{android.R.id.text1}
            );
            listview.setAdapter(adapter);
            listview.setEnabled(false);
            return;
        }
        // 리스트뷰 출력용
        list = new ArrayList<>();
        HashMap<String, String> map = null;
        for (Rock point : Rocklist) {
            map = new HashMap<String, String>();
            map.put("id", point._id + "");
            map.put("name", point.point_nm + "");
            map.put("rest", point.name + "");
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                list, android.R.layout.simple_list_item_2,
                new String[]{"name", "rest"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listview.setAdapter(adapter);
        listview.setEnabled(true);
        listview.setOnItemClickListener(new ListItemClick());
    }

    public void printSea(String SIDO, String SIGUNGU) {
        // DB에서 조건에 맞는 데이터 가져옴
        Sealist = handler.SeaList(SIDO, SIGUNGU);
        if (Sealist.size() == 0) {
            SimpleAdapter adapter = new SimpleAdapter(this,
                    emptylist, android.R.layout.simple_list_item_1,
                    new String[]{"msg"},
                    new int[]{android.R.id.text1}
            );
            listview.setAdapter(adapter);
            listview.setEnabled(false);
            return;
        }
        // 리스트뷰 출력용
        list = new ArrayList<>();
        HashMap<String, String> map = null;
        for (Sea point : Sealist) {
            map = new HashMap<String, String>();
            map.put("id", point._id + "");
            map.put("name", point.name + "");
            map.put("rest", point.addr + "");
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                list, android.R.layout.simple_list_item_2,
                new String[]{"name", "rest"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listview.setAdapter(adapter);
        listview.setEnabled(true);
        listview.setOnItemClickListener(new ListItemClick());
    }

    public void printFreshwater(String SIDO, String SIGUNGU) {
        // DB에서 조건에 맞는 데이터 가져옴
        Freshwaterlist = handler.FreshwaterList(SIDO, SIGUNGU);
        if (Freshwaterlist.size() == 0) {
            SimpleAdapter adapter = new SimpleAdapter(this,
                    emptylist, android.R.layout.simple_list_item_1,
                    new String[]{"msg"},
                    new int[]{android.R.id.text1}
            );
            listview.setAdapter(adapter);
            listview.setEnabled(false);
            return;
        }
        // 리스트뷰 출력용
        list = new ArrayList<>();
        HashMap<String, String> map = null;
        for (FreshWater point : Freshwaterlist) {
            map = new HashMap<String, String>();
            map.put("id", point._id + "");
            map.put("name", point.name + "");
            map.put("rest", point.addr + "");
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                list, android.R.layout.simple_list_item_2,
                new String[]{"name", "rest"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listview.setAdapter(adapter);
        listview.setEnabled(true);
        listview.setOnItemClickListener(new ListItemClick());
    }


    // TODO: map.java로 돌아감
    // TODO: 지도 마커 클릭하면 지명과 아래 '자세히보기' 텍스트 뜸. '자세히보기' 누르면
    // TODO: 어종, 날씨 페이지로
    class ListItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (category) {
                case "Onboard":
                    OnBoard onBoard = Onboardlist.get(position);
                    returnCategory("Onboard", onBoard);
                    break;
                case "Freshwater":
                    FreshWater freshWater = Freshwaterlist.get(position);
                    returnCategory("Freshwater", freshWater);
                    break;
                case "Rock":
                    Rock rock = Rocklist.get(position);
                    returnCategory("Rock", rock);
                    break;
                case "Sea":
                    Sea sea = Sealist.get(position);
                    returnCategory("Sea", sea);
                    break;
            }
        }
    }

    public void returnCategory(String category, Parcelable point){
        //intent = new Intent(Point2.this, map.class);
        intent = getIntent();
        intent.putExtra("returnCategory", category);
        intent.putExtra("Parcelable", point);
        //아이템 클릭했으면 Point1으로 돌아갈 수 없으므로 finish
        /*Intent intent2 = new Intent(Point2.this, Point1.class);
        Log.d("intent", "point1로 돌아감");
        intent2.putExtra("flag", 1);*/
        setResult(RESULT_OK, intent);
        //setResult(RESULT_OK, intent2);
        finish();
    }

}





