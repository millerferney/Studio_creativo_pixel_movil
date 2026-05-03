package com.example.studio_creativo_pixel.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.studio_creativo_pixel.MainActivity;
import com.example.studio_creativo_pixel.R;
import com.example.studio_creativo_pixel.models.Product;
import java.text.NumberFormat;
import java.util.Locale;

public class CatalogFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);

        // Inyectar datos específicos a cada tarjeta incluida
        setupProduct(view.findViewById(R.id.product_1), new Product("Taza Pixel-Glow", 38000, R.drawable.tazapixel_glow));
        setupProduct(view.findViewById(R.id.product_2), new Product("Hoodie Tech-Spec", 95000, R.drawable.hoodietechspec));
        setupProduct(view.findViewById(R.id.product_3), new Product("Gorra Vector-Core", 25000, R.drawable.gorra_snapback));
        setupProduct(view.findViewById(R.id.product_4), new Product("Botella Tech-H2O", 45000, R.drawable.botella_aluminio));
        setupProduct(view.findViewById(R.id.product_5), new Product("Carcasa Pixel-Case", 32000, R.drawable.carcasa_iphone_14));
        setupProduct(view.findViewById(R.id.product_6), new Product("Llavero Acrílico", 15000, R.drawable.llavero_acrilco));
        setupProduct(view.findViewById(R.id.product_7), new Product("Taza Esmerilada", 42000, R.drawable.taza_esmerilada));

        return view;
    }

    private void setupProduct(View productView, Product product) {
        if (productView != null) {
            ImageView img = productView.findViewById(R.id.img_product);
            TextView name = productView.findViewById(R.id.txt_product_name);
            TextView price = productView.findViewById(R.id.txt_product_price);
            View btnPersonalize = productView.findViewById(R.id.btn_personalize);
            View btnAdd = productView.findViewById(R.id.btn_add_to_cart);

            if (img != null) img.setImageResource(product.getImageResId());
            if (name != null) name.setText(product.getName());
            
            // Formatear precio como $XX.XXX
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            nf.setMaximumFractionDigits(0);
            if (price != null) price.setText(nf.format(product.getPrice()));

            if (btnPersonalize != null) {
                btnPersonalize.setOnClickListener(v -> {
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).navigateToCustomize(product);
                    }
                });
            }

            if (btnAdd != null) {
                btnAdd.setOnClickListener(v -> {
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).addProductToCart(product);
                    }
                });
            }
        }
    }
}
