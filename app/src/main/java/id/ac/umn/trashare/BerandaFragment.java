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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        //return inflater.inflate(R.layout.fragment_menu_beranda, container, false);
        View v = inflater.inflate(R.layout.fragment_menu_beranda, container, false);


        ListView listView =(ListView) v.findViewById(R.id.listBeranda);
        final String[] items = new String[] {"EVENT 1", "EVENT 2", "EVENT 3","EVENT 4","EVENT 5","EVENT 6","EVENT 7","EVENT 8","EVENT 9","EVENT 10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);


        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Beranda Bank Sampah");
    }
}
