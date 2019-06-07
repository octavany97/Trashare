package id.ac.umn.trashare;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by ASUS on 5/8/2018.
 */

public class TabunganSayaFragment extends Fragment {
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_tabungan_saya, container, false);

        listView = (ListView) v.findViewById(R.id.listTabungan);
        final String[][] items = new String[][] {{"EVENT 1", "Tanggal 1", "HAHA", "logoputih"}, {"EVENT 2", "Tanggal 2", "HIHI", "logowarna"}};
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), items, 3);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setClickable(false);
        /*
        TableLayout tblHistory = v.findViewById(R.id.tblHistory);

        TableRow tr_head = new TableRow(this.getActivity());
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        TextView label_no = new TextView(this.getActivity());
        label_no.setText("No");
        tr_head.addView(label_no);

        TextView label_tanggal = new TextView(this.getActivity());
        label_tanggal.setText("Tanggal");
        tr_head.addView(label_tanggal);

        TextView label_poin = new TextView(this.getActivity());
        label_poin.setText("Keterangan");
        tr_head.addView(label_poin);

        TextView label_keterangan = new TextView(this.getActivity());
        label_keterangan.setText("Jumlah Saldo");
        tr_head.addView(label_keterangan);

        tblHistory.addView(tr_head, new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        String[] tanggal = {"04-04-2018","04-05-2018", "05-05-2018"};
        Integer[] uang = {13000, 14500, 16900};
        String[] status = {"Menabung 2 kg Alumunium", "Menabung 1 kg Besi", "Menabung 3 kg Majalah"};

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
            txtJenis.setText(tanggal[i - 1].toString());
            tr.addView(txtJenis);

            TextView txtNasabah = new TextView(this.getActivity());
            txtNasabah.setText(status[i - 1].toString());
            tr.addView(txtNasabah);

            TextView txtLapak = new TextView(this.getActivity());
            txtLapak.setText(uang[i - 1].toString());
            tr.addView(txtLapak);

            tblHistory.addView(tr, new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            i++;
        }
*/
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Tabungan Saya");
    }

}
