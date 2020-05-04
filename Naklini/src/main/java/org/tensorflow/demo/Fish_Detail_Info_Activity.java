package org.tensorflow.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fish_Detail_Info_Activity extends AppCompatActivity {
    Context mContext;
    Fish_Detail_Info_DBHandler handler;
    ArrayList<Fish_Detail_InfoList> infoList;
    RecyclerView infoView;
    FishInfoAdapter adapter;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = Fish_Detail_Info_Activity.this;
        setContentView(R.layout.fish_detail_info);
        infoView = findViewById(R.id.fish_info);
        handler = Fish_Detail_Info_DBHandler.open(mContext);

        //setThread();
        //Log.d("bitmap","사진데이터 : "+bitmap);

        infoList = handler.InfoList();
        ArrayList<Fish_Detail_InfoList> List = new ArrayList<>();
        for(int i=1;i<infoList.size();i++){
            Fish_Detail_InfoList info = new Fish_Detail_InfoList();
            info.setImg(infoList.get(i).img);
            info.setNum(infoList.get(i).num);
            info.setFish_name(infoList.get(i).fish_name);
            info.setDistribution(infoList.get(i).distribution);
            info.setHabitat(infoList.get(i).habitat);
            List.add(info);
        }
        adapter = new FishInfoAdapter(mContext, R.layout.info_detail,List);
        infoView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        infoView.setLayoutManager(manager);
        infoView.setAdapter(adapter);
    }
/*
    public void setThread(){
        final ArrayList<Bitmap> bitmaps = new ArrayList<>();
        Thread thread = new Thread(){
            @Override
            public void run() {
                URL url = null;
                handler = Fish_Detail_Info_DBHandler.open(mContext);
                infoList = handler.InfoList();

                try {
                    for (int i=45;i<=infoList.size();i++){
                        url = new URL(infoList.get(i).img);
                        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();
                        InputStream input = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(input);
                        input.close();
                        bitmaps.add(bitmap);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

}
