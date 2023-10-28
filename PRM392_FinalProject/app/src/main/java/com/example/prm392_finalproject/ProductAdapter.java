package com.example.prm392_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context mContext;
    private List<Product> mListProduct;
    private IClickItemListener mIClickItemListener;
    public interface IClickItemListener{
        void onClickItemProduct(Product product);
    }

    public ProductAdapter(Context mContext, IClickItemListener listener) {
        this.mContext = mContext;
        this.mIClickItemListener = listener;
    }

    public void setData(List<Product> list) {
        this.mListProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rev_home, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if (product == null) {
            return;
        }
        holder.imgProduct.setImageResource(product.getImage());
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText((int) product.getPrice() + " VNĐ");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickItemListener.onClickItemProduct(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListProduct != null) {
            return mListProduct.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView tvName;
        private TextView tvPrice;
        CardView cardView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.img_product);
            tvName = itemView.findViewById(R.id.tv_name_product);
            tvPrice = itemView.findViewById(R.id.tv_price_product);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
