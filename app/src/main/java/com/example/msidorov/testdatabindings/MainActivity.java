package com.example.msidorov.testdatabindings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;
import com.example.msidorov.testdatabindings.data.database.AppDatabase;
import com.example.msidorov.testdatabindings.data.entity.UserEntity;
import com.example.msidorov.testdatabindings.domain.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();

        testDatabase();

        // SQLiteStudioService.instance().setPort(9999);
        SQLiteStudioService.instance().start(this);
    }

    private void testDatabase(){
        UserEntity user = new UserEntity();
        user.setLogin("test");
        user.setUserName("test user");
        user.setDescription("descriotuon");
        user.setPassword("12345");
        AppDatabase.getInstance().userDao().insert(user);

        List<UserEntity> users =  AppDatabase.getInstance().userDao().getUsers();
        String s = "dsdas" + String.valueOf(users.isEmpty());
    }

    @Override
    protected void onStop(){
        SQLiteStudioService.instance().stop();

        super.onStop();
   }

    static void f() {
        final String s = null;
    }

}
