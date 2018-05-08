package id.ac.umn.trashare;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ASUS on 5/8/2018.
 */

public class PointFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_poin, container, false);

        Button btnPoin = (Button) v.findViewById(R.id.btnPoin);
        btnPoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PoinSayaActivity.class);
                startActivity(i);
            }
        });

        Button btnHistory = (Button) v.findViewById(R.id.btnHistoryPoin);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), HistoryPoinActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Poin Saya");
    }
}
