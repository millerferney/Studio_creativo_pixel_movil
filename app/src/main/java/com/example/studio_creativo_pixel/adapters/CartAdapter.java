package com.example.studio_creativo_pixel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.studio_creativo_pixel.R;
import com.example.studio_creativo_pixel.models.CartManager;
import com.example.studio_creativo_pixel.models.Product;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<Product> items;
    private OnCartChangedListener listener;

    public interface OnCartChangedListener {
        void onCartChanged();
    }

    public CartAdapter(List<Product> items, OnCartChangedListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = items.get(position);
        holder.txtName.setText(product.getName());
        
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        nf.setMaximumFractionDigits(0);
        holder.txtPrice.setText(nf.format(product.getPrice()));
        
        holder.imgProduct.setImageResource(product.getImageResId());
        
        holder.btnRemove.setOnClickListener(v -> {
            CartManager.getInstance().removeProduct(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            if (listener != null) listener.onCartChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtName, txtPrice;
        ImageButton btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_cart_item);
            txtName = itemView.findViewById(R.id.txt_cart_item_name);
            txtPrice = itemView.findViewById(R.id.txt_cart_item_price);
            btnRemove = itemView.findViewById(R.id.btn_remove_item);
        }
    }
}
