package com.ue.proyecto_final.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ue.proyecto_final.MainActivity;
import com.ue.proyecto_final.R;

public class Profile extends AppCompatActivity {
    private Button salir;
    private Button atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pInicio();
        salir.setOnClickListener(this::appSalir);
        atras.setOnClickListener(this::apatras);
    }

    public void pInicio() {
        salir = findViewById(R.id.Btn_salir2);
        atras = findViewById(R.id.Btn_Atras);
    }

    public void appSalir(View view) {
        Intent intent = new Intent(Profile.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void apatras(View view) {
        Intent intent = new Intent(Profile.this, Principal.class);
        startActivity(intent);
        finish();
    }
}