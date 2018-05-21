package id.ac.umn.trashare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
    int id_tipe = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_menu_register_bank_sampah, container, false);
        View v = inflater.inflate(R.layout.fragment_menu_input_sampah, container, false);

        ListView listView =(ListView) v.findViewById(R.id.sampah_list);
       // final String[] items = new String[] {"Bukit Pamulang Indah", "Villa Pamulang", "Al Falaah III","Puri Bintaro Hijau","Villa Inti Persada","Taman PAUD Cahaya Agung","RS Griya Pipit VI","Perigi Baru I","Japos Graha Lestari","Perigi Baru II"};
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


        Button addSampahButton = (Button) v.findViewById(R.id.addSampahButton);
        addSampahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                //builder.setMessage();
                LayoutInflater inflater = getActivity().getLayoutInflater();
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        getActivity(),
                        R.array.tipe_list,
                        android.R.layout.simple_dropdown_item_1line
                );
                builder.setView(inflater.inflate(R.layout.dialog_edit_input_sampah, null))
                        .setPositiveButton(R.string.tambah, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //tambah ke database
                                //ntar list view nya bertambah

                                dialogInterface.cancel();
                            }
                        })
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.out.println(i);
                                id_tipe = i;
                            }
                        })
                        .setNegativeButton(R.string.kembali, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                                //cancel the dialog
                                dialog.cancel();
                            }
                        });

                AlertDialog dialog = builder.create();

                dialog.show();

            }
        });
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
