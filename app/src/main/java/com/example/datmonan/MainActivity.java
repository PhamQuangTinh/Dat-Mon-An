package com.example.datmonan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final int GIA_PIZZA = 150000;
    public final int GIA_DE_MONG = 5000;
    public final int GIA_DE_DAY = 8000;
    public final int GIA_DE_TRUYEN_THONG = 12000;
    public final int GIA_VIEN_PHO_MAI = 4000;
    public final int GIA_VIEN_XUC_XICH = 5000;
    public final int GIA_THEM_PHO_MAI = 10000;
    public final int GIA_GAP_2_PHO_MAI = 20000;
    public final int GIA_GAP_3_PHO_MAI = 10000;

    public final int GIA_THEM_PHO_MAI_1 = 15000;
    public final int GIA_THEM_THIT_BO = 35000;
    public final int GIA_THEM_TRUNG_OP_LA = 25000;

    public final int GIA_HAMBURGER = 45000;
    public final int GIA_BANH_MI_OP_LA = 25000;
    public final int GIA_BANH_MI_PHO_MAI = 50000;
    public final int GIA_BANH_MI_CHA_CA = 30000;

    public final double ABC = 0.2;
    public final double XYZ = 0.1;
    private CheckBox cbThemPhoMai1,cbGap2Phomai,cbGap3Phomai,cbThemPhoMai2,cbThemThitBo,cbThemTrungOpLa;

    private RadioButton rbDeMong,rbDeDay,rbDeTruyenThong,rbVienPhoMai,rbVienXucXich,rbBMOpLa,
            rbBMPhoMaiThitNguoi,rbBMChaCa;

    private EditText editMaGiamGia;

    private TextView txtGiamGia,txtTongTien,txtSoLuongPizza,txtSoLuongHamburger,txtSoLuongBanhMi;

    private Button btnRefresh,btnDatHang,btnGiamPizza,btnTangPizza,btnTangBanhMi,btnGiamBanhMi
            ,btnTangHamburger,btnGiamHamburger;

    private RadioGroup rgPizza1,rgPizza,rgBanhMi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        final int[] lastCheckDe = {0};

        final int[] lasCheckVien = {0};

        final int[] tongTien = {0};

        final int[] checkBanhMy = {GIA_BANH_MI_OP_LA};
        //Giảm số lượng hamburger
        btnGiamPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(txtSoLuongPizza.getText().toString());
                if(soLuong > 0){
                    txtSoLuongPizza.setText(String.valueOf(soLuong - 1));
                    int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                    txtTongTien.setText(String.valueOf(tongTien - (soLuong * GIA_PIZZA)+ ((soLuong - 1) * GIA_PIZZA)));
                }
            }
        });
        //Tăng số lượng hamburger
        btnTangPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(txtSoLuongPizza.getText().toString());
                if(soLuong < 100){
                    txtSoLuongPizza.setText(String.valueOf(soLuong + 1));
                    int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                    txtTongTien.setText(String.valueOf(tongTien - (soLuong * GIA_PIZZA)+ ((soLuong + 1) * GIA_PIZZA)));

                }
            }
        });

        //Thêm số lượng hamburger
        btnTangHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(txtSoLuongHamburger.getText().toString());
                if(soLuong < 100){
                    txtSoLuongHamburger.setText(String.valueOf(soLuong + 1));
                    int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                    txtTongTien.setText(String.valueOf(tongTien - (soLuong * GIA_HAMBURGER)+ ((soLuong + 1) * GIA_HAMBURGER)));

                }
            }
        });

        //Xóa số lượng hamburger
        btnGiamHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(txtSoLuongHamburger.getText().toString());

                if(soLuong > 0){
                    txtSoLuongHamburger.setText(String.valueOf(soLuong - 1));
                    int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                    txtTongTien.setText(String.valueOf(tongTien - (soLuong * GIA_HAMBURGER)+ ((soLuong - 1) * GIA_HAMBURGER)));
                }
            }
        });
        //Thêm số lượng bánh mì
        btnTangBanhMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(txtSoLuongBanhMi.getText().toString());
                if(soLuong < 100){
                    txtSoLuongBanhMi.setText(String.valueOf(soLuong + 1));
                    int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                    if(rbBMOpLa.isChecked()){
                        tongTien  = tongTien -(soLuong * checkBanhMy[0]) + ((soLuong + 1) * GIA_BANH_MI_OP_LA);
                    }
                    else if(rbBMChaCa.isChecked()){
                        tongTien  = tongTien -(soLuong * checkBanhMy[0]) + ((soLuong + 1) * GIA_BANH_MI_CHA_CA);
                    }
                    else if(rbBMPhoMaiThitNguoi.isChecked()){
                        tongTien  = tongTien -(soLuong * checkBanhMy[0]) + ((soLuong + 1) * GIA_BANH_MI_PHO_MAI);
                    }
                    txtTongTien.setText(String.valueOf(tongTien));

                }
            }
        });

        //Xóa số lượng bánh mì
        btnGiamBanhMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(txtSoLuongBanhMi.getText().toString());
                if(soLuong > 0){
                    txtSoLuongBanhMi.setText(String.valueOf(soLuong - 1));
                    int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                    if(rbBMOpLa.isChecked()){
                        tongTien  = tongTien -(soLuong * GIA_BANH_MI_OP_LA) + ((soLuong - 1) * GIA_BANH_MI_OP_LA);
                        checkBanhMy[0] = GIA_BANH_MI_OP_LA;
                    }
                    else if(rbBMChaCa.isChecked()){
                        tongTien  = tongTien -(soLuong * GIA_BANH_MI_CHA_CA) + ((soLuong - 1) * GIA_BANH_MI_CHA_CA);
                        checkBanhMy[0] = GIA_BANH_MI_CHA_CA;

                    }
                    else if(rbBMPhoMaiThitNguoi.isChecked()){
                        tongTien  = tongTien -(soLuong * GIA_BANH_MI_PHO_MAI) + ((soLuong - 1) * GIA_BANH_MI_PHO_MAI);                        checkBanhMy[0] = GIA_BANH_MI_OP_LA;
                        checkBanhMy[0] = GIA_BANH_MI_PHO_MAI;
                    }
                    txtTongTien.setText(String.valueOf(tongTien));

                }
            }
        });

        //Chọn De pizza
        rgPizza.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = rgPizza.findViewById(i);
                int index = rgPizza.indexOfChild(radioButton);
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                switch (index){
                    case 0:
                        if(lastCheckDe[0] > 0){
                            tongTien = tongTien - lastCheckDe[0] + GIA_DE_MONG;
                        }else{
                            tongTien = tongTien + GIA_DE_MONG;
                        }
                        lastCheckDe[0] = GIA_DE_MONG;
                        break;
                    case 1:
                        if(lastCheckDe[0] > 0){
                            tongTien = tongTien - lastCheckDe[0] + GIA_DE_DAY;
                        }else{
                            tongTien = tongTien + GIA_DE_DAY;
                        }
                        lastCheckDe[0] = GIA_DE_DAY;
                        break;

                    case 2:
                        if(lastCheckDe[0] > 0){
                            tongTien = tongTien - lastCheckDe[0] + GIA_DE_TRUYEN_THONG;
                        }else{
                            tongTien = tongTien + GIA_DE_TRUYEN_THONG;
                        }
                        lastCheckDe[0] = GIA_DE_TRUYEN_THONG;
                        break;
                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });

        //Chọn Vien Pizza
        rgPizza1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = rgPizza1.findViewById(i);
                int index = rgPizza1.indexOfChild(radioButton);
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                switch (index){
                    case 0:
                        if(lasCheckVien[0] > 0){
                            tongTien = tongTien - lasCheckVien[0] + GIA_VIEN_PHO_MAI;
                        }else{
                            tongTien = tongTien + GIA_VIEN_PHO_MAI;
                        }
                        lasCheckVien[0] = GIA_VIEN_PHO_MAI;
                        break;
                    case 1:
                        if(lasCheckVien[0] > 0){
                            tongTien = tongTien - lasCheckVien[0] + GIA_VIEN_XUC_XICH;
                        }else{
                            tongTien = tongTien + GIA_VIEN_XUC_XICH;
                        }
                        lasCheckVien[0] = GIA_VIEN_XUC_XICH;
                        break;

                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });

        rgBanhMi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = rgBanhMi.findViewById(i);
                int index = rgBanhMi.indexOfChild(radioButton);
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                tongTien = tongTien - (checkBanhMy[0] * Integer.parseInt(txtSoLuongBanhMi.getText().toString()));
                txtSoLuongBanhMi.setText("0");
                switch (index){
                    case 0:
                        checkBanhMy[0] = GIA_BANH_MI_OP_LA;
                        break;
                    case 1:
                        checkBanhMy[0] = GIA_BANH_MI_PHO_MAI;
                        break;
                    case 2:
                        checkBanhMy[0] = GIA_BANH_MI_CHA_CA;
                        break;
                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });



        cbThemPhoMai1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                if(ischecked){
                    tongTien = tongTien + GIA_THEM_PHO_MAI;
                }else{
                    tongTien = tongTien - GIA_THEM_PHO_MAI;
                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });

        cbGap2Phomai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                if(ischecked){
                    tongTien = tongTien + GIA_GAP_2_PHO_MAI;
                }else{
                    tongTien = tongTien - GIA_GAP_2_PHO_MAI;
                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });

        cbGap3Phomai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                if(ischecked){
                    tongTien = tongTien + GIA_GAP_3_PHO_MAI;
                }else{
                    tongTien = tongTien - GIA_GAP_3_PHO_MAI;
                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });

        cbThemPhoMai2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                if(ischecked){
                    tongTien = tongTien + GIA_THEM_PHO_MAI_1;
                }else{
                    tongTien = tongTien - GIA_THEM_PHO_MAI_1;
                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });

        cbThemThitBo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                if(ischecked){
                    tongTien = tongTien + GIA_THEM_THIT_BO;
                }else{
                    tongTien = tongTien - GIA_THEM_THIT_BO;
                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });

        cbThemTrungOpLa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                int tongTien = Integer.parseInt(txtTongTien.getText().toString());
                if(ischecked){
                    tongTien = tongTien + GIA_THEM_TRUNG_OP_LA;
                }else{
                    tongTien = tongTien - GIA_THEM_TRUNG_OP_LA;
                }
                txtTongTien.setText(String.valueOf(tongTien));
            }
        });

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maGiamGia = editMaGiamGia.getText().toString();
                String tongTien = txtTongTien.getText().toString();
                String slPizza = txtSoLuongPizza.getText().toString();
                String slHamburger = txtSoLuongHamburger.getText().toString();
                String slBanhMi = txtSoLuongBanhMi.getText().toString();
                if(tongTien != "0" && (slPizza != "0" || slBanhMi != "0" || slHamburger != "0")){
                    double giamGia = 0.0;
                    if(maGiamGia.equalsIgnoreCase("abc")){
                        giamGia = Integer.parseInt(tongTien) * ABC;
                    }else if(maGiamGia.equalsIgnoreCase("xyz")){
                        giamGia = Integer.parseInt(tongTien) * XYZ;
                    }else{
                        giamGia = 0.0;
                    }
                    txtGiamGia.setText(String.valueOf(giamGia));
                    Intent secondActivate = new Intent(MainActivity.this,SecondActivity.class);
                    secondActivate.putExtra("Giam Gia", tongTien);
                    secondActivate.putExtra("Tong Tien", giamGia);
                    startActivity(secondActivate);
                    String kq = "Giam Gia: " + giamGia + "\nTong Tien: " + tongTien;
                    Toast.makeText(getApplicationContext(),
                            kq,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Khong hoan tat duoc",Toast.LENGTH_LONG).show();
                }
            }
        });







        //Nút làm lại
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshEditText(editMaGiamGia);
                refreshTextView(txtGiamGia,txtTongTien,txtSoLuongBanhMi,txtSoLuongHamburger,txtSoLuongPizza);
                refreshCheckBox(cbThemPhoMai1,cbGap2Phomai,cbGap3Phomai,cbThemPhoMai2,
                        cbThemThitBo,cbThemTrungOpLa);
                refreshRadioButton(rbDeMong,rbDeDay,rbDeTruyenThong);
                refreshRadioButton(rbVienPhoMai,rbVienXucXich);
                refreshRadioButton(rbBMOpLa,rbBMPhoMaiThitNguoi,rbBMChaCa);

            }
        });










    }
    private void init(){
        cbThemPhoMai1 = (CheckBox) findViewById(R.id.cbThemPhoMai1);
        cbGap2Phomai = (CheckBox) findViewById(R.id.cbGap2Phomai);
        cbGap3Phomai = (CheckBox) findViewById(R.id.cbGap3Phomai);
        cbThemPhoMai2 = (CheckBox) findViewById(R.id.cbThemPhoMai2);
        cbThemThitBo = (CheckBox) findViewById(R.id.cbThemThitBo);
        cbThemTrungOpLa = (CheckBox) findViewById(R.id.cbThemTrungOpLa);

        rgBanhMi = (RadioGroup)findViewById(R.id.rgBanhMi);
        rgPizza1 = (RadioGroup)findViewById(R.id.rgPizza1);
        rgPizza = (RadioGroup) findViewById(R.id.rgPizza);

        rbDeMong = (RadioButton) findViewById(R.id.rbDeMong);
        rbDeDay = (RadioButton) findViewById(R.id.rbDeDay);
        rbDeTruyenThong = (RadioButton) findViewById(R.id.rbDeTruyenThong);
        rbVienPhoMai = (RadioButton) findViewById(R.id.rbVienPhoMai);
        rbVienXucXich = (RadioButton) findViewById(R.id.rbVienXucXich);
        rbBMOpLa = (RadioButton) findViewById(R.id.rbBMOpLa);
        rbBMPhoMaiThitNguoi = (RadioButton) findViewById(R.id.rbBMPhoMaiThitNguoi);
        rbBMChaCa = (RadioButton) findViewById(R.id.rbBMChaCa);


        editMaGiamGia = (EditText) findViewById(R.id.editMaGiamGia);

        txtGiamGia = (TextView) findViewById(R.id.txtGiamGia);
        txtTongTien = (TextView) findViewById(R.id.txtTongTien);
        txtSoLuongPizza = (TextView) findViewById(R.id.txtSoLuongPizza);
        txtSoLuongHamburger = (TextView) findViewById(R.id.txtSoLuongHamburger);
        txtSoLuongBanhMi = (TextView) findViewById(R.id.txtSoLuongBanhMi);


        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnDatHang = (Button) findViewById(R.id.btnDatHang);
        btnGiamPizza = (Button) findViewById(R.id.btnGiamPizza);
        btnTangPizza = (Button) findViewById(R.id.btnTangPizza);
        btnGiamBanhMi = (Button) findViewById(R.id.btnGiamBanhMi);
        btnTangBanhMi = (Button) findViewById(R.id.btnTangBanhMi);
        btnGiamHamburger = (Button) findViewById(R.id.btnGiamHamburger);
        btnTangHamburger = (Button) findViewById(R.id.btnTangHamburger);


    }

    private void refreshCheckBox(CheckBox... checkBoxes){
        for(CheckBox checkBox : checkBoxes){
            checkBox.setChecked(false);
        }
    }

    private void refreshRadioButton(RadioButton... radioButtons){
        for(RadioButton radioButton : radioButtons){
            radioButton.setChecked(false);
        }
    }

    private void refreshEditText(EditText... editTexts){
        for(EditText editText : editTexts){
            editText.setText("0");
        }
    }

    private void refreshTextView(TextView... textViews){
        for(TextView textView : textViews){
            textView.setText("0");
        }
    }
}