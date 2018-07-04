package id.ac.umn.trashare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
    ProgressDialog pdLoading;

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

        pdLoading = new ProgressDialog(getActivity());

        pdLoading.setMessage("Please wait...");
        pdLoading.setTitle("\tLoading...");
        pdLoading.setCancelable(false);

        getData();
    }

    private void getData() {
        pdLoading.show();
        Webservice.getService(getActivity()).getAllHadiah().enqueue(new Callback<List<Hadiah>>() {
            @Override
            public void onResponse(Call<List<Hadiah>> call, Response<List<Hadiah>> response) {
                pdLoading.dismiss();
                hadiahList = response.body();
                listHadiah =(ListView) v.findViewById(R.id.listHadiah);
                HadiahListAdapter adapter = new HadiahListAdapter(getActivity(), hadiahList);

                listHadiah.setAdapter(adapter);

                listHadiah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(getActivity(), DetailRedeemPrizeActivity.class);
                        intent.putExtra("name", hadiahList.get(i).namaHadiah);
                        intent.putExtra("poin", String.valueOf(hadiahList.get(i).poin));
                        intent.putExtra("valid", hadiahList.get(i).periodeTukar);
                        intent.putExtra("sponsor", hadiahList.get(i).sponsor);
                        intent.putExtra("desc", hadiahList.get(i).deskripsiHadiah);
                        intent.putExtra("img", hadiahList.get(i).fotoHadiah);
                        startActivity(intent);
                    }

                });
            }

            @Override
            public void onFailure(Call<List<Hadiah>> call, Throwable t) {
                pdLoading.dismiss();
            }
        });
    }
}
