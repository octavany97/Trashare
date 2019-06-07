package id.ac.umn.trashare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FormRegistrasiBankSampahActivity extends AppCompatActivity {

    EditText txtAlamat;
    EditText txtNamaBankSampah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_registrasi_bank_sampah);

        txtNamaBankSampah = (EditText) findViewById(R.id.nama_banksampah_edit);
        txtAlamat = (EditText) findViewById(R.id.alamat_edit);
        txtAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(FormRegistrasiBankSampahActivity.this, MapActivity.class), 0);
            }
        });
        String [] values =
                {"Bekasi","Depok","Legok","Pamulang","Tigaraksa","Tangerang Kota", "Tangerang Selatan"};

        Spinner spinner = (Spinner) findViewById(R.id.spinner_wilayah);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);

        Button btnRegis = (Button) findViewById(R.id.btnRegister);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                String address = extras.getString("ADDRESS");
                txtAlamat.setText(address);
            }

        }
    }
}
