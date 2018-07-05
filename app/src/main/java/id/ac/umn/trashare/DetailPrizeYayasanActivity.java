package id.ac.umn.trashare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailPrizeYayasanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_prize_yayasan);

        EditText nama = (EditText) findViewById(R.id.editNama);
        EditText deskripsi = (EditText) findViewById(R.id.editDescription);
        EditText sponsor = (EditText) findViewById(R.id.editSponsor);
        EditText poin = (EditText) findViewById(R.id.editPoint);
        //EditText periode = (EditText) findViewById(R.id.editNama);

        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
