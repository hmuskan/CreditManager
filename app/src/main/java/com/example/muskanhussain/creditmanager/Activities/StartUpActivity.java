package com.example.muskanhussain.creditmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.muskanhussain.creditmanager.R;

public class StartUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button allUsers, allTransfers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        allUsers = findViewById(R.id.all_users_button);
        allTransfers = findViewById(R.id.all_transfers_button);
        allUsers.setOnClickListener(this);
        allTransfers.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.all_users_button : {
                startActivity(new Intent(this, AllUsersActivity.class));
            } break;

            case R.id.all_transfers_button : {
                startActivity(new Intent(this, AllTransfersActivity.class));
            }break;
        }
    }
}
