package id.ac.umn.trashare;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        getActivity().setTitle("Menu Login");
    }

    @Override
    public void onStart() {
        super.onStart();

        final EditText username = (EditText) getActivity().findViewById(R.id.username_edit);
        final EditText password = (EditText) getActivity().findViewById(R.id.password_edit);
        Button loginBtn = (Button) getActivity().findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("member") && password.getText().toString().equals("member")){
                    Intent i = new Intent(getActivity(), MemberActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);
                    //getActivity().getFragmentManager().beginTransaction().remove(LoginFragment.this).commit();
                    getActivity().finish();
                }
                else if(username.getText().toString().equals("pengurus") && password.getText().toString().equals("pengurus")){
                    Intent i = new Intent(getActivity(), PengurusActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);
                    //getActivity().getFragmentManager().beginTransaction().remove(LoginFragment.this).commit();
                    getActivity().finish();
                }
                else if(username.getText().toString().equals("yayasan") && password.getText().toString().equals("yayasan")){
                    Intent i = new Intent(getActivity(), YayasanActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0,0);
                    //getActivity().getFragmentManager().beginTransaction().remove(LoginFragment.this).commit();
                    getActivity().finish();
                }
                else{
                    Toast.makeText(getActivity(), "Username atau password tidak terdaftar!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button btnDaftar = (Button) getActivity().findViewById(R.id.btnRegister);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),SignUpActivity.class);
                startActivity(i);
            }
        });
    }
}
