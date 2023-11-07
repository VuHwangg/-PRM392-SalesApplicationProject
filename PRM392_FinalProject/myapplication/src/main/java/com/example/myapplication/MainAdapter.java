package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter  extends  RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    private List<String> lst;
    private Context context;

    public MainAdapter(List<String> lst, Context context) {
        this.lst = lst;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        String string = lst.get(position);
        if(string== null){
            return;
        }
        holder.tv.setText(string);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sendid",string);
                intent.putExtras(bundle);
                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        if(lst != null){
            return lst.size();
        }
        return 0;
    }

    class  MainViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private ImageView imageView;
        private ConstraintLayout constraintLayout;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            constraintLayout = itemView.findViewById(R.id.layout1);
        }
    }
}
