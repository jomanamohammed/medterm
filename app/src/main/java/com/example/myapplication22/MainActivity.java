
package com.example.myapplication22;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                validateInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        editEmail.addTextChangedListener(watcher);
        editPassword.addTextChangedListener(watcher);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this, ModeActivity.class);
            startActivity(intent);
        });

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {

                        if (backPressedTime + 2000 >
                                System.currentTimeMillis()) {
                            finish();
                        } else {
                            Snackbar.make(
                                    findViewById(R.id.mainLayout),
                                    "Press back again to exit",
                                    Snackbar.LENGTH_SHORT
                            ).show();

                            backPressedTime =
                                    System.currentTimeMillis();
                        }
                    }
                });
    }

    private void validateInputs() {

        String email =
                editEmail.getText().toString().trim();

        String password =
                editPassword.getText().toString();

        boolean validEmail =
                Patterns.EMAIL_ADDRESS.matcher(email).matches();

        boolean validPassword =
                password.length() >= 6;

        btnLogin.setEnabled(validEmail && validPassword);
    }
}
