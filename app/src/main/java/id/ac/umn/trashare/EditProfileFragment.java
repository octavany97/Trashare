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
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Octavany on 7/1/2018.
 */

public class EditProfileFragment extends Fragment {
    ImageView foto;
    EditText id_user, nama_user, email_user, nama_bs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Edit Profile");
    }

    @Override
    public void onStart() {
        super.onStart();

        foto = (ImageView) getActivity().findViewById(R.id.profile_image);
        id_user = (EditText) getActivity().findViewById(R.id.id_user);
        nama_user = (EditText) getActivity().findViewById(R.id.nama_user);
        email_user = (EditText) getActivity().findViewById(R.id.email_user);
        nama_bs = (EditText) getActivity().findViewById(R.id.nama_bs);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Button saveButton = (Button) getActivity().findViewById(R.id.save_profile);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }
}
