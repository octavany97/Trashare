package id.ac.umn.trashare;

import android.app.Fragment;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HistoryPoinFragment extends Fragment {
    View v;
    public HistoryPoinFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history_poin,container,false);
        return v;
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history_poin);
//
//        TableLayout tblHistory = findViewById(R.id.tblHistory);
//
//        TableRow tr_head = new TableRow(this);
//        tr_head.setBackgroundColor(Color.GRAY);
//        tr_head.setLayoutParams(new TableRow.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        ));
//
//        TextView label_no = new TextView(this);
//        label_no.setText("No");
//        tr_head.addView(label_no);
//
//        TextView label_tanggal = new TextView(this);
//        label_tanggal.setText("Tanggal");
//        tr_head.addView(label_tanggal);
//
//        TextView label_poin = new TextView(this);
//        label_poin.setText("Poin");
//        tr_head.addView(label_poin);
//
//        TextView label_keterangan = new TextView(this);
//        label_keterangan.setText("Keterangan");
//        tr_head.addView(label_keterangan);
//
//        tblHistory.addView(tr_head, new TableLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        ));
//
//        String[] tanggal = {"04-04-2018","04-05-2018", "05-05-2018"};
//        Integer[] poin = {50, 150, 75};
//        String[] status = {"Menabung", "Menabung", "Tukar dengan Hadiah 2"};
//
//        Integer i = 1;
//        while( i <= 3) {
//            TableRow tr = new TableRow(this);
//            tr.setLayoutParams(new TableRow.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            ));
//
//            TextView txtNo = new TextView(this);
//            txtNo.setText(i.toString());
//            tr.addView(txtNo);
//
//            TextView txtJenis = new TextView(this);
//            txtJenis.setText(tanggal[i - 1].toString());
//            tr.addView(txtJenis);
//
//            TextView txtNasabah = new TextView(this);
//            txtNasabah.setText(poin[i - 1].toString());
//            tr.addView(txtNasabah);
//
//            TextView txtLapak = new TextView(this);
//            txtLapak.setText(status[i - 1].toString());
//            tr.addView(txtLapak);
//
//            tblHistory.addView(tr, new TableLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            ));
//            i++;
//        }
//
//    }
}
