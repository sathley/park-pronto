package edu.umbc.parkpronto.model;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sushantathley on 9/28/16.
 */

public class PermitAdapter extends RecyclerView.Adapter<PermitAdapter.ViewHolder> {

    private ParkingPermit[] data;
    public PermitAdapter(ParkingPermit[] myDataset) {
        data = myDataset;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}
