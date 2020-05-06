package org.tensorflow.demo.pointdetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.demo.R;
import org.tensorflow.demo.map.weather;
import org.tensorflow.demo.mappoint.DBHandler;
import org.tensorflow.demo.mappoint.FreshWater;
import org.tensorflow.demo.mappoint.OnBoard;
import org.tensorflow.demo.mappoint.Rock;
import org.tensorflow.demo.mappoint.Sea;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Point_Info_Activity extends AppCompatActivity {
    LinearLayout point_info;
    Intent detail_intent;
    TextView detail_pointName;
    TextView detail_address;
    TextView fish_name;
    TextView callMap;
    TextView regionInfo;
    LinearLayout fish_detail;

    String category;
    String point_nm;

    DBHandler handler;
    OnBoard onBoard;
    Rock rock;
    Sea sea;
    FreshWater freshWater;

    ArrayList<String> fishArray = new ArrayList<String>();

    LinearLayout callWeather;
    String addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_info_tool);
        point_info = findViewById(R.id.point_info);
        detail_pointName = findViewById(R.id.detail_pointName);
        detail_address = findViewById(R.id.detail_address);
        fish_name = findViewById(R.id.fish_name);
        callMap = findViewById(R.id.callMap);
        regionInfo = findViewById(R.id.regionInfo);
        fish_detail = findViewById(R.id.fish_detail);
        callWeather = findViewById(R.id.weather);

        handler = new DBHandler();

        // TODO: 연결==========================================================
        detail_intent = getIntent();
        category = detail_intent.getStringExtra("categoryDetail");
        point_nm = detail_intent.getStringExtra("POINT_NM");
        Log.d("intent", category+" "+point_nm);
        // TODO: ===============================================================

        // 불러오기
        switch (category){
            case "Onboard":
                onBoard = (OnBoard)handler.find(category, point_nm);
                printOnboard();
                addr = onBoard.adr_knm;
                fishArray = putFishArray1(onBoard.target);
                Log.d("species", fishArray.size()+"");
                break;
            case "Freshwater":
                freshWater = (FreshWater)handler.find(category, point_nm);
                printFreshwater();
                addr = freshWater.addr;
                // 민물낚시와 바다낚시는 어종 정보가 없는 경우 있음
                Log.d("species", fishArray.size()+"");
                if(freshWater.target!=null) fishArray = putFishArray2(freshWater.target);
                break;
            case "Rock":
                rock = (Rock)handler.find(category, point_nm);
                printRock();
                addr = rock.adr_knm;
                fishArray = putFishArray1(rock.target);
                break;
            case "Sea":
                sea = (Sea)handler.find(category, point_nm);
                printSea();
                addr = sea.addr;
                // 민물낚시와 바다낚시는 어종 정보가 없는 경우 있음
                if(sea.target != null) fishArray = putFishArray2(sea.target);
                break;
        }

        // 지도 불러오기
        callMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 상세 어종 보기
        fish_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fishArray.size() > 0){
                    Intent intent2 = new Intent(Point_Info_Activity.this, Fish_Detail_Info_Activity.class);
                    intent2.putExtra("fishArray", fishArray);
                    startActivity(intent2);
                }else{
                    Toast.makeText(Point_Info_Activity.this, "정보가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Point_Info_Activity.this, weather.class);
                intent.putExtra("addr",addr);
                startActivity(intent);
            }
        });

    }


    private void printOnboard() {
        detail_pointName.setText(onBoard.point_nm);
        detail_address.setText(onBoard.adr_knm+"("+onBoard.name+")");
        fish_name.setText(onBoard.target);
        // TODO: 깊이, 물질, 썰물
        regionInfo.setText("깊이: "+ onBoard.dpwt+"\n"+
                "지형: "+onBoard.material+"\n"+
                "조류: "+onBoard.tide_time
                );

    }

    private void printSea() {
        detail_pointName.setText(sea.name);
        detail_address.setText(sea.addr);
        if(sea.target == null){
            fish_name.setText("정보가 없습니다.");
        }else {
            fish_name.setText(sea.target);
        }
        // TODO: 깊이, 물질, 썰물
        regionInfo.setText("정보가 없습니다.");
    }

    private void printRock() {
        detail_pointName.setText(rock.point_nm);
        detail_address.setText(rock.adr_knm+"("+rock.name+")");
        fish_name.setText(rock.target);
        // TODO: 깊이, 물질, 썰물
        regionInfo.setText("깊이: "+ rock.dpwt+"\n"+
                "지형: "+rock.material+"\n"+
                "조류: "+rock.tide_time
        );
    }

    private void printFreshwater() {
        detail_pointName.setText(freshWater.name);
        detail_address.setText(freshWater.addr);
        if(freshWater.target== null){
            fish_name.setText("정보가 없습니다.");
        } else{
            fish_name.setText(freshWater.target);
        }
        // TODO: 깊이, 물질, 썰물
        regionInfo.setText("정보가 없습니다.");
    }

    // TODO: 어종 파싱 ======================================================================
    // OnBoard, Rock
    private ArrayList<String> putFishArray1(String target){
        ArrayList<String> fishArray = new ArrayList<>();
        // ▶뒤의 문자열로 시작하고 -로 끝나는 단어들
        String patternString = "▶.*-";
        Pattern p = Pattern.compile(patternString);
        Matcher m = p.matcher(target);
        //패턴에 일치하는 문자들만 추출해서 저장
        while (m.find()) {
            String data = m.group(); //패턴과 일치하는 단어
            String trimmed = data.replace("▶", "")
                                                .replace("-", "").trim();
            fishArray.add(trimmed);
        }
        return fishArray;
    }

    // Sea, Freshwater
    private ArrayList<String> putFishArray2(String target) {
        ArrayList<String> fishArray = new ArrayList<>();
        // , 기준으로 단어 쪼개기
        String patternString = ",";
        Pattern p = Pattern.compile(patternString);
        Matcher m = p.matcher(target);
        //패턴에 일치하지 않는 문자들만 추출해서 저장
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            //패턴에 만족하는 문자열을 " "로 치환한 후 전체 문자열을
            //StringBuffer에 저장
            m.appendReplacement(sb, " "); //" "로 치환->강제로 띄어쓰기 한 개 이상 넣어줌
        }
        m.appendTail(sb); // 패턴을 못 찾은 나머지 문장을 끝에 추가
        // 공백 한 칸 이상일 경우 잘라줌
        String[] result = sb.toString().split("\\s+");
        for (int i = 0; i < result.length; i++) {
            fishArray.add(result[i]);
        }
        return fishArray;
    }

}
