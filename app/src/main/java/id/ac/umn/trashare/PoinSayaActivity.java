package id.ac.umn.trashare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PoinSayaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poin_saya);

        ListView listView =(ListView) findViewById(R.id.listHadiah);
        final String[] items = new String[] {"Hadiah 1", "Hadiah 2", "Hadiah 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), DetailRedeemPrizeActivity.class);
                //System.out.println(items[i].toString());
                intent.putExtra("name", items[i].toString());
                startActivity(intent);
                // Toast.makeText(getActivity().getApplicationContext(),items[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
