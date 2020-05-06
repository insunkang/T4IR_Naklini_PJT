package org.tensorflow.demo.map;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.demo.R;
import org.tensorflow.demo.map.weatherdb.DataAdapter;
import org.tensorflow.demo.map.weatherdb.location;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;


public class weather extends AppCompatActivity {
    TextView condition;
    TextView txt;
    ImageView imageView;
    TextView day;
    TextView empty;
    List<location> locationList;
    String html = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=" +
            "bFLjSVwZpB%2BomeIbURaEI3jRNcEQ9j9jhqNnd2bDYYvybfq8qGRrA5zrU19E1b2w7TVtaw%2FZ%2BJhA5wZYDewN3g%3D%3D" +
            "&numOfRows=10&pageNo=1&base_date=20200423&base_time=0500&nx=1&ny=1&dataType=JSON";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        condition = findViewById(R.id.weather_condition);
        txt = findViewById(R.id.temp);
        imageView = findViewById(R.id.weather_icon);
        day = findViewById(R.id.day_of_week);
        empty = findViewById(R.id.empty_textview);

        myAsync myAsync = new myAsync();
        myAsync.execute();
    }

    public class myAsync extends AsyncTask<HashMap<String, String>,HashMap<String,String>,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd HH");
            String sysdate = date.format (System.currentTimeMillis());
            String today = sysdate.substring(4,8);
            day.setText(today);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(HashMap<String, String>... values) {
            super.onProgressUpdate(values);
            for (int j = 0; j < values[0].size(); j++){
                Log.d("listtt",values[0].get(j)+"***");
            }
            String SKY = "";
            String WSD = "";
            if (Integer.parseInt(values[0].get("SKY")) <= 5){
                SKY = "맑음";
            }else if (Integer.parseInt(values[0].get("SKY")) >= 6 && Integer.parseInt(values[0].get("SKY")) <= 8){
                SKY = "구름많음";
            }else if (Integer.parseInt(values[0].get("SKY")) >= 9 && Integer.parseInt(values[0].get("SKY")) <= 10){
                SKY = "흐림";
            }
            if (values[0].get("WSD") != null){
                if (Double.parseDouble(values[0].get("WSD")) < 4){
                    WSD = "약";
                }else if (Double.parseDouble(values[0].get("WSD")) >= 4 && Double.parseDouble(values[0].get("WSD")) < 9){
                    WSD = "약간 강함";
                }else if (Double.parseDouble(values[0].get("WSD")) >= 9 && Double.parseDouble(values[0].get("WSD")) < 14){
                    WSD = "강함";
                }else if (Double.parseDouble(values[0].get("WSD")) >= 14){
                    WSD = "매우 강함";
                }
                empty.append("풍속 : "+WSD);
            }
            empty.setText("강수확률 : "+values[0].get("POP")+"%" +"\n" +
                    "습도 : "+values[0].get("REH")+"%" +"\n" +
                    "하늘상태 : "+SKY +"\n"
            );
            if(values[0].get("PTY").equals("1")) {
                //today = "1";//비
                txt.setText(values[0].get("T3H")+"°C");
                imageView.setImageResource(R.drawable.wi_day_rain);
                condition.setText("Rain");
            }else if(values[0].get("PTY").equals("2") || values[0].get("PTY").equals("3")) {
                //today = "2";//눈
                txt.setText(values[0].get("T3H")+"°C");
                imageView.setImageResource(R.drawable.wi_day_snow);
                condition.setText("Snow");
            }else if(Integer.parseInt(values[0].get("T3H"))>25) {
                //today = "3";//더울때
                txt.setText(values[0].get("T3H")+"°C");
                imageView.setImageResource(R.drawable.wi_day_sunny);
                condition.setText("Hot");
                if(values[0].get("PTY").equals("1")) {
                    //today = "1";
                    txt.setText(values[0].get("T3H")+"°C");
                    imageView.setImageResource(R.drawable.wi_day_rain);
                    condition.setText("Rain/Hot");
                }
            }else if(Integer.parseInt(values[0].get("T3H"))<3) {
                //today = "4";//추울때
                txt.setText(values[0].get("T3H")+"°C");
                imageView.setImageResource(R.drawable.wi_day_windy);
                condition.setText("Cold");
                if(values[0].get("PTY").equals("1")) {
                    //today = "1";
                    //txt.setText("비오고 추워");
                    txt.setText(values[0].get("T3H")+"°C");
                    imageView.setImageResource(R.drawable.wi_night_sprinkle);
                    condition.setText("Rain/Cold");
                }else if(values[0].get("PTY").equals("2") || values[0].get("PTY").equals("3")) {
                    //today = "2";
                    //txt.setText("눈오고 추워");
                    txt.setText(values[0].get("T3H")+"°C");
                    imageView.setImageResource(R.drawable.wi_night_snow);
                    condition.setText("Snow/Cold");
                }
            }else {
                //today="5";//전체중 랜덤
                //int su = Integer.parseInt(values[0].get("T3H"))+5;
                txt.setText(values[0].get("T3H")+"°C");
                imageView.setImageResource(R.drawable.wi_day_sunny);
                condition.setText("Sunny");
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(HashMap<String, String>... strings) {
            /*Document document = null;
            try {
                document = Jsoup.connect(html).ignoreContentType(true).get();
                Log.d("tag","doc"+document);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd HH");
            String sysdate = date.format (System.currentTimeMillis());
            String[] split = sysdate.split(" ");
            Log.d("data","sysdate:"+split[0]+split[1]);
            String today = "";


            String addr = getIntent().getStringExtra("addr");
            DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
            mDbHelper.createDatabase();
            mDbHelper.open();

            // db에 있는 값들을 model을 적용해서 넣는다.
            locationList = mDbHelper.getTableData();

            // db 닫기
            mDbHelper.close();

            try {
                String nx = ""; //
                String ny = ""; //
                String si = "";
                String[] addrsplit = addr.split(" ");
                for (int i = 0; i < locationList.size(); i++){
                    if (addrsplit[1].equals(locationList.get(i).getSi())){
                        nx = locationList.get(i).getX();
                        ny = locationList.get(i).getY();
                        si = locationList.get(i).getSi();
                    }else if (addrsplit[0].equals(locationList.get(i).getGu())){
                        nx = locationList.get(i).getX();
                        ny = locationList.get(i).getY();
                        si = locationList.get(i).getGu();
                    }
                    else if (addrsplit[0].equals(locationList.get(i).getSi())){
                        nx = locationList.get(i).getX();
                        ny = locationList.get(i).getY();
                        si = locationList.get(i).getSi();
                    }
                }
                Log.d("nxny",nx+"--------"+ny+"--------"+si+"----------"+addr+"----------"+addrsplit[0]);
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
                String POP = "";
                String REH = "";
                String SKY = "";
                String WAV = "";
                String VEC = "";
                String WSD = "";
                HashMap<String ,String> list = new HashMap<>();
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
                        list.put("PTY", PTY);
                    }
                    if(category.equals("T3H")) {
                        T3H = fcst_Value;
                        System.out.println(T3H);
                        list.put("T3H", T3H);
                    }
                    if(category.equals("POP")) {
                        POP = fcst_Value;
                        System.out.println(POP);
                        list.put("POP", POP);
                    }
                    if (category.equals("REH")){
                        REH = fcst_Value;
                        list.put("REH", REH);
                    }
                    if (category.equals("SKY")){
                        SKY = fcst_Value;
                        list.put("SKY", SKY);
                    }
                    if (category.equals("WAV")){
                        WAV = fcst_Value;
                        list.put("WAV", WAV);
                    }
                    if (category.equals("VEC")){
                        VEC = fcst_Value;
                        list.put("VEC", VEC);
                    }
                    if (category.equals("WSD")){
                        WSD = fcst_Value;
                        list.put("WSD", WSD);
                    }
                }
                Log.d("listsize",list.size()+"++++");
                publishProgress(list);
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
