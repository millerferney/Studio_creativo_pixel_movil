package com.example.studio_creativo_pixel.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.studio_creativo_pixel.CheckoutActivity;
import com.example.studio_creativo_pixel.MainActivity;
import com.example.studio_creativo_pixel.R;
import com.example.studio_creativo_pixel.models.Product;
import com.google.android.material.button.MaterialButton;

public class CustomizeFragment extends Fragment {

    private int quantity = 1;
    private TextView txtQuantity;
    private String productName;
    private int productImageRes;

    public static CustomizeFragment newInstance(String name, int imageRes) {
        CustomizeFragment fragment = new CustomizeFragment();
        Bundle args = new Bundle();
        args.putString("product_name", name);
        args.putInt("product_image", imageRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productName = getArguments().getString("product_name");
            productImageRes = getArguments().getInt("product_image");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customize, container, false);

        txtQuantity = view.findViewById(R.id.txt_quantity);
        ImageView imgPreview = view.findViewById(R.id.img_preview);
        TextView txtTitle = view.findViewById(R.id.txt_customize_title);
        
        TextView txtStepSizeTitle = view.findViewById(R.id.txt_step_size_title);

        if (productName != null && txtTitle != null) {
            txtTitle.setText(productName.toUpperCase());
            if (productName.contains("Taza") || productName.contains("Llavero") || productName.contains("Botella")) {
                if (txtStepSizeTitle != null) txtStepSizeTitle.setText("PASO 02: TIPO / MATERIAL");
            }
        }

        if (productImageRes != 0 && imgPreview != null) {
            imgPreview.setImageResource(productImageRes);
        }

        MaterialButton btnPlus = view.findViewById(R.id.btn_plus);
        MaterialButton btnMinus = view.findViewById(R.id.btn_minus);
        MaterialButton btnAddToCart = view.findViewById(R.id.btn_add_to_cart_final);
        MaterialButton btnBuyNow = view.findViewById(R.id.btn_buy_now);
        MaterialButton btnUpload = view.findViewById(R.id.btn_upload);

        if (btnPlus != null) {
            btnPlus.setOnClickListener(v -> {
                quantity++;
                updateQuantity();
            });
        }

        if (btnMinus != null) {
            btnMinus.setOnClickListener(v -> {
                if (quantity > 1) {
                    quantity--;
                    updateQuantity();
                }
            });
        }

        if (btnUpload != null) {
            btnUpload.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Cargando archivo para " + productName + "...", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnAddToCart != null) {
            btnAddToCart.setOnClickListener(v -> {
                addToCart();
                Toast.makeText(getContext(), productName + " añadido al carrito", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnBuyNow != null) {
            btnBuyNow.setOnClickListener(v -> {
                addToCart();
                Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                startActivity(intent);
            });
        }

        return view;
    }

    private void addToCart() {
        if (getActivity() instanceof MainActivity) {
            // Price is hardcoded here for simplicity, in a real app it should be passed in or looked up
            double price = 38000; // default
            if (productName != null) {
                if (productName.contains("Hoodie")) price = 95000;
                else if (productName.contains("Gorra")) price = 25000;
                else if (productName.contains("Botella")) price = 45000;
                else if (productName.contains("Carcasa")) price = 32000;
                else if (productName.contains("Llavero")) price = 15000;
                else if (productName.contains("Esmerilada")) price = 42000;
            }
            
            for (int i = 0; i < quantity; i++) {
                ((MainActivity) getActivity()).addProductToCart(new Product(productName, price, productImageRes));
            }
        }
    }

    private void updateQuantity() {
        if (txtQuantity != null) {
            txtQuantity.setText(String.valueOf(quantity));
        }
    }
}
