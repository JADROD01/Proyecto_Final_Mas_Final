package com.ue.proyecto_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ue.proyecto_final.modelo.ManageDB;
import com.ue.proyecto_final.vista.Principal;
import com.ue.proyecto_final.vista.Registrer;

import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity{

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private EditText username, password;
    private Button ingresar;
    private Button registrar;
    private ManageDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        huellaDig();
        username=findViewById(R.id.ET_User);
        password=findViewById(R.id.ET_Password);
        ingresar=findViewById(R.id.Btn_ingresar);
        registrar=findViewById(R.id.Btn_registrar);
        DB= new ManageDB(this);

        registrar.setOnClickListener(this::registrer);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=username.getText().toString();
                String pass=password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(MainActivity.this, "Falta rellenar los campos", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(MainActivity.this, "Ingreso exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Principal.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Ingreso fallido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void huellaDig(){
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(MainActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Principal.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();


        ImageButton biometricLoginButton = findViewById(R.id.biometric_login);
        biometricLoginButton.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }

    public void registrer(View view){
        Intent intent=new Intent(MainActivity.this,Registrer.class);
        startActivity(intent);
        finish();
    }
}