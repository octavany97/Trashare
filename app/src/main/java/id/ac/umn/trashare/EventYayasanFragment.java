package id.ac.umn.trashare;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 5/8/2018.
 */

public class EventYayasanFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_menu_event_yayasan, container, false);

        View v = inflater.inflate(R.layout.fragment_menu_event_yayasan, container, false);

        ListView listEvent =(ListView) v.findViewById(R.id.listEvent);
        final String[][] items = new String[][] {{"Jalan Sehat Bank Sampah", "Bank Sampah Melati"}, {"Kreasi Bank Sampah", "Bank Sampah Pamulang"}, {"Senam Bersama Bank Sampah", "Yayasan Bank Sampah Melati Bersih"}};
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), items, 4);


        listEvent.setAdapter(adapter);
        listEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailEventYayasanActivity.class);
                //System.out.println(items[i].toString());
                intent.putExtra("name", items[i][0].toString());
                intent.putExtra("penyelenggara", items[i][1].toString());
                intent.putExtra("deskripsi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Kegiatan");
    }
}
