package com.example.treytontheoassign;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.ComboViewHolder>{

    private List<Combo> comboList;
    private Drawable[] drawables;

    public ComboAdapter(List<Combo> comboList, Drawable[] drawables) {
        this.comboList = comboList;
        this.drawables = drawables;
    }

    @NonNull
    @Override
    public ComboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.combo_item, parent, false);
        return new ComboViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComboViewHolder holder, int position) {
        holder.tvComboName.setText(comboList.get(position).getComboName());

        for (int i = 0; i < holder.imageViewList.size(); i++){
            holder.imageViewList.get(i).setImageDrawable(null);
        }

        for (int i = 0 ; i < comboList.get(position).getComboItems().size(); i++){
            holder.imageViewList.get(i).setImageDrawable(drawables[comboList.get(position).getComboItems().get(i)]);
        }





        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), TestActivity.class);
            intent.putExtra("combo", comboList.get(position));
            v.getContext().startActivity(intent);
            
        });

    }

    @Override
    public int getItemCount() {
        return comboList.size();
    }

    public static class ComboViewHolder extends RecyclerView.ViewHolder {

        TextView tvComboName;
        List<ImageView> imageViewList = new ArrayList<>();


        public ComboViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvComboName = itemView.findViewById(R.id.tvComboName);
            this.imageViewList.add(itemView.findViewById(R.id.imageView0));
            this.imageViewList.add(itemView.findViewById(R.id.imageView1));
            this.imageViewList.add(itemView.findViewById(R.id.imageView2));
            this.imageViewList.add(itemView.findViewById(R.id.imageView3));
            this.imageViewList.add(itemView.findViewById(R.id.imageView4));
            this.imageViewList.add(itemView.findViewById(R.id.imageView5));
            this.imageViewList.add(itemView.findViewById(R.id.imageView6));
            this.imageViewList.add(itemView.findViewById(R.id.imageView7));

        }
    }

}
