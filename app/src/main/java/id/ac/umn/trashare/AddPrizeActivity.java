package id.ac.umn.trashare;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPrizeActivity extends AppCompatActivity {
    private String datePick, base64image = "";
    private static EditText tanggal;
    private String imagePath;
    ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prize);

        final EditText namaHadiah = (EditText) findViewById(R.id.editNama);
        final EditText deskripsi = (EditText) findViewById(R.id.editDescription);
        final EditText sponsor = (EditText) findViewById(R.id.editSponsor);
        final EditText poin = (EditText) findViewById(R.id.editPoint);
        tanggal = (EditText) findViewById(R.id.editDate);

        final Button tanggalButton = (Button) findViewById(R.id.calendarButton);
        tanggalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker mDatePicker = new DatePicker();
                mDatePicker.show(getFragmentManager(), "Pilih tanggal");
            }
        });
        // final DatePicker periodeTukar = (DatePicker) findViewById(R.id.date);

        //Date tglTukar = getDateFromDatePicker(periodeTukar);

        foto = (ImageView) findViewById(R.id.gambar_hadiah);
        final Button searchButton = (Button) findViewById(R.id.search_btn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryIntent();
                //startActivityForResult(new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI),54);
            }
        });

        if(ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 20);
        }


        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Hadiah newHadiah = new Hadiah(deskripsi.getText().toString(), base64image, namaHadiah.getText().toString(), tanggal.getText().toString(), Long.parseLong(poin.getText().toString()), sponsor.getText().toString());
                Webservice.getService(getBaseContext()).createHadiah(newHadiah).enqueue(new Callback<Hadiah>() {
                    @Override
                    public void onResponse(Call<Hadiah> call, Response<Hadiah> response) {
                        //Toast.makeText()
                        Toast.makeText(getBaseContext(), "Berhasil", Toast.LENGTH_SHORT).show();
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

    public static class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
            //return super.onCreateDialog(savedInstanceState);
        }

        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
            tanggal.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode != RESULT_CANCELED) {
            try {
                if (requestCode == 54 && resultCode != 0) {
                    onSelectImageGalleryResult(intent.getData());
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    base64image = Base64.encodeToString(bytes, Base64.DEFAULT);
                    base64image = base64image.substring(base64image.indexOf(",")+1);
                    foto.setImageBitmap(bitmap);

//                    if (intent != null) {
//                        Uri selectedImage = intent.getData();
//
//                        Bitmap bitmap = BitmapFactory.decodeFile(selectedImage.getPath());
//                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//                        byte[] bytes = byteArrayOutputStream.toByteArray();
//                        base64image = Base64.encodeToString(bytes, Base64.DEFAULT);
//
//                    } else {
//                        Toast.makeText(getApplicationContext(),
//                                "Gambar tidak dapat diambil",
//                                Toast.LENGTH_SHORT).show();
//                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    //buka galeri
    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 54);
    }
    //ambil dari galeri
    private void onSelectImageGalleryResult(Uri contentData) {
        if (Build.VERSION.SDK_INT < 19) {

            String[] projection = { MediaStore.Images.Media.DATA };

            CursorLoader cursorLoader = new CursorLoader(getBaseContext(), contentData, projection, null, null, null);
            Cursor cursor = cursorLoader.loadInBackground();

            if (cursor != null) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                imagePath = cursor.getString(columnIndex);
            } else {
                imagePath = contentData.getPath();
            }
        } else {
            String wholeId = DocumentsContract.getDocumentId(contentData);
            String id = wholeId.split(":")[1];

            String[] column = { MediaStore.Images.Media.DATA };
            String sel = MediaStore.Images.Media._ID + "=?";

            Cursor cursor = getBaseContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[] { id }, null);
            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {
                imagePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }
    }
    private byte[] GetFileBytes(Uri uri)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(200000);
        InputStream imageStream;
        try {
            imageStream = getContentResolver().openInputStream(uri);
            byte[] buffer;
            buffer = new byte[100000];
            while ((imageStream.read(buffer)) != -1) {
                bos.write(buffer);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

}
