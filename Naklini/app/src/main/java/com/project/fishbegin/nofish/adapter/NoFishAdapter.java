package com.project.fishbegin.nofish.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.fishbegin.R;
import com.project.fishbegin.nofish.item.Calendar_data;
import com.project.fishbegin.nofish.item.Date_data;
import com.project.fishbegin.nofish.item.Detail_data;

import java.util.ArrayList;
import java.util.List;

public class NoFishAdapter extends BaseAdapter implements View.OnClickListener {
    Context mContext;
    final ArrayList<Calendar_data> calList;
    final ArrayList<Date_data> datelist;
    LinearLayout Fish_data_cell;
    TextView DayView;
    TextView FishView;
    RecyclerView DetailView;


    public NoFishAdapter(Context context,ArrayList<Calendar_data> list, ArrayList<Date_data> dateList){
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
                    DayView.setTextColor(Color.RED);
                    FishView.setText(datelist.get(i).getTitle());

                }
                if(nowmonth.equals(datelist.get(i).getFinish_month())&&nowday.equals(datelist.get(i).getFinish_day())){
                    DayView.setTextColor(Color.BLUE);
                    FishView.setText(datelist.get(i).getTitle());
                }
            }
        return convertView;

    }

}


