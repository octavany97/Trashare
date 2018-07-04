package id.ac.umn.trashare;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoinSayaFragment extends Fragment{

    View v;
    private ListView listHadiah;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Hadiah> hadiahList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_poin_saya,container,false);

        swipeRefreshLayout = v.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();
    }

    private void getData() {
        Webservice.getService(getActivity()).getAllHadiah().enqueue(new Callback<List<Hadiah>>() {
            @Override
            public void onResponse(Call<List<Hadiah>> call, Response<List<Hadiah>> response) {
                hadiahList = response.body();
                listHadiah =(ListView) v.findViewById(R.id.listHadiah);
                HadiahListAdapter adapter = new HadiahListAdapter(getActivity(), hadiahList);
                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,items);
                listHadiah.setAdapter(adapter);


                listHadiah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView textView = (TextView) view.findViewById(R.id.text1);
                        System.out.println(textView.getText().toString());
                        System.out.println("HAHA");
                    }

                });
            }

            @Override
            public void onFailure(Call<List<Hadiah>> call, Throwable t) {

            }
        });
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
