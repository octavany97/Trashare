package id.ac.umn.trashare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailNotifikasiMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notifikasi_member);

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(getIntent().getStringExtra("name"));
        TextView txtTanggal = (TextView) findViewById(R.id.txtTanggal);
        txtTanggal.setText(getIntent().getStringExtra("tanggal"));
    }
}
