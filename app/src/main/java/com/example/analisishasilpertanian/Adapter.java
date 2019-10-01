package com.example.analisishasilpertanian;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.analisishasilpertanian.user.DetailActivity;

import java.util.ArrayList;

import static com.example.analisishasilpertanian.BitmapConverter.StringToBitMap;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<ModelAnalysis> analyses = new ArrayList<>();
    private ArrayList<ModelInformation> informations = new ArrayList<>();
    private String type=null;


    public void setAnalyses(ArrayList<ModelAnalysis> analyses, String type) {
        this.analyses.clear();
        this.analyses.addAll(analyses);
        notifyDataSetChanged();
        this.type=type;
        Log.d("asd","aaa");
    }

    public void setInformations(ArrayList<ModelInformation> informations, String type) {
        this.informations.clear();
        this.informations.addAll(informations);
        notifyDataSetChanged();
        this.type=type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (type.equals("MODELINFORMATION")){
            Log.d("asd","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            holder.tv1.setText(informations.get(position).getName());
            holder.tv2.setText(informations.get(position).getInformation());
            Glide.with(holder.itemView.getContext()).load(informations.get(position).getImage()).into(holder.image);
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.DATA,informations.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            });
        }else {
            Log.d("asd","aaaaaaaaaaaaa");
            holder.tv1.setText(analyses.get(position).getName());
            holder.tv2.setText(analyses.get(position).getCommodity());

            Glide.with(holder.itemView.getContext()).load(StringToBitMap(analyses.get(position).getImage())).into(holder.image);
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(holder.itemView.getContext(),DetailActivity.class);
                intent.putExtra(DetailActivity.DATA,analyses.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            });
        }

    }

    @Override
    public int getItemCount() {
        if (type=="MODELINFORMATION"){
            return informations.size();
        }else {
            return analyses.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1, tv2;
        private ImageView image;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1= itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            image=itemView.findViewById(R.id.image);
        }
    }
}
