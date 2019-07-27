package com.example.ad36_nguyenthanhlong_day05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Creat_Account extends AppCompatActivity {
    EditText edDkUser,edDkPass,edConfirmPass;
    Button btCreat;
    SQLite sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat__account);

        edDkUser = findViewById(R.id.edDkUser);
        edDkPass = findViewById(R.id.edDkPass);
        edConfirmPass = findViewById(R.id.edConfDkPass);
        btCreat = findViewById(R.id.btCreat);

        sqLite = new SQLite(getBaseContext());

        final Intent intent = getIntent();

        btCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edDkUser.getText().toString();
                String pass = edDkPass.getText().toString();
                String confirmPass =edConfirmPass.getText().toString();

                String dk1 = "qwertyuiopasdfghjklzxcvbnm";
                String dk2 = dk1.toUpperCase();
                String dk3 = "0123456789";
                String dk4 = "!@#$%^&*()_+-=/";
                boolean kt = checkDkPass(pass,dk1,dk2,dk3,dk4);

                if (name.isEmpty()||pass.isEmpty()||confirmPass.isEmpty()){
                    Toast.makeText(getBaseContext(),"Tài khoản hoặc Mật khẩu rỗng",Toast.LENGTH_LONG).show();
                }else{
                    if (kt==true){
                        if (pass.equals(confirmPass)) {
                            sqLite.InsertAccount(name,pass);
                            finish();
                            Toast.makeText(getBaseContext(),"Successful",Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(getBaseContext(),"Nhập lại mật khẩu không khớp",Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(getBaseContext(),"Mật khẩu không thỏa mãn điều kiện",Toast.LENGTH_LONG).show();
                }

            }
        });




    }
    public static boolean checkDkPass(String pass, String dk1, String dk2, String dk3, String dk4){

        int kq1,kq2,kq3,kq4;
        kq1=kq2=kq3=kq4=0;

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
        boolean check;
        if (kq1>0&&kq2>0&&kq3>0&&kq4>0)
        {
            check = true;
        }else  check=false;
        return check;
    }
}
