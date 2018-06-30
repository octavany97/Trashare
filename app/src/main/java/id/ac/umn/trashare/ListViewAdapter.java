package id.ac.umn.trashare;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Octavany on 6/28/2018.
 */

public class ListViewAdapter extends BaseAdapter {
    TextView text1, text2, text3;
    ImageView imgView;
    String[][] data;
    Activity activity;
    int code;

    public ListViewAdapter(Activity activity, String[][] data){
        super();
        this.data = data;
        this.activity = activity;
        this.code = 1;
    }
    public ListViewAdapter(Activity activity, String[][] data, int code){
        super();
        this.data = data;
        this.activity = activity;
        this.code = code;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
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
            if(code == 1){
                v = vi.inflate(R.layout.custom_listview, parent, false);
            }
            // code == 2 untuk hadiah
            else if(code == 2){
                v = vi.inflate(R.layout.custom_listview2, parent, false);
            }
            // code == 3 untuk histori poin member
            else if(code == 3){
                v = vi.inflate(R.layout.custom_listview_transaksi, parent, false);
            }
            else if(code == 4){
                v = vi.inflate(R.layout.custom_listview_kegiatan, parent, false);
            }
        }

        Object p = getItem(position);

        if(p != null){
            if(code == 1 || code == 2){
                imgView = (ImageView) v.findViewById(R.id.imgView);
                text1 = (TextView) v.findViewById(R.id.text1);
                text2 = (TextView) v.findViewById(R.id.text2);

                int id = activity.getResources().getIdentifier(data[position][2], "drawable", activity.getPackageName());
                Drawable drawable = activity.getResources().getDrawable(id);

                imgView.setImageDrawable(drawable);
                text1.setText(data[position][0]);
                text2.setText(data[position][1]);
            }
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
        }

        return v;
    }
}
