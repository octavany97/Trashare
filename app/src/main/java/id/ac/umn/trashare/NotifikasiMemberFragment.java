package id.ac.umn.trashare;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Created by ASUS on 5/8/2018.
 */

public class NotifikasiMemberFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_notifikasi_member, container, false);

        ListView listView =(ListView) v.findViewById(R.id.listNotif);
        final String[][] myDataset = new String[][] {{"Jadwal Penimbangan Jumat", "11 Mei 2018", "emails"}, {"Jadwal Penimbangan Jumat","emailsopen", "4 Mei 2018"},{"Jalan Sehat Bank Sampah","1 Mei 2018","emailsopen"}};
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
