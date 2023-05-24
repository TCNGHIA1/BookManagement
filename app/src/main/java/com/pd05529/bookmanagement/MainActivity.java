package com.pd05529.bookmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.pd05529.bookmanagement.DAO.ThuThuDAO;
import com.pd05529.bookmanagement.model.ThuThu;
import com.pd05529.bookmanagement.ui.DoanhThuFragment;
import com.pd05529.bookmanagement.ui.DoiPassFragment;
import com.pd05529.bookmanagement.ui.LoaiSachFragment;
import com.pd05529.bookmanagement.ui.PhieuMuonFragment;
import com.pd05529.bookmanagement.ui.SachFragment;
import com.pd05529.bookmanagement.ui.ThanhVienFragment;
import com.pd05529.bookmanagement.ui.ThuThuFragment;
import com.pd05529.bookmanagement.ui.Top10Fragment;


public class MainActivity extends AppCompatActivity {
    DrawerLayout layout;
    Toolbar toolbar;
    NavigationView nv;
    View mHeaderView;
    SharedPreferences pref;
    TextView tvHeaderId,tvHeaderName,tvHeaderRole;
    public ThuThuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        dao = new ThuThuDAO(getApplicationContext());

        final MainActivity current = this;
        //Thay toolbar cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        //
        nv = findViewById(R.id.nav_view);
        //maping headerView
        mHeaderView = nv.getHeaderView(0);
        tvHeaderId = mHeaderView.findViewById(R.id.tvHeaderId);
        tvHeaderName = mHeaderView.findViewById(R.id.tvHeaderName);
        tvHeaderRole = mHeaderView.findViewById(R.id.tvHeaderRole);
        Bundle bundle = getIntent().getExtras();
        //Thông tin quản lí
        ThuThu obj = dao.getId(bundle.getString("id"));
        //Đổ thông tin lên header
        tvHeaderId.setText("Mã thủ thư: "+ obj.getMaTT());
        tvHeaderName.setText(obj.getHoTen());
        //Quyền hạn của quản lí
        if(obj.getRole() == 0){
            tvHeaderRole.setText("Admin");
            nv.getMenu().findItem(R.id.nav_manager).setVisible(true);
        }else{
            tvHeaderRole.setText("Thủ Thư");
            nv.getMenu().findItem(R.id.nav_manager).setVisible(false);
        }

        //Lam viec voi NavigationView

        nv.setNavigationItemSelectedListener((item -> {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction fragment = manager.beginTransaction();
            switch (item.getItemId()){
                case R.id.nav_home:
                {
                    setTitle("Quản lý phiếu mượn");
                    PhieuMuonFragment new_fragment = new PhieuMuonFragment();
                    fragment.replace(R.id.fragment,new_fragment).commit();
                    break;
            }
                case R.id.nav_loaisach:
                {
                    setTitle("Quản lý loại sách");
                    LoaiSachFragment new_fragment = new LoaiSachFragment();
                    fragment.replace(R.id.fragment,new_fragment).commit();

                    break;
                }
                case R.id.nav_sach:
                {
                    setTitle("Quản lý sách");
                    SachFragment new_fragment = new SachFragment();
                    fragment.replace(R.id.fragment,new_fragment).commit();
                    break;
                }
                case R.id.nav_user:
                {
                    setTitle("Quản lý thành viên");
                    ThanhVienFragment new_fragment = new ThanhVienFragment();
                    fragment.replace(R.id.fragment,new_fragment).commit();
                    break;
                }
                case R.id.nav_doanhthu:
                {
                    setTitle("Quản lý doanh thu");
                    DoanhThuFragment new_fragment = new DoanhThuFragment();
                    fragment.replace(R.id.fragment,new_fragment).commit();

                    break;
                }
                case R.id.nav_top:
                {
                    setTitle("Top sách bán chạy");
                    Top10Fragment new_fragment = new Top10Fragment();
                    fragment.replace(R.id.fragment,new_fragment).commit();
                    break;
                }
                case R.id.nav_manager:
                {
                    setTitle("Quản lý thủ thư");
                    ThuThuFragment new_fragment = new ThuThuFragment();
                    fragment.replace(R.id.fragment,new_fragment).commit();

                    break;
                }
                case R.id.nav_changePass:{
                    setTitle("Đổi mật khẩu");
                    DoiPassFragment new_fragment = new DoiPassFragment();
                    fragment.replace(R.id.fragment,new_fragment).commit();
                    break;
                }
                case R.id.nav_LogOut:
                {
                    pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.clear();
                    editor.commit();
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                    break;
                }
            }
            layout.closeDrawers();
            return false;
        }));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            layout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}