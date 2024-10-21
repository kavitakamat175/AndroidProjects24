package com.example.firebasecrudapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    EditText etID, etName, etPhone, etAddress;
    String id, name, ph, email;
    DatabaseReference dbRef;
    Employee emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etID = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        emp = new Employee();
        etID.requestFocus();

    }

    public void addRecord(View view) {
        id = etID.getText().toString();
        name = etName.getText().toString();
        ph = etPhone.getText().toString();
        email = etAddress.getText().toString();


        dbRef = FirebaseDatabase.getInstance().getReference().child("Employee");
        try {
            if (TextUtils.isEmpty(etID.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter ID",
                        Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(etName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Name",
                        Toast.LENGTH_LONG).show();
            else if (TextUtils.isEmpty(etPhone.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter Phone Number",
                        Toast.LENGTH_LONG).show();

        else {
                emp.setId(id);
                emp.setName(name);
                emp.setPhone(ph);
                emp.setAddress(email);
                dbRef.child(id).setValue(emp);
                Toast.makeText(getApplicationContext(), "Record Added",Toast.LENGTH_LONG).show();
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
        id = etID.getText().toString();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(id);
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

    public void deleteRecord(View view) {
        id = etID.getText().toString();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(id);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    dbRef.removeValue();
                    Toast.makeText(getApplicationContext(), "Record Deleted",
                            Toast.LENGTH_LONG).show();
                    etID.setText("");
                    etName.setText("");
                    etAddress.setText("");
                    etPhone.setText("");
                    etID.requestFocus();

                } else {
                    Toast.makeText(getApplicationContext(), "No such record",
                            Toast.LENGTH_LONG).show();
                    etID.requestFocus();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateRecord(View view) {
        id = etID.getText().toString();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(id);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    emp.setName(etName.getText().toString().trim());
                    emp.setAddress(etAddress.getText().toString().trim());
                    emp.setPhone(etPhone.getText().toString().trim());

                    dbRef.setValue(emp);
                    Toast.makeText(getApplicationContext(), "Data Updated",
                            Toast.LENGTH_LONG).show();
                    etID.setText("");
                    etName.setText("");
                    etAddress.setText("");
                    etPhone.setText("");
                    etID.requestFocus();

                } else {
                    Toast.makeText(getApplicationContext(), "No data to update",
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
