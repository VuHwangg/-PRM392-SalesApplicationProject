package com.example.prm392_finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.R;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder>{

    private Context mContext;
    private List<Cart_Product_DTO> mListProductOrder;

    public OrderDetailAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Cart_Product_DTO> list) {
        this.mListProductOrder = list;
        // Load du lieu vao Adapter
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rev_order_detail, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        Cart_Product_DTO productOrder = mListProductOrder.get(position);
        if (productOrder == null) {
            return;
        }
        Glide.with(holder.itemView)
                .load(productOrder.getImage())
                .centerCrop()
                .into(holder.imgProduct);
        holder.tvName.setText(productOrder.getName());
        holder.tvPrice.setText(productOrder.getPrice() +"");
        holder.tvQuantity.setText(productOrder.getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        if (mListProductOrder != null) {
            return mListProductOrder.size();
        }
        return 0;
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvPrice, tvQuantity;
        private ImageView imgProduct;

        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.img_productorder);
            tvName = itemView.findViewById(R.id.tv_name_productorder);
            tvPrice = itemView.findViewById(R.id.tv_price_productorder);
            tvQuantity = itemView.findViewById(R.id.tv_quantity_productorder);
        }
    }
}
