package com.ue.proyecto_final.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ue.proyecto_final.MainActivity;
import com.ue.proyecto_final.R;
import com.ue.proyecto_final.modelo.ManageDB;

public class Registrer extends AppCompatActivity {

    private EditText eTDocu;
    private EditText eTName;
    private EditText eTLastname;
    private EditText eTUserN;
    private EditText eTPassw;
    private EditText eTrePassw;
    private Button btnRegister;
    private ManageDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        eTUserN=findViewById(R.id.eTUserName);
        eTPassw=findViewById(R.id.eTPassword);
        eTrePassw=findViewById(R.id.eTrePassword);
        eTDocu=findViewById(R.id.eTDocument);
        eTName=findViewById(R.id.eTNames);
        eTLastname=findViewById(R.id.eTLastNames);
        btnRegister=findViewById(R.id.btnRegister);
        DB= new ManageDB(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= eTUserN.getText().toString();
                String pass= eTPassw.getText().toString();
                String repass= eTrePassw.getText().toString();
                String document= eTDocu.getText().toString();
                String name= eTName.getText().toString();
                String lastname= eTLastname.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass) || TextUtils.isEmpty(document) || TextUtils.isEmpty(name) || TextUtils.isEmpty(lastname)){
                    Toast.makeText(Registrer.this, "Todos los campos requeridos", Toast.LENGTH_SHORT).show();
                }else{
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass,name,lastname,document);
                            if (insert==true){
                                Toast.makeText(Registrer.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("name",name);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(Registrer.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Registrer.this, "Usuario ya existente", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Registrer.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}