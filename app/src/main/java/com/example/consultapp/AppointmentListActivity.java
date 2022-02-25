package com.example.consultapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultapp.adapters.AppointmentsAdapter;
import com.example.consultapp.classes.Appointment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppointmentListActivity extends AppCompatActivity {
    public static ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
    String uid;
    DatabaseReference dbAppointments;
    TextView tvDetails;
    ListView lvAppointments;
    AppointmentsAdapter appointmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        tvDetails = findViewById(R.id.tvDetails);
        lvAppointments=findViewById(R.id.lvAppointments);

        getAppointments();
    }

    private void getAppointments() {

        dbAppointments = FirebaseDatabase.getInstance().getReference("appointments");
        dbAppointments.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appointmentArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Appointment appointment = dataSnapshot.getValue(Appointment.class);

                    if (appointment.getStudent().equalsIgnoreCase(uid)) {
                        appointmentArrayList.add(appointment);
                    }
                }

                if (appointmentArrayList.isEmpty()) {
                    tvDetails.setVisibility(View.VISIBLE);
                } else {
                    tvDetails.setVisibility(View.GONE);
                    appointmentsAdapter=new AppointmentsAdapter(AppointmentListActivity.this,appointmentArrayList);
                    lvAppointments.setAdapter(appointmentsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AppointmentListActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}