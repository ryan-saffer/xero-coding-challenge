package com.xero.interview.bankrecmatchmaker.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xero.interview.bankrecmatchmaker.MatcherViewModel;
import com.xero.interview.bankrecmatchmaker.model.MatchItem;
import com.xero.interview.bankrecmatchmaker.R;

import java.util.ArrayList;
import java.util.List;

public class FindMatchActivity extends AppCompatActivity {

    public static final String TARGET_MATCH_VALUE = "com.xero.interview.target_match_value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_match);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView matchText = findViewById(R.id.match_text);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_find_match);


        float target = getIntent().getFloatExtra(TARGET_MATCH_VALUE, 10000f);
        matchText.setText(getString(R.string.select_matches, (int) target));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ViewModel init
        MatcherViewModel viewModel = new ViewModelProvider(this).get(MatcherViewModel.class);

        // ViewModel listeners
        viewModel.getItems().observe(this, items -> {
            final MatchAdapter adapter = new MatchAdapter(items);
            recyclerView.setAdapter(adapter);
        });

        viewModel.getSelectedItems().observe(this, selectedItems -> {
            System.out.println(selectedItems);
        });
    }


}
