package org.tensorflow.demo.pointdetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.demo.R;
import org.tensorflow.demo.mappoint.DBHandler;
import org.tensorflow.demo.mappoint.FreshWater;
import org.tensorflow.demo.mappoint.OnBoard;
import org.tensorflow.demo.mappoint.Rock;
import org.tensorflow.demo.mappoint.Sea;


public class Point_Info_Activity extends AppCompatActivity {
    LinearLayout point_info;
    Intent detail_intent;
    TextView detail_pointName;
    TextView detail_address;
    TextView fish_name;
    TextView callMap;
    TextView regionInfo;

    String category;
    String point_nm;

    DBHandler handler;
    OnBoard onBoard;
    Rock rock;
    Sea sea;
    FreshWater freshWater;

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

        handler = new DBHandler();

        // TODO: 연결==========================================================
        detail_intent = getIntent();
        category = detail_intent.getStringExtra("categoryDetail");
        point_nm = detail_intent.getStringExtra("POINT_NM");
        Log.d("intent", category+" "+point_nm);
        // TODO: ===============================================================

        callMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 불러오기
        switch (category){
            case "Onboard":
                onBoard = (OnBoard)handler.find(category, point_nm);
                printOnboard();
                break;
            case "Freshwater":
                freshWater = (FreshWater)handler.find(category, point_nm);
                printFreshwater();
                break;
            case "Rock":
                rock = (Rock)handler.find(category, point_nm);
                printRock();
                break;
            case "Sea":
                sea = (Sea)handler.find(category, point_nm);
                printSea();
                break;
        }

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

}
