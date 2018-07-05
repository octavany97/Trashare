package id.ac.umn.trashare;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.models.Kegiatan;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 5/8/2018.
 */

public class PengaturanHadiahFragment extends Fragment {
    ListView listHadiah;
    ProgressDialog pdLoading;
    HadiahListAdapter adapter;
    List<Hadiah> hadiahList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_menu_pengaturan_hadiah, container, false);
        View v = inflater.inflate(R.layout.fragment_menu_pengaturan_hadiah, container, false);


        listHadiah =(ListView) v.findViewById(R.id.listHadiah);


        listHadiah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailPrizeYayasanActivity.class);
                TextView textView = (TextView) view.findViewById(R.id.text1);
                System.out.println(textView.getText().toString());
                startActivity(intent);
            }

        });

        Button btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddPrizeActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hadiah");

        pdLoading = new ProgressDialog(getActivity());

        pdLoading.setMessage("Please wait...");
        pdLoading.setTitle("\tLoading...");
        pdLoading.setCancelable(false);

        getData();
    }
    private void getData(){
        pdLoading.show();
        Webservice.getService(getActivity()).getAllHadiah().enqueue(new Callback<List<Hadiah>>() {
            @Override
            public void onResponse(Call<List<Hadiah>> call, Response<List<Hadiah>> response) {
                pdLoading.dismiss();
                hadiahList = response.body();

                adapter = new HadiahListAdapter(getActivity(), hadiahList);

                listHadiah.setAdapter(adapter);

                listHadiah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), DetailPrizeYayasanActivity.class);
                        intent.putExtra("judul", hadiahList.get(i).namaHadiah);
                        intent.putExtra("deskripsi", hadiahList.get(i).deskripsiHadiah);
                        intent.putExtra("periode", hadiahList.get(i).periodeTukar);
                        intent.putExtra("sponsor", hadiahList.get(i).sponsor);
                        intent.putExtra("poin", hadiahList.get(i).poin);
                        intent.putExtra("foto", hadiahList.get(i).fotoHadiah);
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
