package id.ac.umn.trashare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by ASUS on 5/5/2018.
 */

public class BerandaFragment extends Fragment{
    ListView listBeranda;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_menu_beranda, container, false);

        listBeranda =(ListView) v.findViewById(R.id.listBeranda);
        final String[][] items = new String[][] {{"EVENT 1", "Tanggal 1", "logoputih"}, {"EVENT 2", "Tanggal 2", "logowarna"}, {"EVENT 3", "Tanggal 3", "logowarna2"}, {"EVENT 4", "Tanggal 4", "logoputih"}, {"EVENT 5", "Tanggal 5", "logowarna"},
                {"EVENT 6", "Tanggal 6", "logowarna2"},{"EVENT 7", "Tanggal 7", "logoputih"},{"EVENT 8", "Tanggal 8", "logowarna"},{"EVENT 9", "Tanggal 9", "logowarna2"},{"EVENT 10", "Tanggal 10", "logoputih"}};
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), items);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,items);
        listBeranda.setAdapter(adapter);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Beranda Bank Sampah");
        //getActivity().setTitleColor(246315);
    }
}
