package com.example.prm392_finalproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.DTOModels.Message_DTO;
import com.example.prm392_finalproject.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.SendMessViewHolder>{
   List<Message_DTO> list;

    public ChatAdapter(List<Message_DTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SendMessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sendmessage,parent,false);
        return new SendMessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SendMessViewHolder holder, int position) {
        Message_DTO message =list.get(position);
        SendMessViewHolder sendHolder = (SendMessViewHolder) holder;
        sendHolder.txtmess.setText(message.mess);
        sendHolder.txttime.setText(message.DateTime);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SendMessViewHolder extends  RecyclerView.ViewHolder{
        TextView txtmess, txttime;
        public SendMessViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmess = itemView.findViewById(R.id.txtmesssend);
            txttime = itemView.findViewById(R.id.txttimesend);
        }
    }
}
