package com.example.administrator.broadcastdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/29 0029.
 */

public class LoginActivity extends BaseActivity {

    private EditText account_editText;
    private EditText password_editText;
    private Button button;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private CheckBox checkBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        account_editText = (EditText) findViewById(R.id.account);
        password_editText = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        button = (Button) findViewById(R.id.button);
        boolean isRemember = sharedPreferences.getBoolean("remember_password",false);
        if (isRemember)
        {
            String account = sharedPreferences.getString("account","");
            String password = sharedPreferences.getString("password","");
            account_editText.setText(account);
            password_editText.setText(password);
            checkBox.setChecked(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = account_editText.getText().toString();
                String password = password_editText.getText().toString();
                if(account.equals("admin") && password.equals("123456"))
                {
                    editor = sharedPreferences.edit();
                    if(checkBox.isChecked())
                    {
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else
                    {
                        editor.clear();
                    }
                    editor.apply();
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
