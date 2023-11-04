package com.example.prm392_finalproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_finalproject.DTOModels.Order_DTO;
import com.example.prm392_finalproject.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context mContext;
    private List<Order_DTO> mListOrder;

    private IClickItemListener mIClickItemListener;

    public interface IClickItemListener{
        void onClickItemOrder(Order_DTO order);
    }

    public OrderAdapter(Context mContext, IClickItemListener listener) {
        this.mContext = mContext;
        this.mIClickItemListener = listener;
    }

    public void setData(List<Order_DTO> list) {
        this.mListOrder = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rev_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order_DTO order = mListOrder.get(position);
        if (order == null) {
            return;
        }

        holder.tvOrderId.setText(order.getId() + "");

        if (order.getStatus() == 0) {
            holder.tvOrderStatus.setText("Đã đặt hàng");
            holder.tvOrderStatus.setTextColor(Color.parseColor("#80000000"));
            holder.layoutOrder.setBackgroundColor(Color.parseColor("#40000000"));
        } else if (order.getStatus() == 1) {
            holder.tvOrderStatus.setText("Đã xác nhận");
            holder.tvOrderStatus.setTextColor(Color.parseColor("#9C27B0"));
            holder.layoutOrder.setBackgroundColor(Color.parseColor("#409C27B0"));
        } else if (order.getStatus() == 2) {
            holder.tvOrderStatus.setText("Đang giao hàng");
            holder.tvOrderStatus.setTextColor(Color.parseColor("#3F51B5"));
            holder.layoutOrder.setBackgroundColor(Color.parseColor("#403F51B5"));
        } else if (order.getStatus() == 3) {
            holder.tvOrderStatus.setText("Đã giao hàng");
            holder.tvOrderStatus.setTextColor(Color.parseColor("#369C3A"));
            holder.layoutOrder.setBackgroundColor(Color.parseColor("#40369C3A"));
        } else if (order.getStatus() == 4) {
            holder.tvOrderStatus.setText("Đơn hàng bị hủy");
            holder.tvOrderStatus.setTextColor(Color.parseColor("#CC3125"));
            holder.layoutOrder.setBackgroundColor(Color.parseColor("#40CC3125"));
        }

        holder.tvOrderUsername.setText(order.getCustomerName());
        holder.tvOrderPhonenum.setText(order.getPhone());
        holder.tvOrderAddress.setText(order.getAddress());
        holder.tvOrderCost.setText(order.getPrice() + "");
        holder.cvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickItemListener.onClickItemOrder(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListOrder != null) {
            return mListOrder.size();
        }
        return 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOrderId, tvOrderStatus, tvOrderUsername, tvOrderPhonenum, tvOrderAddress, tvOrderCost;
        private CardView cvOrder;
        private LinearLayout layoutOrder;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
            tvOrderUsername = itemView.findViewById(R.id.tv_order_username);
            tvOrderPhonenum = itemView.findViewById(R.id.tv_order_phonenum);
            tvOrderAddress = itemView.findViewById(R.id.tv_order_address);
            tvOrderCost = itemView.findViewById(R.id.tv_order_cost);
            cvOrder = itemView.findViewById(R.id.cv_order);
            layoutOrder = itemView.findViewById(R.id.layout_order);
        }
    }
}
