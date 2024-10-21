package com.example.databasedemo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextPRN, editTextName, editTextEmail, editTextPhone, editTextCourse;
    Button btnAdd, btnShow, btnUpdate, btnDel;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPRN = findViewById(R.id.etPRN);
        editTextName = findViewById(R.id.etName);
        editTextEmail = findViewById(R.id.etEmail);
        editTextPhone = findViewById(R.id.etPh);
        editTextCourse = findViewById(R.id.etCourse);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDel=findViewById(R.id.btnDelete);
        dbHelper = new DBHelper(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prn = Integer.parseInt(editTextPRN.getText().toString());
                String nm = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String ph = editTextPhone.getText().toString();
                String crc = editTextCourse.getText().toString();
                boolean result = dbHelper.addRecord(prn, nm, email, ph, crc);
                if (result)
                    Toast.makeText(MainActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Sorry Some error", Toast.LENGTH_SHORT).show();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prn = Integer.parseInt(editTextPRN.getText().toString());
                Cursor result = dbHelper.showRecord(prn);
                if (result.getCount() != 0) {
                    result.moveToNext();
                    editTextName.setText(result.getString(1));
                    editTextEmail.setText(result.getString(2));
                    editTextPhone.setText(result.getString(3));
                    editTextCourse.setText(result.getString(4));
                } else {
                    Toast.makeText(MainActivity.this, "Record not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prn = Integer.parseInt(editTextPRN.getText().toString());
                String nm = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String ph = editTextPhone.getText().toString();
                String crc = editTextCourse.getText().toString();
                boolean result = dbHelper.updateRecord(prn, nm, email, ph, crc);
                if (result)
                    Toast.makeText(MainActivity.this, "Record Updated successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Sorry Some error in updating record", Toast.LENGTH_SHORT).show();
            }

        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prnno=Integer.parseInt(editTextPRN.getText().toString());
                boolean result=dbHelper.deleteRecord(prnno);
                if(result)
                    Toast.makeText(MainActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Some error in deletion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}