package com.example.studio_creativo_pixel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studio_creativo_pixel.models.CartManager;
import java.text.NumberFormat;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        updateSummary();

        findViewById(R.id.btn_pay).setOnClickListener(v -> {
            v.setEnabled(false);
            Toast.makeText(this, "Validando transacción AES-256...", Toast.LENGTH_SHORT).show();
            
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Toast.makeText(this, "¡PAGO EXITOSO! Tu pedido está en producción (3-5 días).", Toast.LENGTH_LONG).show();
                CartManager.getInstance().clearCart();
                finish();
            }, 2500);
        });
    }

    private void updateSummary() {
        double subtotal = CartManager.getInstance().getSubtotal();
        double iva = subtotal * 0.19;
        double discount = subtotal * 0.05;
        double total = subtotal + iva - discount;

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        nf.setMaximumFractionDigits(0);

        TextView txtSubtotal = findViewById(R.id.val_subtotal);
        TextView txtIva = findViewById(R.id.val_iva);
        TextView txtDiscount = findViewById(R.id.val_discount);
        TextView txtTotal = findViewById(R.id.val_total);

        if (txtSubtotal != null) txtSubtotal.setText(nf.format(subtotal));
        if (txtIva != null) txtIva.setText(nf.format(iva));
        if (txtDiscount != null) {
            String discountStr = "-" + nf.format(discount);
            txtDiscount.setText(discountStr);
        }
        if (txtTotal != null) txtTotal.setText(nf.format(total));
    }
}
