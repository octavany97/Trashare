package id.ac.umn.trashare;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import id.ac.umn.trashare.models.BankSampah;
import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.models.Kegiatan;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 5/14/2018.
 */

public class EventPengurusFragment extends Fragment {

    View v;
    private String access;
    private ListView listEvent;
    private SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog pdLoading;

    private List<Kegiatan> kegiatanList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_menu_event_pengurus, container, false);

        swipeRefreshLayout = v.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

       /* ListView listEvent =(ListView) v.findViewById(R.id.listEvent);
        final String[][] items = new String[][] {{"Jalan Sehat Bank Sampah", "Bank Sampah Melati"}, {"Senam Bersama Bank Sampah", "Yayasan Bank Sampah Melati Bersih"}};
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), items, 4);

        listEvent.setAdapter(adapter);
        listEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailEventPengurusActivity.class);
                //System.out.println(items[i].toString());
                intent.putExtra("name", items[i][0].toString());
                intent.putExtra("penyelenggara", items[i][1].toString());
                intent.putExtra("deskripsi", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
                startActivity(intent);
                // Toast.makeText(getActivity().getApplicationContext(),items[i], Toast.LENGTH_SHORT).show();
            }
        });*/

        Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddEventPengurusActivity.class);
                startActivity(intent);
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
        Webservice.getService(getActivity()).getAllKegiatan().enqueue(new Callback<List<Kegiatan>>() {
            @Override
            public void onResponse(Call<List<Kegiatan>> call, Response<List<Kegiatan>> response) {
                if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
                pdLoading.dismiss();
                kegiatanList = response.body();
                listEvent =(ListView) v.findViewById(R.id.listEvent);
                EventListAdapter adapter = new EventListAdapter(getActivity(), kegiatanList);

                listEvent.setAdapter(adapter);

                listEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(getActivity(), DetailEventPengurusActivity.class);
                        intent.putExtra("id", String.valueOf(kegiatanList.get(i).idKegiatan));
                        intent.putExtra("name", kegiatanList.get(i).namaKegiatan);
                        intent.putExtra("location", kegiatanList.get(i).tempatKegiatan);
                        intent.putExtra("date", kegiatanList.get(i).tanggalKegiatan);
                        intent.putExtra("desc", kegiatanList.get(i).deskripsiKegiatan);
                        startActivity(intent);
                    }

                });
            }

            @Override
            public void onFailure(Call<List<Kegiatan>> call, Throwable t) {
                pdLoading.dismiss();
                if (swipeRefreshLayout.isRefreshing()) swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        swipeRefreshLayout.setRefreshing(true);
        getData();
    }
}
