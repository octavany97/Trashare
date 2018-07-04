package id.ac.umn.trashare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import id.ac.umn.trashare.models.Sampah;
import id.ac.umn.trashare.utils.Webservice;

public class UpdateSampahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sampah);

        TextView namaSampah = (TextView) findViewById(R.id.editJenisSampah);
        TextView hargaLapak = (TextView) findViewById(R.id.editHargaLapak);
        TextView hargaNasabah = (TextView) findViewById(R.id.editHargaNasabah);

        final Long id = Long.parseLong(getIntent().getStringExtra("idSampah"));
        final String nama = getIntent().getStringExtra("nama");
        final Long hrgLapak = Long.parseLong(getIntent().getStringExtra("hargaLapak"));
        final Long hrgNasabah = Long.parseLong(getIntent().getStringExtra("hargaNasabah"));
        final String tipe = getIntent().getStringExtra("tipe");

        namaSampah.setText(nama);
        hargaLapak.setText(hrgLapak.toString());
        hargaNasabah.setText(hrgNasabah.toString());

        final Sampah updateSampah = new Sampah(Long.parseLong(hargaLapak.getText().toString()), Long.parseLong(hargaNasabah.getText().toString()), namaSampah.getText().toString(), tipe);


        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Webservice.getService(getBaseContext()).updateSampah(id.toString(), updateSampah);
                finish();
            }
        });
    }
}
