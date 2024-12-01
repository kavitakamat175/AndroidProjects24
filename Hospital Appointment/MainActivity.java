package com.example.hospitalappointment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText etID, etName, etPhone, etAddress;
    String name, ph, address;
    int id;
    static int counter;
    DatabaseReference dbRef;
    Patient patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etID = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        patient = new Patient();
        etName.requestFocus();

    }

    public void addRecord(View view) {
        name = etName.getText().toString();
        ph = etPhone.getText().toString();
        address = etAddress.getText().toString();


        dbRef = FirebaseDatabase.getInstance().getReference().child("Patient");
        try {
            if (TextUtils.isEmpty(etName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Name",
                        Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(etPhone.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Phone Number",
                        Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(etAddress.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter address",
                        Toast.LENGTH_LONG).show();

            else {
                patient.setId(++counter);
                patient.setName(name);
                patient.setPhone(ph);
                patient.setAddress(address);
                id = patient.getId();
                dbRef.child(String.valueOf(id)).setValue(patient);
                Toast.makeText(getApplicationContext(), "Patient registered and you have been assigned ID as " + id,Toast.LENGTH_LONG).show();
                etID.setText("");
                etName.setText("");
                etPhone.setText("");
                etAddress.setText("");
                etID.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRecord(View view) {

        dbRef = FirebaseDatabase.getInstance().getReference().child("Patient").child(String.valueOf(id));
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    etName.setText(snapshot.child("name").getValue().toString());
                    etAddress.setText(snapshot.child("address").getValue().toString());
                    etPhone.setText(snapshot.child("phone").getValue().toString());
                    etID.requestFocus();

                } else {
                    Toast.makeText(getApplicationContext(), "No data to display",
                            Toast.LENGTH_LONG).show();
                    etID.requestFocus();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}