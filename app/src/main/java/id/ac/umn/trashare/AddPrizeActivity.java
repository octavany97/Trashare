package id.ac.umn.trashare;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPrizeActivity extends AppCompatActivity {
    private String datePick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prize);

        final EditText namaHadiah = (EditText) findViewById(R.id.editNama);
        final EditText deskripsi = (EditText) findViewById(R.id.editDescription);
        final EditText sponsor = (EditText) findViewById(R.id.editSponsor);
        final EditText poin = (EditText) findViewById(R.id.editPoint);
        final DatePicker periodeTukar = (DatePicker) findViewById(R.id.date);

        Date tglTukar = getDateFromDatePicker(periodeTukar);

        final ImageView foto = (ImageView) findViewById(R.id.gambar_hadiah);
        final Button searchButton = (Button) findViewById(R.id.search_btn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Hadiah newHadiah = new Hadiah(deskripsi.getText().toString(), null, namaHadiah.getText().toString(), null, Long.parseLong(poin.getText().toString()), sponsor.getText().toString());
                Webservice.getService(getBaseContext()).createHadiah(newHadiah).enqueue(new Callback<Hadiah>() {
                    @Override
                    public void onResponse(Call<Hadiah> call, Response<Hadiah> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Hadiah> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
