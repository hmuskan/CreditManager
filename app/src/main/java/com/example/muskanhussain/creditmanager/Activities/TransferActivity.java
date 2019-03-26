package com.example.muskanhussain.creditmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.muskanhussain.creditmanager.Data.DatabaseHandler;
import com.example.muskanhussain.creditmanager.Model.Transfer;
import com.example.muskanhussain.creditmanager.Model.User;
import com.example.muskanhussain.creditmanager.R;

import java.util.ArrayList;
import java.util.List;

public class TransferActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button transferButton;
    private Bundle extras;
    private User currUser;
    private List<User> userList, transferList;
    private DatabaseHandler db;
    private EditText creditTransfer;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        spinner = findViewById(R.id.spinner);
        transferButton = findViewById(R.id.transfer_activity_button);
        creditTransfer = findViewById(R.id.transferCredits);
        db = new DatabaseHandler(this);
        userList = db.getAllUsers();
        transferList = new ArrayList<>();
        extras = getIntent().getExtras();
        if(extras != null) {
            currUser = new User();
            currUser.setId(extras.getInt("tr_id"));
            currUser.setName(extras.getString("tr_name"));
            currUser.setEmail(extras.getString("tr_email"));
            currUser.setCurrCredit(extras.getInt("tr_currCredit"));

            for(User u : userList) {
                if(u.getId() != currUser.getId()) {
                    User user = new User();
                    user.setName(u.getName());
                    user.setEmail(u.getEmail());
                    user.setId(u.getId());
                    user.setCurrCredit(u.getCurrCredit());
                    transferList.add(user);
                }
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, transferList);
            spinner.setAdapter(adapter);
            transferButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!creditTransfer.getText().toString().isEmpty()) {
                        int credits = Integer.parseInt(creditTransfer.getText().toString());
                        if(credits <= 0 || credits > currUser.getCurrCredit()) {
                            Toast.makeText(TransferActivity.this, "Invalid Credit Amount", Toast.LENGTH_LONG).show();
                        }

                        else {
                            User toUser = (User)((Spinner)findViewById(R.id.spinner)).getSelectedItem();
                            User userf = new User();
                            User usert = new User();
                            userf.setName(currUser.getName());
                            userf.setEmail(currUser.getEmail());
                            userf.setId(currUser.getId());
                            userf.setCurrCredit(currUser.getCurrCredit() - credits);
                            usert.setName(toUser.getName());
                            usert.setEmail(toUser.getEmail());
                            usert.setId(toUser.getId());
                            usert.setCurrCredit(toUser.getCurrCredit() + credits);
                            Transfer transfer = new Transfer();
                            transfer.setFromId(userf.getId());
                            transfer.setToId(usert.getId());
                            transfer.setCredit(credits);
                            db.updateCredit(userf);
                            db.updateCredit(usert);
                            db.addTransfer(transfer);
                            startActivity(new Intent(TransferActivity.this, AllUsersActivity.class));
                            finish();
                        }
                    }
                }
            });
        }
        /*List<User> userList = new DatabaseHandler(this).getAllUsers();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userList);
        spinner.setAdapter(adapter);
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TransferActivity.this, String.valueOf(((User)((Spinner)findViewById(R.id.spinner)).getSelectedItem()).getId()) , Toast.LENGTH_LONG).show();
            }
        });*/


    }
}
