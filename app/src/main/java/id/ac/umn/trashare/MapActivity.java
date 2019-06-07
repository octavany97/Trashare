package id.ac.umn.trashare;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnCameraMoveListener, View.OnClickListener {

    private static final String TAG = "MapActivity";

    private GoogleMap googleMap;
    MapView mapView;
    private CameraPosition newLocation;
    Button btnSetPosition, btnFindPosition;
    EditText txtFindPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA}, 1);
        }

        setupViews();

        try {
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setupViews() {
        mapView = (MapView) findViewById(R.id.map_view);
        btnSetPosition = (Button) findViewById(R.id.btnSetPosition);
        btnSetPosition.setOnClickListener(this);
        btnFindPosition = (Button) findViewById(R.id.btnMapsFind);
        btnFindPosition.setOnClickListener(this);
        txtFindPosition = (EditText) findViewById(R.id.txtMapsLocation);

        animate();
    }

    private void fillViews() {

    }


    private void animate(){
        TableRow[] rows = new TableRow[]{
                (TableRow) findViewById(R.id.anim_location),
                (TableRow) findViewById(R.id.anim_maps),
                (TableRow) findViewById(R.id.anim_mark_button)
        };

        ObjectAnimator[] animator = new ObjectAnimator[rows.length*2];
        AnimatorSet set = new AnimatorSet();
        for(int i=0;i<rows.length;i++){
            animator[i] = ObjectAnimator.ofFloat(rows[i], "alpha", 0,1);
            animator[i+rows.length] = ObjectAnimator.ofFloat(rows[i], "translationY", rows[i].getY()+500,rows[i].getY());
            animator[i].setDuration(400+i*100);
            animator[i+rows.length].setDuration(400+i*100);
        }
        set.playTogether(animator);
        set.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        googleMap = null;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        try {
            this.googleMap = googleMap;
            this.googleMap.getUiSettings().setMapToolbarEnabled(false);

            try {
                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                } else {
                    if (this.googleMap != null) {
                        this.googleMap.setMyLocationEnabled(true);
                        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                        this.googleMap.getUiSettings().setAllGesturesEnabled(true);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Location location = getLastKnownLocation(getBaseContext());

                final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                this.googleMap.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
                    @Override
                    public void onMyLocationClick(@NonNull Location location) {
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                    }
                });
                googleMap.setOnCameraMoveListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mapView.onResume();
            fillViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCameraMove() {
        newLocation = googleMap.getCameraPosition();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.btnSetPosition:
                Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                try {
                    List<Address> addressList = null;
                    if (newLocation != null) {
                        addressList = geocoder.getFromLocation(newLocation.target.latitude, newLocation.target.longitude, 1);
                    } else {
                        Location location = getLastKnownLocation(getBaseContext());
                        addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    }
                    Address obj = addressList.get(0);

                    String add = obj.getAddressLine(0);
//                    add = add + ", " + obj.getCountryName();
//                    add = add + ", " + obj.getCountryCode();
//                    add = add + ", " + obj.getAdminArea();
//                    add = add + ", " + obj.getPostalCode();
//                    add = add + ", " + obj.getSubAdminArea();
//                    add = add + ", " + obj.getLocality();
//                    add = add + ", " + obj.getSubThoroughfare();

                    Intent intent = new Intent();
                    intent.putExtra("ADDRESS", add);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnMapsFind:
                String inputPosition = txtFindPosition.getText().toString();
                if(inputPosition!=null && !inputPosition.equals("")){
                    new GeocoderTask().execute(inputPosition);
                }
                break;
        }
    }

    private static Location getLastKnownLocation (Context mContext) {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        Location location = null;
        try {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            long GPSLocationTime = 0;
            if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

            long NetLocationTime = 0;

            if (null != locationNet) {
                NetLocationTime = locationNet.getTime();
            }

            if ( 0 < GPSLocationTime - NetLocationTime ) {
                location = locationGPS;
            }
            else {
                location = locationNet;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return location;
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
            }

            // Clears all the existing markers on the map
            googleMap.clear();

            // Adding Markers on Google Map for each matching address
            for (int i = 0; i < addresses.size(); i++) {

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getCountryName());

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));

                newLocation = googleMap.getCameraPosition();

                // Locate the first location
                if (i == 0)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }
}
