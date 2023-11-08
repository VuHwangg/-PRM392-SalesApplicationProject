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

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message_DTO> messageList;
    private static final int VIEW_TYPE_SEND = 1;
    private static final int VIEW_TYPE_RECEIVE = 2;

    @Override
    public int getItemViewType(int position) {
        Message_DTO message = messageList.get(position);
        if (message.sendid.equals(String.valueOf(VIEW_TYPE_SEND))) {
            return VIEW_TYPE_SEND;
        } else {
            return VIEW_TYPE_RECEIVE;
        }
    }

    public MessageAdapter(List<Message_DTO> messageList) {
        this.messageList = messageList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_SEND) {
            view = inflater.inflate(R.layout.item_sendmessage, parent, false);
            return new SendMessViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.item_received, parent, false);
            return new ReceivedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message_DTO message = messageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_SEND:
                SendMessViewHolder sendHolder = (SendMessViewHolder) holder;
                sendHolder.txtmess.setText(message.mess);
                sendHolder.txttime.setText(message.DateTime);
                break;
            case VIEW_TYPE_RECEIVE:
                ReceivedViewHolder receivedHolder = (ReceivedViewHolder) holder;
                receivedHolder.txtmess.setText(message.mess);
                receivedHolder.txttime.setText(message.DateTime);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class SendMessViewHolder extends  RecyclerView.ViewHolder{
        TextView txtmess, txttime;
        public SendMessViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmess = itemView.findViewById(R.id.txtmesssend);
            txttime = itemView.findViewById(R.id.txttimesend);
        }
    }
    class ReceivedViewHolder extends  RecyclerView.ViewHolder{
        TextView txtmess, txttime;
        public ReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmess = itemView.findViewById(R.id.txtmessreceived);
            txttime = itemView.findViewById(R.id.txttimereceived);
        }
    }
}
