package com.example.shivani.tryocr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class UserInfo2 extends AppCompatActivity {

    EditText e;

    TextView PersonName, NumberPlate, Address;

    DatabaseReference databasePerson;


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info2);


        e = (EditText) findViewById(R.id.editText1);

        PersonName = (TextView) findViewById(R.id.info1);
        NumberPlate = (TextView) findViewById(R.id.info2);
        Address = (TextView) findViewById(R.id.info3);

        button = (Button) findViewById(R.id.button1);


        databasePerson = FirebaseDatabase.getInstance().getReference();

        databasePerson.child("MH 24 AA 1111").setValue(new Person("TANMAY", "LATUR", "MH 24 AA 1111"));
        databasePerson.child("MH 34 BB 2222").setValue(new Person("SHIVANI", "CHANDRAPUR", "MH 34 BB 2222"));
        databasePerson.child("MH 12 CC 3333").setValue(new Person("ATHARVA", "PUNE", "MH 12 CC 3333"));
        databasePerson.child("MH 15 DD 4444").setValue(new Person("MOHIT", "NASHIK", "MH 15 DD 4444"));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String n = e.getText().toString();
                // PersonName.setText(n);

                databasePerson.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int flag = 0;
                        for (DataSnapshot personSnapshot : dataSnapshot.getChildren()) {
                            Person p = personSnapshot.getValue(Person.class);

                            if (p.car_number.equals(n)) {
                                PersonName.setText(p.getName());
                                Address.setText(p.getLocation());
                                NumberPlate.setText(p.getCar_number());
                                flag = 1;
                            }
                        }
                        if (flag == 0) {
                            PersonName.setText("NOT FOUND");
                            Address.setText("NOT FOUND");
                            NumberPlate.setText("NOT FOUND");
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}