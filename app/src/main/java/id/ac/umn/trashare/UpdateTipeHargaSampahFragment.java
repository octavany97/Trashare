package id.ac.umn.trashare;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by ASUS on 5/7/2018.
 */

public class UpdateTipeHargaSampahFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_menu_update_tipe_harga_sampah, container, false);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Jenis Sampah");
    }

    @Override
    public void onStart() {
        super.onStart();

        TableLayout tblTipeHargaSampah = (TableLayout) getActivity().findViewById(R.id.tipe_harga_sampah_table);

        TableRow tr_head = new TableRow(this.getActivity());
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        TextView label_no = new TextView(this.getActivity());
        label_no.setText("No");
        tr_head.addView(label_no);

        TextView label_tipe = new TextView(this.getActivity());
        label_tipe.setText("Jenis");
        tr_head.addView(label_tipe);

        TextView label_nasabah = new TextView(this.getActivity());
        label_nasabah.setText("Nasabah");
        tr_head.addView(label_nasabah);

        TextView label_lapak = new TextView(this.getActivity());
        label_lapak.setText("Lapak");
        tr_head.addView(label_lapak);

        tblTipeHargaSampah.addView(tr_head, new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        String[] jenis = {"Alumunium","Besi", "Majalah"};
        Integer[] nasabah = {6500, 1500, 800};
        Integer[] lapak = {7000, 1700, 1200};

        Integer i = 1;
        while( i <= 3) {
            TableRow tr = new TableRow(this.getActivity());
            tr.setLayoutParams(new TableRow.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            TextView txtNo = new TextView(this.getActivity());
            txtNo.setText(i.toString());
            tr.addView(txtNo);

            TextView txtJenis = new TextView(this.getActivity());
            txtJenis.setText(jenis[i - 1].toString());
            tr.addView(txtJenis);

            TextView txtNasabah = new TextView(this.getActivity());
            txtNasabah.setText(nasabah[i - 1].toString());
            tr.addView(txtNasabah);

            TextView txtLapak = new TextView(this.getActivity());
            txtLapak.setText(lapak[i - 1].toString());
            tr.addView(txtLapak);

            tblTipeHargaSampah.addView(tr, new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            i++;

            tr.setClickable(true);
            tr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TableRow tr1=(TableRow)view;
                    TextView tv1= (TextView)tr1.getChildAt(0);

                    Intent i = new Intent(getActivity(), UpdateSampahActivity.class);
                    i.putExtra("IDSAMPAH", tv1.getText().toString());
                    getActivity().getFragmentManager().beginTransaction().remove(UpdateTipeHargaSampahFragment.this).commit();
                    startActivity(i);

                }
            });
            tr.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    TableRow tr1=(TableRow)view;
                    TextView tv1= (TextView)tr1.getChildAt(1);

                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getActivity());
                    }
                    builder.setTitle("Penghapusan Sampah")
                            .setMessage("Apakah Anda yakin akan menghapus " + tv1.getText().toString() + "?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();


                    return false;
                }
            });


        }

        TableRow tr2 = new TableRow(this.getActivity());

        Button addButton = new Button(this.getActivity());
        addButton.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1
        ));
        addButton.setText("Tambah Sampah");
        tr2.addView(addButton);
        tblTipeHargaSampah.addView(tr2);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddSampahActivity.class);
                startActivity(i);
            }
        });
    }
}
