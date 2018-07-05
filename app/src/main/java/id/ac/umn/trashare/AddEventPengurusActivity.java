package id.ac.umn.trashare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.umn.trashare.models.Kegiatan;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventPengurusActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_pengurus);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.setTitle("Tambah Event");

        final EditText namaEvent = (EditText) findViewById(R.id.editNamaEvent);
        final EditText descriptionEvent = (EditText) findViewById(R.id.editDescriptionEvent);
        final EditText tempatEvent = (EditText) findViewById(R.id.editTempatEvent);
        final EditText tanggalEvent = (EditText) findViewById(R.id.editTanggalEvent);
        final EditText jamEvent = (EditText) findViewById(R.id.editJamEvent);

        Button btnTambah = (Button) findViewById(R.id.btnAdd);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Webservice.getService(getBaseContext()).createKegiatan(new Kegiatan(namaEvent.getText().toString(),descriptionEvent.getText().toString(),tempatEvent.getText().toString(),tanggalEvent.getText().toString() + " " + jamEvent.getText().toString())).enqueue(new Callback<Kegiatan>() {
                    @Override
                    public void onResponse(Call<Kegiatan> call, Response<Kegiatan> response) {
                        Toast.makeText(AddEventPengurusActivity.this, "Kegiatan berhasil dibuat", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Kegiatan> call, Throwable t) {
                        Toast.makeText(AddEventPengurusActivity.this, "Kegiatan gagal dibuat", Toast.LENGTH_SHORT).show();

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
