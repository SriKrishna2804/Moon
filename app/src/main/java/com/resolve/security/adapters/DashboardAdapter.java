package com.resolve.security.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.resolve.security.R;

// https://www.dev2qa.com/android-cardview-with-image-and-text-example/
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    String[] items = {
            "Visitors",
            "Staff",
            "Register",
            "Resident",
            "Vehicle",
            "Courier",
            "Faculty Booking"
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View carItemView = layoutInflater.inflate(R.layout.dashboard_item, viewGroup, false);
        final TextView carTitleView = carItemView.findViewById(R.id.card_view_image_title);
        final ImageView carImageView = carItemView.findViewById(R.id.card_view_image);
        carImageView.setOnClickListener(v -> {
            String carTitle = carTitleView.getText().toString();
            Toast.makeText(v.getContext(), "You click " + carTitle + " image", Toast.LENGTH_LONG).show();
        });

        // Create and return our custom Car Recycler View Item Holder object.
        ViewHolder ret = new ViewHolder(carItemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Get car item dto in list.
        String carItem = items[position];

        if(carItem != null) {
            // Set car item title.
            viewHolder.getTvTitle().setText(carItem);
            // Set car image resource id.
            viewHolder.getIvImage().setImageResource(R.drawable.video_play_icon);
        }
    }

    @Override
    public int getItemCount() {
        return items.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle = null;
        private ImageView ivImage = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (itemView != null) {
                tvTitle = itemView.findViewById(R.id.card_view_image_title);
                ivImage = itemView.findViewById(R.id.card_view_image);
            }
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public ImageView getIvImage() {
            return ivImage;
        }
    }
}
