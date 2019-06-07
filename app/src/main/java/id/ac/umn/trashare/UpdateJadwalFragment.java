package id.ac.umn.trashare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class UpdateJadwalFragment extends Fragment {
    private String datePick;
    TextView date;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_update_jadwal, container, false);
        date = (TextView) v.findViewById(R.id.date);

        CalendarView mCalender = (CalendarView) v.findViewById(R.id.calenderview);
        mCalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                GregorianCalendar getDate = new GregorianCalendar(year, month, dayOfMonth);
                datePick = dayOfMonth + "/" + (month+1) + "/" + year;
                date.setText(datePick);
            }
        });

        Button updateButton = (Button) v.findViewById(R.id.btn_update_tanggal);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Update Jadwal Penimbangan");
    }
}