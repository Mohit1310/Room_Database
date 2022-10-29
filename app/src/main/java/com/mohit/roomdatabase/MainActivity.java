package com.mohit.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText t1,t2,r1;
    private TextView lbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r1 = findViewById(R.id.r1);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
//        data = findViewById(R.id.dataHolder);
        lbl = findViewById(R.id.lbl);

        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                Boolean check = userDao.isExist(Integer.parseInt(r1.getText().toString()));
                if(!check){
                    userDao.insertRecord(new User(Integer.parseInt(r1.getText().toString()),t1.getText().toString(),t2.getText().toString()));
                    r1.setText("");
                    t1.setText("");
                    t2.setText("");
                    lbl.setText("Inserted Successfully");
                }
                else{
                    r1.setText("");
                    t1.setText("");
                    t2.setText("");
                    lbl.setText("Record is existing already");
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
//                UserDao userDao = db.userDao();
//
//                List<User> users = userDao.getAllUsers();
//                String str = "";
//
//                for(User user : users)
//                {
//                    str = str + "\t  " + user.getUid() + " "+ user.getFirstName()+" "+user.getLastName() + "\n\n";
//                }
//                data.setText(str);
                startActivity(new Intent(getApplicationContext(), fetchData.class));
            }
        });
    }
}