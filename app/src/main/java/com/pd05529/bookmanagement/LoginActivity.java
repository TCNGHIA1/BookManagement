package com.pd05529.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pd05529.bookmanagement.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout tilUser, tilPass;
    Button btnCancel, btnLogin;
    CheckBox chkRemember;
    public ThuThuDAO dao;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //mapping
        tilUser = findViewById(R.id.tilUser);
        tilPass = findViewById(R.id.tilPass);
        btnCancel = findViewById(R.id.btnClose);
        btnLogin = findViewById(R.id.btnLogin);
        chkRemember = findViewById(R.id.chkRemember);
        //Lay du lieu thu thu
        dao = new ThuThuDAO(this);
        //Su kien

        pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        chkRemember.setChecked(pref.getBoolean("CHECK", false));
        tilUser.getEditText().setText(pref.getString("USERNAME", ""));
        tilPass.getEditText().setText(pref.getString("PASSWORD", ""));
        //Clear o nhap tai khoan va mat khau
        btnCancel.setOnClickListener(v -> {
            tilUser.getEditText().setText("");
            tilPass.getEditText().setText("");
        });
        //Dang nhap
        btnLogin.setOnClickListener(v -> {
            checkLogin();
        });
    }

    //Kiem tra tai khoan dang nhap
    public void checkLogin() {
        String strUser = tilUser.getEditText().getText().toString().trim();
        String strPass = tilPass.getEditText().getText().toString().trim();
        if (!validate(strUser, tilUser) || !validate(strPass, tilPass)) {
            return;
        }
        //Kiểm tra thủ thư trong CSDL
        if (dao.checkLogin(strUser, strPass) > 0) {
            //Lay quyen han nguoi dung
            int role = dao.getId(strUser).getRole();
            String name = dao.getId(strUser).getHoTen();
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            rememberUser(strUser, strPass, chkRemember.isChecked());
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            //Chuyền thông tin người quản lí
            Bundle bundle = new Bundle();
            bundle.putString("id", strUser);
            i.putExtras(bundle);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
        }
    }

    public void rememberUser(String strUser, String strPass, boolean checked) {
        pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!checked) {
            //xoa tinh trang luu tru
            editor.clear();
        } else {
            //luu du lieu
            editor.putString("USERNAME", strUser);
            editor.putString("PASSWORD", strPass);
            editor.putBoolean("CHECK", checked);
        }
        editor.commit();
    }

    public boolean validate(String text, TextInputLayout input) {
        if (text.isEmpty()) {
            input.setError("Không để trống tài khoản");
            return false;
        } else {
            input.setError("");
            return true;
        }
    }
}