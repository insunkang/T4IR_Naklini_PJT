package org.tensorflow.demo.nofish.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.tensorflow.demo.R;
import org.tensorflow.demo.nofish.item.Calendar_data;
import org.tensorflow.demo.nofish.item.Date_data;

import java.util.ArrayList;

public class NoFishAdapter extends BaseAdapter implements View.OnClickListener {
    Context mContext;
    final ArrayList<Calendar_data> calList;
    final ArrayList<Date_data> datelist;
    LinearLayout Fish_data_cell;
    TextView DayView;
    TextView FishView;
    RecyclerView DetailView;


    public NoFishAdapter(Context context, ArrayList<Calendar_data> list, ArrayList<Date_data> dateList){
        mContext = context;
        calList = list;
        datelist = dateList;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getCount() {
        return calList.size();
    }

    @Override
    public Object getItem(int position) {
        return calList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater =
                    (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cal_data,parent,false);
        }
            Fish_data_cell = convertView.findViewById(R.id.calendar_cell);
            DayView = convertView.findViewById(R.id.DayView);
            FishView = convertView.findViewById(R.id.FishView);
            DetailView = convertView.findViewById(R.id.detailView);
            Fish_data_cell.setOnClickListener(this);

            String nowday = calList.get(position).getDay();
            String nowmonth = calList.get(position).getMonth();
            DayView.setText(nowday);
            DayView.setTextColor(Color.BLACK);
            for(int i=0;i<datelist.size();i++){
                if(nowmonth.equals(datelist.get(i).getStart_month())&&nowday.equals(datelist.get(i).getStart_day())){
                    DayView.setTextColor(Color.WHITE);
                    DayView.setTextSize(10);
                    DayView.setBackgroundColor(Color.rgb(255,102,0));
                    FishView.setText(datelist.get(i).getTitle());
                    FishView.setBackgroundColor(Color.rgb(255,102,0));
                    FishView.setTextColor(Color.WHITE);
                    FishView.setTextSize(15);

                }
                if(nowmonth.equals(datelist.get(i).getFinish_month())&&nowday.equals(datelist.get(i).getFinish_day())){
                    DayView.setTextColor(Color.WHITE);
                    DayView.setTextSize(10);
                    DayView.setBackgroundColor(Color.rgb(26,35,126));
                    FishView.setText(datelist.get(i).getTitle());
                    FishView.setBackgroundColor(Color.rgb(26,35,126));
                    FishView.setTextColor(Color.WHITE);
                    FishView.setTextSize(15);
                }
            }
        return convertView;

    }

}


