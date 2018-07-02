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
import android.widget.Toast;

/**
 * Created by ASUS on 5/5/2018.
 */

public class ListBankSampahFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final String access = this.getArguments().getString("access");
        View v = inflater.inflate(R.layout.fragment_menu_list_bank_sampah, container, false);

        String [] values =
                {"Pamulang","Tangerang Kota","Tangerang Selatan","Legok","Tigaraksa","Kali Deres"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner_lokasi);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);



        ListView listView =(ListView) v.findViewById(R.id.bs_list);

        final String[][] items = new String[][] {{"Bukit Pamulang Indah", "Pamulang", "logoputih"}, {"Villa Pamulang", "Pamulang", "logowarna"}, {"Al Falaah III", "Al Falaah", "logowarna2"},{"Puri Bintaro Hijau", "Bintaro", "logoputih"},{"Villa Inti Persada","Persada","logowarna"},
                {"Taman PAUD Cahaya Agung","Cahaya Agung", "logowarna2"},{"RS Griya Pipit VI","Pipit","logoputih"},{"Perigi Baru I","Perigi Baru", "logowarna"},{"Japos Graha Lestari","Graha Lestari","logowarna2"},{"Perigi Baru II","Perigi Baru","logoputih"}};
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailBSMapActivity.class);
                intent.putExtra("access", access);
                intent.putExtra("name", items[i][0]);
                intent.putExtra("location", items[i][1]);
                intent.putExtra("image", items[i][2]);
                startActivity(intent);
            }
        });
        return v;

        //return inflater.inflate(R.layout.fragment_menu_list_bank_sampah, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Daftar Bank Sampah");
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
