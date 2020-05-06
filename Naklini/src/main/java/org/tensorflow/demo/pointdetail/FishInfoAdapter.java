package org.tensorflow.demo.pointdetail;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.tensorflow.demo.R;

import java.util.ArrayList;


public class FishInfoAdapter extends RecyclerView.Adapter<FishInfoAdapter.ViewHolder>{
    Context mContext;
    int res_id;
    ArrayList<Species> speciesList;

    public FishInfoAdapter(Context mContext, int res_id, ArrayList<Species> speciesList) {
        this.mContext = mContext;
        this.res_id = res_id;
        this.speciesList = speciesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(res_id,null);
        //view.setLayoutParams(new RecyclerView.LayoutParams(1080,800));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView img = holder.img;
        TextView name = holder.name;
        TextView distribution = holder.distribution;
        TextView living = holder.living;
        TextView size = holder.size;
        // URL이미지 출력
        final Bitmap[] bitmap = new Bitmap[1];
        final Drawable URL_img;

        // TODO: 이미지 출력 ======================================================
        Glide.with(this.mContext).
                load(speciesList.get(position).picture)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());

        // TODO: 이미지 제외하고 출력 ==========================================================
        if(speciesList.get(position)._id == -1){
            name.setText(speciesList.get(position).name);
            distribution.setText("정보가 없습니다.");
            living.setText("정보가 없습니다.");
            size.setText("정보가 없습니다.");
        }else{
            name.setText(speciesList.get(position).name);
            if(speciesList.get(position).dist == null)
                distribution.setText("정보가 없습니다.");
            else
                distribution.setText(speciesList.get(position).dist);

            if(speciesList.get(position).living == null)
                living.setText("정보가 없습니다.");
            else
                living.setText(speciesList.get(position).living);

            if(speciesList.get(position).size == null)
                size.setText("정보가 없습니다.");
            else
                size.setText(speciesList.get(position).size);
        }
    }

    @Override
    public int getItemCount() {
        return speciesList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView distribution;
        TextView living;
        TextView size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.fish_img);
            name = itemView.findViewById(R.id.fishName);
            distribution = itemView.findViewById(R.id.distribution);
            living = itemView.findViewById(R.id.living);
            size = itemView.findViewById(R.id.size);
        }

        public ImageView getImage(){ return this.img;}
    }

}


