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
import android.widget.Spinner;

/**
 * Created by Octavany on 5/14/2018.
 */

public class InputSampahFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_menu_register_bank_sampah, container, false);
        View v = inflater.inflate(R.layout.fragment_menu_input_sampah, container, false);

        /*String [] values =
                {"Pamulang","Tangerang Kota","Tangerang Selatan","Legok","Tigaraksa","Kali Deres"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner_lokasi);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);
*/


        ListView listView =(ListView) v.findViewById(R.id.sampah_list);
        final String[] items = new String[] {"Bukit Pamulang Indah", "Villa Pamulang", "Al Falaah III","Puri Bintaro Hijau","Villa Inti Persada","Taman PAUD Cahaya Agung","RS Griya Pipit VI","Perigi Baru I","Japos Graha Lestari","Perigi Baru II"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,R.array.tipe_list);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getActivity(),
                R.array.tipe_list,
                android.R.layout.simple_list_item_1
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent intent = new Intent(getActivity(), DetailBankSampahActivity.class);
                //System.out.println(items[i].toString());
//                intent.putExtra("access", access);
//                intent.putExtra("name", items[i].toString());
                //startActivity(intent);
                // Toast.makeText(getActivity().getApplicationContext(),items[i], Toast.LENGTH_SHORT).show();
            }
        });


        Button btnRegis = (Button) v.findViewById(R.id.addSampahButton);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
