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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.ac.umn.trashare.models.BankSampah;
import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 5/5/2018.
 */

public class ListBankSampahFragment extends Fragment {

    View v;
    private String access;
    private ListView listBankSampah;
    private SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog pdLoading;

    private List<BankSampah> bankSampahList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         access = this.getArguments().getString("access");
        v = inflater.inflate(R.layout.fragment_menu_list_bank_sampah, container, false);

        String [] values =
                {"Pamulang","Tangerang Kota","Tangerang Selatan","Legok","Tigaraksa","Kali Deres"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner_lokasi);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);

        swipeRefreshLayout = v.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        /*ListView listView =(ListView) v.findViewById(R.id.bs_list);

        final String[][] items = new String[][] {{"Bukit Pamulang Indah", "Pamulang", "logoputih"}, {"Villa Pamulang", "Pamulang", "logowarna"}, {"Al Falaah III", "Al Falaah", "logowarna2"},{"Puri Bintaro Hijau", "Bintaro", "logoputih"},{"Villa Inti Persada","Persada","logowarna"},
                {"Taman PAUD Cahaya Agung","Cahaya Agung", "logowarna2"},{"RS Griya Pipit VI","Pipit","logoputih"},{"Perigi Baru I","Perigi Baru", "logowarna"},{"Japos Graha Lestari","Graha Lestari","logowarna2"},{"Perigi Baru II","Perigi Baru","logoputih"}};
        ListViewAdapter adapter = new ListViewAdapter(getActivity(), items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailBSMapActivity.class);
                intent.putExtra("access", access);
                intent.putExtra("name", items[i][0]);
                intent.putExtra("location", items[i][1]);
                intent.putExtra("image", items[i][2]);
                startActivity(intent);
            }
        });*/
        return v;

        //return inflater.inflate(R.layout.fragment_menu_list_bank_sampah, container, false);
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
        Webservice.getService(getActivity()).getAllBankSampah().enqueue(new Callback<List<BankSampah>>() {
            @Override
            public void onResponse(Call<List<BankSampah>> call, Response<List<BankSampah>> response) {
                pdLoading.dismiss();
                bankSampahList = response.body();
                listBankSampah =(ListView) v.findViewById(R.id.bs_list);
                BankSampahListAdapter adapter = new BankSampahListAdapter(getActivity(), bankSampahList);
                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,items);
                listBankSampah.setAdapter(adapter);

                listBankSampah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), DetailBSMapActivity.class);
                        intent.putExtra("access", access);
                        intent.putExtra("name", bankSampahList.get(i).namaBankSampah);
                        intent.putExtra("address", bankSampahList.get(i).alamat);
                        intent.putExtra("leader", bankSampahList.get(i).namaKetua);
                        intent.putExtra("phone", bankSampahList.get(i).noTelp);
                        intent.putExtra("email", bankSampahList.get(i).email);
                        intent.putExtra("location", bankSampahList.get(i).wilayah);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<BankSampah>> call, Throwable t) {
                pdLoading.dismiss();
            }
        });


        /*Webservice.getService(getActivity()).getAllBankSampah().enqueue(new Callback<List<BankSampah>>() {
            @Override
            public void onResponse(Call<List<BankSampah>> call, Response<List<BankSampah>> response) {
                bankSampahList = response.body();
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
        });*/
    }
}
