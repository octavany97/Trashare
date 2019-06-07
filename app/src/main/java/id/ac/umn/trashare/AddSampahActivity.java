package id.ac.umn.trashare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.umn.trashare.models.Sampah;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSampahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sampah);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final EditText namaSampah = (EditText) findViewById(R.id.editJenisSampah);
        final EditText hargaLapak = (EditText) findViewById(R.id.editHargaLapak);
        final EditText hargaNasabah = (EditText) findViewById(R.id.editHargaNasabah);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Webservice.getService(getBaseContext()).createSampah(new Sampah(Long.parseLong(hargaLapak.getText().toString()), Long.parseLong(hargaNasabah.getText().toString()), namaSampah.getText().toString(), "Non-Organik")).enqueue(new Callback<Sampah>() {
                    @Override
                    public void onResponse(Call<Sampah> call, Response<Sampah> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Sampah> call, Throwable t) {
                        Toast.makeText(AddSampahActivity.this,"Gagal menambah sampah.", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
        this.setTitle("Tambah Sampah");
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
