package id.ac.umn.trashare;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.umn.trashare.models.BankSampah;
import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.models.Kegiatan;
import id.ac.umn.trashare.models.Member;
import id.ac.umn.trashare.models.Sampah;
import id.ac.umn.trashare.models.Yayasan;

/**
 * Created by ASUS on 6/28/2018.
 */

public class HadiahListAdapter extends BaseAdapter {
    List<Hadiah> listHadiah;
    TextView text1, text2, text3;
    ImageView imgView;
    Activity activity;

    public HadiahListAdapter(Activity activity, List<Hadiah> listHadiah){
        super();
        this.listHadiah = listHadiah;
        this.activity = activity;
    }
   /* public ListViewAdapter(Activity activity, String[][] data, int code){
        super();
        this.data = data;
        this.activity = activity;
        this.code = code;
    }*/

    @Override
    public int getCount() {
        return listHadiah.size();
    }

    @Override
    public Object getItem(int position) {
        return listHadiah.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(activity);
            // code == 1 untuk beranda, list bank sampah, notifikasi
            /*if(code == 1){
                v = vi.inflate(R.layout.custom_listview, parent, false);
            }*/
            // code == 2 untuk hadiah
            v = vi.inflate(R.layout.custom_listview2, parent, false);

            /*// code == 3 untuk histori poin member
            else if(code == 3){
                v = vi.inflate(R.layout.custom_listview_transaksi, parent, false);
            }
            else if(code == 4){
                v = vi.inflate(R.layout.custom_listview_kegiatan, parent, false);
            }
            else if(code == 5){
                v = vi.inflate(R.layout.custom_listview_jenis_sampah, parent, false);
            }
            else if(code == 6){
                v = vi.inflate(R.layout.custom_listview_hadiah, parent, false);
            }*/
        }

        Object p = getItem(position);

        if(p != null){
            /*if(code == 1 || code == 2 || code == 6){*/
            imgView = (ImageView) v.findViewById(R.id.imgView);
            text1 = (TextView) v.findViewById(R.id.text1);
            text2 = (TextView) v.findViewById(R.id.text2);

                /*int id = activity.getResources().getIdentifier(listHadiah.get(position).fotoHadiah, "drawable", activity.getPackageName());
                Drawable drawable = activity.getResources().getDrawable(id);

                imgView.setImageDrawable(drawable);*/
            byte[] imageAsBytes = Base64.decode(listHadiah.get(position).fotoHadiah, Base64.DEFAULT);

            imgView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            text1.setText(listHadiah.get(position).namaHadiah);
            text2.setText(String.valueOf(listHadiah.get(position).poin));
            /*}
            else if(code == 3){
                imgView = (ImageView) v.findViewById(R.id.imgView);
                text1 = (TextView) v.findViewById(R.id.text1);
                text2 = (TextView) v.findViewById(R.id.text2);
                text3 = (TextView) v.findViewById(R.id.text3);

                int id = activity.getResources().getIdentifier(data[position][3], "drawable", activity.getPackageName());
                Drawable drawable = activity.getResources().getDrawable(id);

                imgView.setImageDrawable(drawable);
                text1.setText(data[position][0]);
                text2.setText(data[position][1]);
                text3.setText(data[position][2]);
            }
            else if(code == 4){
                text1 = (TextView) v.findViewById(R.id.text1);
                text2 = (TextView) v.findViewById(R.id.text2);

                text1.setText(data[position][0]);
                text2.setText(data[position][1]);
            }
            else if(code == 5){
                text1 = (TextView) v.findViewById(R.id.txtSampah);
                text2 = (TextView) v.findViewById(R.id.txtNasabah);
                text3 = (TextView) v.findViewById(R.id.txtLapak);

                text1.setText(data[position][0]);
                text2.setText(data[position][1]);
                text3.setText(data[position][2]);
            }*/
        }

        return v;
    }
}
