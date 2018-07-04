package id.ac.umn.trashare;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import id.ac.umn.trashare.models.Sampah;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 5/7/2018.
 */

public class UpdateTipeHargaSampahFragment extends Fragment {
    ListView listSampah;
    List<Sampah> sampahList;
    SwipeRefreshLayout swipeRefreshSampah;
    SampahListAdapter adapter;
    ProgressDialog pdLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_update_tipe_harga_sampah, container, false);

        listSampah =(ListView) v.findViewById(R.id.listSampah);
        swipeRefreshSampah = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshSampah);
        swipeRefreshSampah.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshSampah.setRefreshing(false);
            }
        });

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Tipe dan Harga Sampah");
        pdLoading = new ProgressDialog(getActivity());

        pdLoading.setMessage("Please wait...");
        pdLoading.setTitle("\tLoading...");
        pdLoading.setCancelable(false);

        getData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    private void getData(){
        pdLoading.show();
        Webservice.getService(getActivity()).getAllSampah().enqueue(new Callback<List<Sampah>>() {
            @Override
            public void onResponse(Call<List<Sampah>> call, Response<List<Sampah>> response) {

                pdLoading.dismiss();

                sampahList = response.body();

                adapter = new SampahListAdapter(getActivity(), sampahList);
                listSampah.setAdapter(adapter);

                listSampah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), DetailBerandaActivity.class);
                        intent.putExtra("idSampah", sampahList.get(i).idSampah);
                        intent.putExtra("nama", sampahList.get(i).namaSampah);
                        intent.putExtra("tipe", sampahList.get(i).tipeSampah);
                        intent.putExtra("hargaNasabah", sampahList.get(i).hargaBeliNasabah);
                        intent.putExtra("hargaLapak", sampahList.get(i).hargaBeliLapak);

                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Sampah>> call, Throwable t) {
                pdLoading.dismiss();
            }
        });
    }
}
