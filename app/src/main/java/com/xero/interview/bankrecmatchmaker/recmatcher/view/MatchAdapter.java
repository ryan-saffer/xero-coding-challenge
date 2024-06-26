package com.xero.interview.bankrecmatchmaker.recmatcher.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xero.interview.bankrecmatchmaker.recmatcher.MatcherViewModel;
import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem;
import com.xero.interview.bankrecmatchmaker.R;

import java.util.ArrayList;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckedListItem checkedListItem;

        private final TextView mainText;
        private final TextView total;
        private final TextView subtextLeft;
        private final TextView subtextRight;

        public ViewHolder(CheckedListItem itemView) {
            super(itemView);

            checkedListItem = itemView;

            mainText = itemView.findViewById(R.id.text_main);
            total = itemView.findViewById(R.id.text_total);
            subtextLeft = itemView.findViewById(R.id.text_sub_left);
            subtextRight = itemView.findViewById(R.id.text_sub_right);
        }

        public void bind(MatchItem matchItem, Boolean isChecked) {
            checkedListItem.setChecked(isChecked);

            mainText.setText(matchItem.getPaidTo());
            total.setText(String.format(Float.toString(matchItem.getTotal())));
            subtextLeft.setText(matchItem.getTransactionDate());
            subtextRight.setText(matchItem.getDocType());
            checkedListItem.setMatchItem(matchItem);
        }
    }

    private final MatcherViewModel viewModel;
    private List<MatchItem> matchItems = new ArrayList<>();

    public MatchAdapter(MatcherViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void updateMatchItems(List<MatchItem> items) {
        this.matchItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CheckedListItem listItem = (CheckedListItem) layoutInflater.inflate(R.layout.list_item_match, parent, false);
        listItem.setViewModel(this.viewModel);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MatchItem matchItem = matchItems.get(position);
        holder.bind(matchItem, this.viewModel.isItemSelected(matchItem));
    }

    @Override
    public int getItemCount() {
        return matchItems.size();
    }

}
