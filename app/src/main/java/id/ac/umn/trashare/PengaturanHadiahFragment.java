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
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by ASUS on 5/8/2018.
 */

public class PengaturanHadiahFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_menu_pengaturan_hadiah, container, false);
        View v = inflater.inflate(R.layout.fragment_menu_pengaturan_hadiah, container, false);

        ListView listPrize =(ListView) v.findViewById(R.id.listHadiah);
        final String[][] items = new String[][] {{"Pulsa 10.000",  "500", "logowarna"}, {"Voucher Indomaret 20.000","800", "logowarna2"}, {"Voucher Alfamart 20.000", "800", "logowarna"}};
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), items, 1);
        listPrize.setAdapter(adapter);

        listPrize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailPrizeYayasanActivity.class);
                intent.putExtra("name", items[i][0].toString());
                startActivity(intent);
            }
        });

        Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddPrizeActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hadiah");
    }
}
