package id.ac.umn.trashare;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailRedeemPrizeActivity extends AppCompatActivity {

    private String name, sponsor, valid, poin, desc, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_redeem_prize);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = getIntent().getStringExtra("name");
        sponsor = getIntent().getStringExtra("sponsor");
        valid = getIntent().getStringExtra("valid");
        poin = getIntent().getStringExtra("poin");
        desc = getIntent().getStringExtra("desc");
        img = getIntent().getStringExtra("img");

        TextView txtNama = (TextView) findViewById(R.id.txtNama);
        txtNama.setText(name);

        TextView txtSponsor = (TextView) findViewById(R.id.txtSponsor);
        txtSponsor.setText(sponsor);

        TextView txtValid = (TextView) findViewById(R.id.txtValid);
        txtValid.setText(valid);

        TextView txtPoin= (TextView) findViewById(R.id.txtPoin);
        txtPoin.setText(poin);

        TextView txtDesc = (TextView) findViewById(R.id.txtDescription);
        txtDesc.setText(desc);

        byte[] imageAsBytes = Base64.decode(img, Base64.DEFAULT);
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        Button btnRedeem = (Button) findViewById(R.id.btnRedeem);
        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id  == android.R.id.home)
        {
            Intent i = new Intent(getBaseContext(), PoinMemberActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
