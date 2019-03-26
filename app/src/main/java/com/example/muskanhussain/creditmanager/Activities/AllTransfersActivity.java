package com.example.muskanhussain.creditmanager.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.muskanhussain.creditmanager.Data.DatabaseHandler;
import com.example.muskanhussain.creditmanager.Model.Transfer;
import com.example.muskanhussain.creditmanager.Model.User;
import com.example.muskanhussain.creditmanager.R;
import com.example.muskanhussain.creditmanager.UI.TransferRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllTransfersActivity extends AppCompatActivity {
    private List<Transfer> transfers;
    private DatabaseHandler db;
    private RecyclerView recyclerView;
    private TransferRecyclerViewAdapter adapter;
    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transfers);
        db = new DatabaseHandler(this);
        recyclerView = findViewById(R.id.transferRV);
        emptyText = findViewById(R.id.emptyList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        transfers = new ArrayList<>();
        transfers = db.getAllTransfers();
        if(transfers.size() == 0) {
            emptyText.setVisibility(View.VISIBLE);
        }

        else {
            emptyText.setVisibility(View.INVISIBLE);
        }
        adapter = new TransferRecyclerViewAdapter(this, transfers);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
