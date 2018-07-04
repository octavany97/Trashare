package id.ac.umn.trashare;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailBerandaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_beranda);

        TextView judul = (TextView) findViewById(R.id.judul_text);
        TextView tanggal = (TextView) findViewById(R.id.tanggal_text);
        TextView deskripsi = (TextView) findViewById(R.id.deskripsi_text);
        ImageView gambar = (ImageView) findViewById(R.id.foto_image);

        judul.setText(getIntent().getStringExtra("judul"));
        tanggal.setText(getIntent().getStringExtra("tanggal"));
        deskripsi.setText(getIntent().getStringExtra("deskripsi"));
        byte[] imageAsBytes = Base64.decode(getIntent().getStringExtra("foto"), Base64.DEFAULT);

        gambar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
    }
}
