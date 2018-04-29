package com.example.administrator.broadcastdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/29 0029.
 */

public class LoginActivity extends BaseActivity {

    private EditText account_editText;
    private EditText password_editText;
    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account_editText = (EditText) findViewById(R.id.account);
        password_editText = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = account_editText.getText().toString();
                String password = password_editText.getText().toString();
                if(account.equals("admin") && password.equals("123456"))
                {
                    Intent in = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(in);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"account or password is invaild.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
