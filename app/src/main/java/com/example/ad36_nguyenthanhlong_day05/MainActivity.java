package com.example.ad36_nguyenthanhlong_day05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText edUser, edPass;
    Button btLogin,btSignUp;
    int kq1 = 0;
    int kq2 = 0;
    int kq3 = 0;
    int kq4 = 0;

    SQLite sqLite;
    List<Account> accountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLite = new SQLite(getBaseContext());

        sqLite.InsertAccount("Long1","Long123@");
        sqLite.InsertAccount("Long2","Long123!");

        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPassword);
        btLogin = findViewById(R.id.btLogin);
        btSignUp = findViewById(R.id.btSignUp);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),Creat_Account.class);
                startActivity(intent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edUser.getText().toString();
                String pass = edPass.getText().toString();

                String dk1 = "qwertyuiopasdfghjklzxcvbnm";
                String dk2 = dk1.toUpperCase();
                String dk3 = "0123456789";
                String dk4 = "!@#$%^&*()_+-=/";

                if (pass.length() >= 6) {
                    for (int i = 0; i < pass.length(); i++) {
                        if (dk1.contains(String.valueOf(pass.charAt(i)))) {
                            kq1++;
                        }
                        if (dk2.contains(String.valueOf(pass.charAt(i)))) {
                            kq2++;
                        }
                        if (dk3.contains(String.valueOf(pass.charAt(i)))) {
                            kq3++;
                        }
                        if (dk4.contains(String.valueOf(pass.charAt(i)))) {
                            kq4++;
                        }
                    }
                }


                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getBaseContext(), "User or Pass Null", Toast.LENGTH_LONG).show();
                } else if (kq1 > 0 && kq2 > 0 && kq3 > 0 && kq4 > 0) {

                    accountList = sqLite.getAllAccountAdvanced();
                    int check = 0;
                    for (Account i:accountList){
                        if (i.pass.equals(pass) && i.user.equals(user)){
                            check++;
                            Toast.makeText(getBaseContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                    if (check==0) Toast.makeText(getBaseContext(), "Tài khoản mật khẩu không đúng", Toast.LENGTH_LONG).show();

                } else Toast.makeText(getBaseContext(), "Đăng nhập thất bại", Toast.LENGTH_LONG).show();


            }
        });
    }
}
