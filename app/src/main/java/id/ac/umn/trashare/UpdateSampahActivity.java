package id.ac.umn.trashare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import id.ac.umn.trashare.models.Sampah;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSampahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sampah);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.setTitle("Ubah Sampah");

        final TextView namaSampah = (TextView) findViewById(R.id.editJenisSampah);
        final TextView hargaLapak = (TextView) findViewById(R.id.editHargaLapak);
        final TextView hargaNasabah = (TextView) findViewById(R.id.editHargaNasabah);

        final Long id = Long.parseLong(getIntent().getStringExtra("idSampah"));
        final String nama = getIntent().getStringExtra("nama");
        final Long hrgLapak = Long.parseLong(getIntent().getStringExtra("hargaLapak"));
        final Long hrgNasabah = Long.parseLong(getIntent().getStringExtra("hargaNasabah"));
        final String tipe = getIntent().getStringExtra("tipe");

        namaSampah.setText(nama);
        hargaLapak.setText(hrgLapak.toString());
        hargaNasabah.setText(hrgNasabah.toString());

        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Sampah updateSampah2 = new Sampah(Long.parseLong(hargaLapak.getText().toString()), Long.parseLong(hargaNasabah.getText().toString()), namaSampah.getText().toString(), tipe);
                //System.out.println(id + " " + hargaLapak.getText().toString()+" " + hargaNasabah.getText().toString());
                Webservice.getService(getBaseContext()).updateSampah(id.toString(), updateSampah2).enqueue(new Callback<Sampah>() {
                    @Override
                    public void onResponse(Call<Sampah> call, Response<Sampah> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Sampah> call, Throwable t) {
                        Toast.makeText(UpdateSampahActivity.this, "Gagal update sampah.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Webservice.getService(getBaseContext()).deleteSampah(id.toString()).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Toast.makeText(UpdateSampahActivity.this,"Sampah berhasil terhapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                        Toast.makeText(UpdateSampahActivity.this,"Gagal menghapus sampah.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id  == android.R.id.home)
        {
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
