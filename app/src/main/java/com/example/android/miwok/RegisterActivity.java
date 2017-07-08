package com.example.android.miwok;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityApi {
    private Button registerButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private RegisterActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = (Button) findViewById(R.id.register_btn);
        emailEditText = (EditText) findViewById(R.id.register_email);
        passwordEditText = (EditText) findViewById(R.id.register_password);
        presenter = new RegisterActivityPresenter(this, this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signUp();
            }
        });
    }

    @Override
    public String getEmailText() {
        return emailEditText.getText().toString().trim();
    }

    @Override
    public String getPasswordText() {
        return passwordEditText.getText().toString().trim();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}