package id.ac.umn.trashare;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.umn.trashare.models.Sampah;

/**
 * Created by Octavany on 7/4/2018.
 */

public class SampahListAdapter extends BaseAdapter{
    List<Sampah> listSampah;
    TextView text1, text2, text3;
    Activity activity;

    public SampahListAdapter(Activity activity, List<Sampah> listSampah){
        super();
        this.listSampah = listSampah;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listSampah.size();
    }

    @Override
    public Object getItem(int position) {
        return listSampah.get(position);
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
            v = vi.inflate(R.layout.custom_listview_jenis_sampah, parent, false);
        }

        Object p = getItem(position);

        if(p != null){
            text1 = (TextView) v.findViewById(R.id.txtSampah);
            text2 = (TextView) v.findViewById(R.id.txtNasabah);
            text3 = (TextView) v.findViewById(R.id.txtLapak);

            text1.setText(listSampah.get(position).namaSampah);
            text2.setText(String.valueOf(listSampah.get(position).hargaBeliNasabah));
            text3.setText(String.valueOf(listSampah.get(position).hargaBeliLapak));
        }

        return v;
    }
}
