package com.ue.proyecto_final.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ue.proyecto_final.MainActivity;
import com.ue.proyecto_final.R;

public class Principal extends AppCompatActivity {
    private Button salir;
    private Button verPerfil;
    private TextView bienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        pInicio();
        verNombre();
        salir.setOnClickListener(this::appSalir);
        verPerfil.setOnClickListener(this::vPerfil);
    }

    public void pInicio() {
        salir = findViewById(R.id.Btn_salir);
        verPerfil = findViewById(R.id.Btn_ver_perfil);
        bienvenido = findViewById(R.id.bienvenido);
    }

    public void appSalir(View view) {
        Intent intent = new Intent(Principal.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void vPerfil(View view) {
        Intent intent = new Intent(Principal.this, Profile.class);
        startActivity(intent);
        finish();
    }

    public void verNombre(){
        String nombre = getIntent().getStringExtra("name");
        bienvenido.setText("Bienvenido" + nombre);
    }
}