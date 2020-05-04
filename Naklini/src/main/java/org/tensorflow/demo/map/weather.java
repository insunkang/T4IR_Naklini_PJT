package org.tensorflow.demo.map;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.tensorflow.demo.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;


public class weather extends AppCompatActivity {
    TextView condition;
    TextView txt;
    ImageView imageView;
    String html = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=" +
            "bFLjSVwZpB%2BomeIbURaEI3jRNcEQ9j9jhqNnd2bDYYvybfq8qGRrA5zrU19E1b2w7TVtaw%2FZ%2BJhA5wZYDewN3g%3D%3D" +
            "&numOfRows=10&pageNo=1&base_date=20200423&base_time=0500&nx=1&ny=1&dataType=JSON";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        condition = findViewById(R.id.weather_condition);
        txt = findViewById(R.id.temp);
        imageView = findViewById(R.id.weather_icon);
        myAsync myAsync = new myAsync();
        myAsync.execute();
    }

    public class myAsync extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            /*Document document = null;
            try {
                document = Jsoup.connect(html).ignoreContentType(true).get();
                Log.d("tag","doc"+document);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            wapi();
            return null;
        }
    }

    public String wapi() {
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd HH");
        String sysdate = date.format (System.currentTimeMillis());
        String[] split = sysdate.split(" ");
        Log.d("data","sysdate:"+split[0]+split[1]);
        String today = "";
        try {
            String nx = "61"; //
            String ny = "125"; //
            String baseDate = split[0];
            String baseTime = basetime(split[1]); //시간  설정
            Log.d("time","time"+baseTime);
            String serviceKey = "bFLjSVwZpB%2BomeIbURaEI3jRNcEQ9j9jhqNnd2bDYYvybfq8qGRrA5zrU19E1b2w7TVtaw%2FZ%2BJhA5wZYDewN3g%3D%3D";
            String urlStr = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?" + "serviceKey="
                    + serviceKey + "&numOfRows=10&pageNo=1&base_date=" + baseDate + "&base_time=" + baseTime + "&nx="
                    + nx + "&ny=" + ny + "&dataType=JSON";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader bf;
            String line = "";
            String result = "";

            bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = bf.readLine()) != null) {
                result = result.concat(line);
                //System.out.println(result);
                Log.d("data","result =>"+result);
            }
            //JSONParser parser = new JSONParser();
            JSONObject obj = new JSONObject(result);
            JSONObject parse_response = (JSONObject) obj.get("response");
            JSONObject parse_body = (JSONObject) parse_response.get("body");
            JSONObject parse_items = (JSONObject) parse_body.get("items");
            JSONArray parse_item = (JSONArray) parse_items.get("item");
            String category="";
            JSONObject weather;
            String PTY = "";
            String T3H = "";
            for (int i = 0; i < parse_item.length(); i++) {
                weather = (JSONObject) parse_item.get(i);
                String fcst_Value = ((String) weather.get("fcstValue"));
                category = (String) weather.get("category");
                System.out.print(i + "번째");
                System.out.print(" category : " + category);
                System.out.print(" fcst_Value : " + fcst_Value);
                System.out.println();
                Log.d("data",i+"번째"+" category : " + category+" fcst_Value : " + fcst_Value);
                if(category.equals("PTY")) {
                    PTY = fcst_Value;
                    System.out.println(PTY);
                }
                if(category.equals("T3H")) {
                    T3H = fcst_Value;
                    System.out.println(T3H);
                }
            }
            if(PTY.equals("1")) {
                //today = "1";//비
                txt.setText("비");
            }else if(PTY.equals("2") || PTY.equals("3")) {
                //today = "2";//눈
                txt.setText("눈");
            }else if(Integer.parseInt(T3H)>30) {
                //today = "3";//더울때
                txt.setText("더워");
                if(PTY.equals("1")) {
                    //today = "1";
                    txt.setText("비오고 더워");
                }
            }else if(Integer.parseInt(T3H)<0) {
                //today = "4";//추울때
                txt.setText("추워");
                if(PTY.equals("1")) {
                    today = "1";
                    txt.setText("비오고 추워");
                }else if(PTY.equals("2") || PTY.equals("3")) {
                    today = "2";
                    txt.setText("눈오고 추워");
                }
            }else {
                today="5";//전체중 랜덤
                txt.setText(T3H+"°C");
                imageView.setImageResource(R.drawable.wi_day_rain);
                condition.setText("Rain");
            }
            System.out.println(today);
            bf.close();
            conn.disconnect();
            //System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*List<weatherVO> wlist = service.weathersearch(today);
        System.out.println(wlist.get(0).food_keyword);
        mav.addObject("food", wlist.get(0).food_keyword);
        mav.addObject("today", today);
        mav.setViewName("weather");*/
        return "";
    }

    public String basetime(String split){
        //Base_time : 0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)
        if (Integer.parseInt(split) >= 23){
            split = "2300";
        }else if (Integer.parseInt(split) >= 0 && Integer.parseInt(split) < 2){
            split = "2300";
        }else if (Integer.parseInt(split) >= 2 && Integer.parseInt(split) < 5){
            split = "0200";
        }else if (Integer.parseInt(split) >= 5 && Integer.parseInt(split) < 8){
            split = "0500";
        }else if (Integer.parseInt(split) >= 8 && Integer.parseInt(split) < 11){
            split = "0800";
        }else if (Integer.parseInt(split) >= 11 && Integer.parseInt(split) < 14){
            split = "1100";
        }else if (Integer.parseInt(split) >= 14 && Integer.parseInt(split) < 17){
            split = "1400";
        }else if (Integer.parseInt(split) >= 17 && Integer.parseInt(split) < 20){
            split = "1700";
        }else if (Integer.parseInt(split) >= 20 && Integer.parseInt(split) < 23){
            split = "2000";
        }

        Log.d("data",split+"");
        return split;
    }
}
