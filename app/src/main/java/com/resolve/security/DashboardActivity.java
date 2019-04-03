package com.resolve.security;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.resolve.security.adapters.DashboardAdapter;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView rView = findViewById(R.id.rvDashboard);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        DashboardAdapter adapter = new DashboardAdapter();
        rView.setAdapter(adapter);
    }
}
