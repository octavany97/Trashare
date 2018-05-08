package id.ac.umn.trashare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailBankSampahActivity extends AppCompatActivity {
    private String name = "";
    private String info = "Informasi Bank Sampah ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bank_sampah);

        name = getIntent().getStringExtra("name");
        info += name;

        TextView txtInfo = (TextView) findViewById(R.id.nama_bs);
        txtInfo.setText(info);

        TextView txtNama = (TextView) findViewById(R.id.nama_lokasi);
        txtNama.setText(name);

        final String access = getIntent().getStringExtra("access");

        Button btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(access.equals("not login")){
                    Intent i = new Intent(DetailBankSampahActivity.this, MainActivity.class);
                    i.putExtra("access", "login");
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(DetailBankSampahActivity.this, RegisterNasabahActivity.class);
                    startActivity(i);
                }
            }
        });
    }

}
