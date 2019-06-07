package id.ac.umn.trashare;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by ASUS on 5/14/2018.
 */

public class NotifikasiYayasanFragment extends Fragment{
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_notifikasi_yayasan, container, false);

        listView = v.findViewById(R.id.listNotif);
        //perlu id sepertinya, buat pindah ke activity
        final String[][] myDataset = new String[][] {{"Pengajuan Bank Sampah ABC di Bogor","Tanggal 1", "emails"}, {"Jalan Sehat Bank Sampah","Tanggal 2", "emailsopen"}};
        ListViewAdapter mAdapter = new ListViewAdapter(getActivity(), myDataset);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailNotifikasiMemberActivity.class);
                //System.out.println(items[i].toString());
                intent.putExtra("name", myDataset[i][0].toString());
                intent.putExtra("tanggal", myDataset[i][1].toString());
                //intent.putExtra("icon", myDataset[i][2].toString());
                startActivity(intent);
                // Toast.makeText(getActivity().getApplicationContext(),items[i], Toast.LENGTH_SHORT).show();
            }
        });
        return v;

        //return inflater.inflate(R.layout.fragment_menu_list_bank_sampah, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Notifikasi");
    }
}
