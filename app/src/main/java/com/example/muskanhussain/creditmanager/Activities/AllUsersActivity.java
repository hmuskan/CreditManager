package com.example.muskanhussain.creditmanager.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.muskanhussain.creditmanager.Data.DatabaseHandler;
import com.example.muskanhussain.creditmanager.Model.User;
import com.example.muskanhussain.creditmanager.R;
import com.example.muskanhussain.creditmanager.UI.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllUsersActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;
    private List<User> users;
    private RecyclerView rv;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        databaseHandler = new DatabaseHandler(this);
        rv = findViewById(R.id.listRV);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        addInfoToDB();
        users = new ArrayList<>();

        users = databaseHandler.getAllUsers();


        adapter = new RecyclerViewAdapter(this, users);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void addInfoToDB() {
        int count = databaseHandler.getUserCount();
        if(count == 0) {
            User user = new User();
            user.setName("Ana");
            user.setEmail("ana@abc.com");
            user.setCurrCredit(200);
            databaseHandler.addUser(user);
            User user1 = new User();
            user1.setName("Ben");
            user1.setEmail("ben@abc.com");
            user1.setCurrCredit(300);
            databaseHandler.addUser(user1);
            User user2 = new User();
            user2.setName("Cynthia");
            user2.setEmail("cyn@abc.com");
            user2.setCurrCredit(150);
            databaseHandler.addUser(user2);
            User user3 = new User();
            user3.setName("Dave");
            user3.setEmail("dave@abc.com");
            user3.setCurrCredit(30);
            databaseHandler.addUser(user3);
            User user4 = new User();
            user4.setName("Eugene");
            user4.setEmail("eugn@abc.com");
            user4.setCurrCredit(350);
            databaseHandler.addUser(user4);
            User user5 = new User();
            user5.setName("Fred");
            user5.setEmail("fred@abc.com");
            user5.setCurrCredit(1050);
            databaseHandler.addUser(user5);
            User user6 = new User();
            user6.setName("Gaston");
            user6.setEmail("gaston@abc.com");
            user6.setCurrCredit(990);
            databaseHandler.addUser(user6);
            User user7 = new User();
            user7.setName("Harry");
            user7.setEmail("harry@abc.com");
            user7.setCurrCredit(876);
            databaseHandler.addUser(user7);
            User user8 = new User();
            user8.setName("Indira");
            user8.setEmail("indira@abc.com");
            user8.setCurrCredit(550);
            databaseHandler.addUser(user8);
            User user9 = new User();
            user9.setName("Jill");
            user9.setEmail("jill@abc.com");
            user9.setCurrCredit(601);
            databaseHandler.addUser(user9);
        }
    }
}
