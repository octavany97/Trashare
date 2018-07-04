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

import id.ac.umn.trashare.models.Kegiatan;

/**
 * Created by Octavany on 7/4/2018.
 */

public class EventListAdapter extends BaseAdapter {
    List<Kegiatan> listKegiatan;
    TextView text1, text2, text3;
    ImageView imgView;
    Activity activity;

    public EventListAdapter(Activity activity, List<Kegiatan> listKegiatan){
        super();
        this.listKegiatan = listKegiatan;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if(listKegiatan.size() > 0) return listKegiatan.size();
        else return 0;
    }

    @Override
    public Object getItem(int position) {
        return listKegiatan.get(position);
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
            v = vi.inflate(R.layout.custom_listview, parent, false);
        }

        Object p = getItem(position);

        if(p != null){
            imgView = (ImageView) v.findViewById(R.id.imgView);
            text1 = (TextView) v.findViewById(R.id.text1);
            text2 = (TextView) v.findViewById(R.id.text2);

            byte[] imageAsBytes = Base64.decode(listKegiatan.get(position).fotoKegiatan, Base64.DEFAULT);

            imgView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            text1.setText(listKegiatan.get(position).namaKegiatan);
            text2.setText(listKegiatan.get(position).tanggalKegiatan);
        }

        return v;
    }
}
