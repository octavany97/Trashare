package id.ac.umn.trashare;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PoinSayaFragment extends Fragment{

    View v;
    public PoinSayaFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_poin_saya,container,false);
        return v;
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_poin_saya);
//
//        ListView listView =(ListView) findViewById(R.id.listHadiah);
//        final String[] items = new String[] {"Hadiah 1", "Hadiah 2", "Hadiah 3"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getBaseContext(), DetailRedeemPrizeActivity.class);
//                //System.out.println(items[i].toString());
//                intent.putExtra("name", items[i].toString());
//                startActivity(intent);
//                // Toast.makeText(getActivity().getApplicationContext(),items[i], Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}