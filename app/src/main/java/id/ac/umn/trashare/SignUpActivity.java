package id.ac.umn.trashare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import id.ac.umn.trashare.models.Member;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText name = (EditText) findViewById(R.id.editName);
        final EditText email = (EditText) findViewById(R.id.editEmail);
        final EditText password = (EditText) findViewById(R.id.editPassword);

        final Member newMember = new Member(email.getText().toString(), name.getText().toString(), password.getText().toString());
        System.out.println(email.getText().toString());
        System.out.println(name.getText().toString());
        System.out.println(password.getText().toString());

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Webservice.getService(getBaseContext()).createMember(newMember).enqueue(new Callback<id.ac.umn.trashare.models.Member>() {
                    @Override
                    public void onResponse(Call<id.ac.umn.trashare.models.Member> call, Response<id.ac.umn.trashare.models.Member> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<id.ac.umn.trashare.models.Member> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
