package com.example.prm392_finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392_finalproject.DTOModels.Cart_Product_DTO;
import com.example.prm392_finalproject.R;
import com.example.prm392_finalproject.Singleton.CartSingleton;

import java.util.List;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private Context mContext;
    private List<Cart_Product_DTO> mListProduct;

    private TextView tvTotalCost;

    public CartAdapter(Context mContext, TextView tvTotalCost) {
        this.mContext = mContext;
        this.tvTotalCost = tvTotalCost;
    }

    public void setData(List<Cart_Product_DTO> list) {
        this.mListProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rev_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart_Product_DTO product = mListProduct.get(position);
        if (product == null) {
            return;
        }
        Glide.with(holder.itemView)
                .load(product.getImage())
                .centerCrop()
                .into(holder.imgProduct);
        holder.tvName.setText(product.getName());

        holder.tvPrice.setText((int) product.getPrice() + " VNĐ");
        holder.tvQuantity.setText(String.valueOf((int) product.getQuantity()));
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCart())
                {
                    if(cart_product_dto.getId()== product.getId())
                    {
                        if(cart_product_dto.getQuantity()==1)
                        {
                            CartSingleton.getInstance().getCart().remove(cart_product_dto);
                            for(Cart_Product_DTO cart_product_dto1 : CartSingleton.getInstance().getCartSelected()){
                                if(cart_product_dto1.getId() == product.getId()){
                                    CartSingleton.getInstance().getCartSelected().remove(cart_product_dto1);
                                    break;
                                }
                            }
                        }
                        else
                        {
                            cart_product_dto.setQuantity(cart_product_dto.getQuantity()-1);
                            for(Cart_Product_DTO cart_product_dto1 : CartSingleton.getInstance().getCartSelected()){
                                if (cart_product_dto1.getId() == product.getId()){
                                    cart_product_dto1.setQuantity(cart_product_dto1.getQuantity()-1);
                                    break;
                                }
                            }
                        }
                        notifyDataSetChanged();
                        tvTotalCost.setText(String.valueOf((int) getTotalCost()));
                        break;
                    }
                }
            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCart())
                {
                    if(cart_product_dto.getId()== product.getId())
                    {
                        cart_product_dto.setQuantity(cart_product_dto.getQuantity()+1);
                        notifyDataSetChanged();
                        for(Cart_Product_DTO cart_product_dto1 : CartSingleton.getInstance().getCartSelected()){
                            if (cart_product_dto1.getId() == product.getId()){
                                cart_product_dto1.setQuantity(cart_product_dto1.getQuantity()+1);
                                tvTotalCost.setText(String.valueOf((int) getTotalCost()));
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCart())
                {
                    if(cart_product_dto.getId()== product.getId())
                    {
                        CartSingleton.getInstance().getCart().remove(cart_product_dto);
                        notifyDataSetChanged();
                        for(Cart_Product_DTO cart_product_dto1 : CartSingleton.getInstance().getCartSelected()){
                            if(cart_product_dto1.getId() == product.getId()){
                                CartSingleton.getInstance().getCartSelected().remove(cart_product_dto1);
                                tvTotalCost.setText(String.valueOf((int) getTotalCost()));
                                break;
                            }
                        }
                        Toast.makeText(mContext, "Sản phẩm đã bị xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    CartSingleton.getInstance().getCartSelected().add(product);
                    tvTotalCost.setText(String.valueOf((int) getTotalCost()));
                }else{
                    for(Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCartSelected()){
                        if(cart_product_dto.getId() == product.getId()){
                            CartSingleton.getInstance().getCartSelected().remove(cart_product_dto);
                            break;
                        }
                    }
                    tvTotalCost.setText(String.valueOf((int) getTotalCost()));
                }
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

    public double getTotalCost(){
        double totalCost = 0;
        for(Cart_Product_DTO cart_product_dto : CartSingleton.getInstance().getCartSelected()){
            totalCost+=cart_product_dto.getPrice()* cart_product_dto.getQuantity();
        }
        return totalCost;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProduct;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvQuantity;
        private Button btnMinus, btnPlus, btnDelete;
        private CheckBox checkBox;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_productcart);
            tvName = itemView.findViewById(R.id.tv_name_productcart);
            tvPrice = itemView.findViewById(R.id.tv_price_productcart);
            tvQuantity = itemView.findViewById(R.id.tv_quantity_productcart);
            btnMinus = itemView.findViewById(R.id.btn_minus_quantity);
            btnPlus = itemView.findViewById(R.id.btn_plus_quantity);
            btnDelete = itemView.findViewById(R.id.btn_delete_cart);
            checkBox = itemView.findViewById(R.id.itemSelected);
        }
    }
}
