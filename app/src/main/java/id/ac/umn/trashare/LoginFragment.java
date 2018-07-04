package id.ac.umn.trashare;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import id.ac.umn.trashare.models.BankSampah;
import id.ac.umn.trashare.models.Member;
import id.ac.umn.trashare.models.Yayasan;
import id.ac.umn.trashare.utils.Webservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 5/5/2018.
 */

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Login");
    }

    @Override
    public void onStart() {
        super.onStart();

        final EditText username = (EditText) getActivity().findViewById(R.id.username_edit);
        final EditText password = (EditText) getActivity().findViewById(R.id.password_edit);
        String [] values = {"Yayasan","Pengurus","Anggota"};
        final Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner_wilayah);
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(LTRadapter);

        Button cardLogin = (Button) getActivity().findViewById(R.id.cardLogin);

        cardLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinner.getSelectedItem().equals("Anggota")){
                    Map<String, String> body = new HashMap<>();
                    body.put("email", username.getText().toString());
                    body.put("password", password.getText().toString());
                    Webservice.getService(getActivity()).loginMember(body).enqueue(new Callback<Member>() {
                        @Override
                        public void onResponse(Call<Member> call, Response<Member> response) {
                            if(response.body() != null){
                                SharedPreferences.Editor token = getActivity().getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE).edit();
                                token.putString("sessionToken", response.body().sessionToken);
                                token.commit();

                                Intent i = new Intent(getActivity(), MemberActivity.class);
                                startActivity(i);
                                ((Activity) getActivity()).overridePendingTransition(0,0);
                                //getActivity().getFragmentManager().beginTransaction().remove(LoginFragment.this).commit();
                                getActivity().finish();
                            }
                            else{
                                Toast.makeText(getActivity(), "Username atau password tidak terdaftar!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Member> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else if(spinner.getSelectedItem().equals("Pengurus")){
                    Map<String, String> body = new HashMap<>();
                    body.put("email", username.getText().toString());
                    body.put("password", password.getText().toString());
                    Webservice.getService(getActivity()).loginBankSampah(body).enqueue(new Callback<BankSampah>() {
                        @Override
                        public void onResponse(Call<BankSampah> call, Response<BankSampah> response) {
                            if(response.body() != null){
                                SharedPreferences.Editor token = getActivity().getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE).edit();
                                token.putString("sessionToken", response.body().sessionToken);
                                token.commit();

                                Intent i = new Intent(getActivity(), PengurusActivity.class);
                                startActivity(i);
                                ((Activity) getActivity()).overridePendingTransition(0,0);
                                //getActivity().getFragmentManager().beginTransaction().remove(LoginFragment.this).commit();
                                getActivity().finish();
                            }
                            else{
                                Toast.makeText(getActivity(), "Username atau password tidak terdaftar!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BankSampah> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if(spinner.getSelectedItem().equals("Yayasan")) {
                    Map<String, String> body = new HashMap<>();
                    body.put("email", username.getText().toString());
                    body.put("password", password.getText().toString());
                    Webservice.getService(getActivity()).loginYayasan(body).enqueue(new Callback<Yayasan>() {
                        @Override
                        public void onResponse(Call<Yayasan> call, Response<Yayasan> response) {
                            if(response.body() != null){
                                SharedPreferences.Editor token = getActivity().getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE).edit();
                                token.putString("sessionToken", response.body().sessionToken);
                                token.commit();

                                Intent i = new Intent(getActivity(), YayasanActivity.class);
                                startActivity(i);
                                ((Activity) getActivity()).overridePendingTransition(0, 0);
                                //getActivity().getFragmentManager().beginTransaction().remove(LoginFragment.this).commit();
                                getActivity().finish();
                            }
                            else {
                                Toast.makeText(getActivity(), "Username atau password tidak terdaftar!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Yayasan> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        Button cardSignUp = (Button) getActivity().findViewById(R.id.cardRegister);
        cardSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}
