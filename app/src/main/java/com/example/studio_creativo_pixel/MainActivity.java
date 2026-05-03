package com.example.studio_creativo_pixel;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.studio_creativo_pixel.fragments.CatalogFragment;
import com.example.studio_creativo_pixel.fragments.CustomizeFragment;
import com.example.studio_creativo_pixel.fragments.HomeFragment;
import com.example.studio_creativo_pixel.fragments.ServicesFragment;
import com.example.studio_creativo_pixel.models.CartManager;
import com.example.studio_creativo_pixel.models.Product;
import com.example.studio_creativo_pixel.adapters.CartAdapter;
import com.google.android.material.navigation.NavigationView;
import android.content.Intent;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private CartAdapter cartAdapter;
    private TextView txtSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Setup Cart
        RecyclerView rvCart = findViewById(R.id.rv_cart);
        txtSubtotal = findViewById(R.id.txt_cart_subtotal);
        if (rvCart != null) {
            rvCart.setLayoutManager(new LinearLayoutManager(this));
            cartAdapter = new CartAdapter(CartManager.getInstance().getCartItems(), this::updateCartUI);
            rvCart.setAdapter(cartAdapter);
        }

        ImageButton btnCart = findViewById(R.id.btn_cart);
        if (btnCart != null) {
            btnCart.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.END));
        }

        View btnAccount = findViewById(R.id.btn_account);
        if (btnAccount != null) {
            btnAccount.setOnClickListener(v -> {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            });
        }

        View toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        }

        View btnCheckout = findViewById(R.id.btn_checkout);
        if (btnCheckout != null) {
            btnCheckout.setOnClickListener(v -> {
                Intent intent = new Intent(this, CheckoutActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.END);
            });
        }

        // Load Home by default
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_home);
        }
        
        updateCartUI();
    }

    public void updateCartUI() {
        if (cartAdapter != null) cartAdapter.notifyDataSetChanged();
        if (txtSubtotal != null) {
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            nf.setMaximumFractionDigits(0);
            String subtotalText = "Items: " + CartManager.getInstance().getItemCount() + 
                                 "\nTotal: " + nf.format(CartManager.getInstance().getSubtotal());
            txtSubtotal.setText(subtotalText);
        }
    }

    public void addProductToCart(Product product) {
        CartManager.getInstance().addProduct(product);
        updateCartUI();
        drawerLayout.openDrawer(GravityCompat.END);
    }

    public void navigateToCustomize(Product product) {
        loadFragment(CustomizeFragment.newInstance(product.getName(), product.getImageResId()));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            loadFragment(new HomeFragment());
        } else if (id == R.id.nav_services) {
            loadFragment(new ServicesFragment());
        } else if (id == R.id.nav_catalog) {
            loadFragment(new CatalogFragment());
        } else if (id == R.id.nav_customize) {
            loadFragment(new CustomizeFragment());
        } else if (id == R.id.nav_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
}
