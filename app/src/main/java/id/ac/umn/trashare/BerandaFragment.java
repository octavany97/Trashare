package id.ac.umn.trashare;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.models.Kegiatan;
import id.ac.umn.trashare.models.Sampah;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 5/5/2018.
 */

public class BerandaFragment extends Fragment{
    ListView listBeranda;
    SwipeRefreshLayout swipeBeranda;
    EventListAdapter adapter;
    List<Kegiatan> listKegiatan;
    ProgressDialog pdLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_menu_beranda, container, false);

        listBeranda = (ListView) v.findViewById(R.id.listBeranda);
        swipeBeranda = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshBeranda);

        swipeBeranda.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeBeranda.setRefreshing(false);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Beranda");

        pdLoading = new ProgressDialog(getActivity());

        pdLoading.setMessage("Please wait...");
        pdLoading.setTitle("\tLoading...");
        pdLoading.setCancelable(false);

        getData();
    }

    private void getData(){
        pdLoading.show();
        Webservice.getService(getActivity()).getAllKegiatan().enqueue(new Callback<List<Kegiatan>>() {
            @Override
            public void onResponse(Call<List<Kegiatan>> call, Response<List<Kegiatan>> response) {
                pdLoading.dismiss();
                listKegiatan = response.body();
                adapter = new EventListAdapter(getActivity(), listKegiatan);
                listBeranda.setAdapter(adapter);

                listBeranda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), DetailBerandaActivity.class);
                        intent.putExtra("judul", listKegiatan.get(i).namaKegiatan);
                        intent.putExtra("tanggal", listKegiatan.get(i).tanggalKegiatan);
                        intent.putExtra("deskripsi", listKegiatan.get(i).deskripsiKegiatan);
                        intent.putExtra("foto", listKegiatan.get(i).fotoKegiatan);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Kegiatan>> call, Throwable t) {
                pdLoading.dismiss();
            }
        });
    }
}