package id.ac.umn.trashare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

public class UpdateJadwalFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_update_jadwal, container, false);


        CalendarView mCalender = (CalendarView) v.findViewById(R.id.calenderview);
//        mCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                String date = dayOfMonth + "/" + month + "/" + year;
//            }
//        });

//        TextView textView = (TextView) v.findViewById(R.id.date);
//        textView.setText(date);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Update Jadwal Penimbangan");
    }
}
