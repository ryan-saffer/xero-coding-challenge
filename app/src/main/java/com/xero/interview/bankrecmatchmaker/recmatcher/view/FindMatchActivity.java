package com.xero.interview.bankrecmatchmaker.recmatcher.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xero.interview.bankrecmatchmaker.recmatcher.MatcherViewModel;
import com.xero.interview.bankrecmatchmaker.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FindMatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_match);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView matchText = findViewById(R.id.match_text);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.title_find_match);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MatcherViewModel viewModel = new ViewModelProvider(this).get(MatcherViewModel.class);

        final MatchAdapter adapter = new MatchAdapter(viewModel);
        recyclerView.setAdapter(adapter);

        // Viewmodel listeners
        viewModel.getItems().observe(this, adapter::updateMatchItems);
        viewModel.getSelectedItems().observe(this, items -> {
            adapter.updateSelectedItems(items);
        });
        viewModel.getTargetMatchValue().observe(this, targetMatchValue -> {
            matchText.setText(getString(R.string.select_matches, (int) targetMatchValue.floatValue()));
        });
    }
}
