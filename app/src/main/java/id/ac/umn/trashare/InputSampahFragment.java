package id.ac.umn.trashare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

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

        final ListView listView = (ListView) v.findViewById(R.id.sampah_list);
        final List<String> list = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


        Button addSampahButton = (Button) v.findViewById(R.id.addSampahButton);
        addSampahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                View viewDialog = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit_input_sampah, (ViewGroup) getView(), false);

                final Spinner spinnerSampah = (Spinner) viewDialog.findViewById(R.id.spinner_jenis_sampah);

                //LayoutInflater inflater = getActivity().getLayoutInflater();
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        getActivity(),
                        R.array.tipe_list,
                        android.R.layout.simple_dropdown_item_1line
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSampah.setAdapter(adapter);
                final EditText beratEdit = (EditText)viewDialog.findViewById(R.id.berat_edit);
                builder.setView(viewDialog)
                        .setPositiveButton(R.string.tambah, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //tambah ke database
                                //ntar list view nya bertambah
                                list.add(String.format("%s - %s kg", spinnerSampah.getSelectedItem().toString(), beratEdit.getText().toString()));

                                dialogInterface.cancel();

                                //buat refresh klo ud ada db
                                /*FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(InputSampahFragment.this).attach(InputSampahFragment.this).commit();*/
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
        getActivity().setTitle("Input Sampah Nasabah");
    }
}
