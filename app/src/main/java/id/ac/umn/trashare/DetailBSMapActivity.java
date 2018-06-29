package id.ac.umn.trashare;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailBSMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String name = "";
    private String location = "";
    private String image = "";
    private String info = "Informasi Bank Sampah ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bsmap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        name = getIntent().getStringExtra("name");
        location = getIntent().getStringExtra("location");
        image = getIntent().getStringExtra("image");
        info += name;

        TextView txtInfo = (TextView) findViewById(R.id.nama_bs);
        txtInfo.setText(info);

        TextView txtNama = (TextView) findViewById(R.id.nama_banksampah);
        txtNama.setText(name);

        TextView txtLokasi = (TextView) findViewById(R.id.nama_lokasi);
        txtLokasi.setText(location);

        final String access = getIntent().getStringExtra("access");

        Button btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(access.equals("not login")){
                    Intent i = new Intent(DetailBSMapActivity.this, MainActivity.class);
                    i.putExtra("access", "login");
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(DetailBSMapActivity.this, RegisterNasabahActivity.class);
                    startActivity(i);
                }
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
