package com.example.treytontheoassign;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.ComboViewHolder> {

    private final Context context;
    private final List<Combo> comboList;

    public ComboAdapter(Context context, List<Combo> comboList) {
        this.context = context;
        this.comboList = comboList;
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

        for (int i = 0; i < holder.imageViewList.size(); i++) {
            holder.imageViewList.get(i).setImageDrawable(null);
        }

        for (int i = 0; i < comboList.get(position).getComboItems().size(); i++) {
            Utils.setImageBaseOnValue(holder.imageViewList.get(i), comboList.get(position).getComboItems().get(i));

        }

        if (comboList.get(position).isAttempted()) {
            if (comboList.get(position).isCorrect()) {
//                    holder.imageViewList.get(i).setBackgroundColor(R.color.success_green);
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.success_green, context.getTheme()));
            } else {
//                    holder.imageViewList.get(i).setBackgroundColor(R.color.failed_red);
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.failed_red, context.getTheme()));
            }

        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.teal_700, context.getTheme()));
        }
//        holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.red, context.getTheme()));


        holder.itemView.setOnClickListener(v -> {
            if (!comboList.get(position).isAttempted()) {
                comboList.get(position).setAttempted(true);
                Intent intent = new Intent(v.getContext(), TestActivity.class);
                intent.putExtra("combo", comboList.get(position));
                v.getContext().startActivity(intent);
                ((MainActivity) v.getContext()).finish();
            } else {
                Toast toast = Toast.makeText(v.getContext(), "You have already attempted this combo", Toast.LENGTH_SHORT);
                toast.show();
            }


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
