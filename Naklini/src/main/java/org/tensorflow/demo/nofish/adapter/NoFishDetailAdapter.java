package org.tensorflow.demo.nofish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tensorflow.demo.R;
import org.tensorflow.demo.nofish.item.Calendar_data;
import org.tensorflow.demo.nofish.item.Date_data;
import org.tensorflow.demo.nofish.item.Detail_data;

import java.util.ArrayList;

//import android.support.annotation.NonNull;

public class NoFishDetailAdapter extends RecyclerView.Adapter<NoFishDetailAdapter.ViewHolder> {
    Context mContext;
    int row_res_id;
    final ArrayList<Detail_data> detailList;
    ArrayList<Calendar_data> calList;
    ArrayList<Date_data> dateList;


    public NoFishDetailAdapter(Context context, int row_res_id,ArrayList<Detail_data> detailList){
        mContext =context;
        this.row_res_id = row_res_id;
        this.detailList = detailList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(row_res_id,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView title = holder.title;
        TextView start = holder.start;
        TextView finish = holder.finish;
        title.setText(detailList.get(position).getTitle());
        start.setText(detailList.get(position).getStart_month() + "월" + detailList.get(position).getStart_day() + "일");
        finish.setText(detailList.get(position).getFinish_month() + "월" + detailList.get(position).getFinish_day() + "일");


    }


    @Override
    public int getItemCount() {
        return detailList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView start;
        TextView finish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.detail_title);
            start = itemView.findViewById(R.id.detail_start);
            finish = itemView.findViewById(R.id.detail_stop);
        }
    }
}
