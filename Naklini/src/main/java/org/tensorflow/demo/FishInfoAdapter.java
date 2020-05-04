package org.tensorflow.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FishInfoAdapter extends RecyclerView.Adapter<FishInfoAdapter.ViewHolder>{
    Context mContext;
    int res_id;
    ArrayList<Fish_Detail_InfoList> infoList;

    public FishInfoAdapter(Context context, int row_res_id, ArrayList<Fish_Detail_InfoList> infoList){
        mContext = context;
        res_id = row_res_id;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(res_id,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView img = holder.img;
        TextView name = holder.name;
        TextView distribution = holder.distribution;
        TextView habitat = holder.habitat;

        if(infoList.get(position).getImg()!=null){
            Picasso.with(mContext).load(infoList.get(position).getImg()).fit().centerCrop().into(img);
        }
        name.setText(infoList.get(position).getFish_name());
        distribution.setText(infoList.get(position).getDistribution());
        habitat.setText(infoList.get(position).getHabitat());
    }

    @Override
    public int getItemCount() {
        return 50;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView distribution;
        TextView habitat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.fish_img);
            name = itemView.findViewById(R.id.fish_country_name);
            distribution = itemView.findViewById(R.id.distribution);
            habitat = itemView.findViewById(R.id.habitat);
        }
    }

}


