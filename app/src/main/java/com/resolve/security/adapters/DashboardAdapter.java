package com.resolve.security.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.resolve.security.CourierActivity;
import com.resolve.security.R;
import com.resolve.security.StaffActivity;
import com.resolve.security.VisitorActivity;


//Scan
//        Register
//        Visitors
//        Vehicles
//        Courier
//        Facility
//        Monitor
//        Residents

// https://www.dev2qa.com/android-cardview-with-image-and-text-example/
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    String[] items = {
            "Scan",
            "Register",
            "Visitors",
            "Vehicle",
            "Courier",
            "Faculty Booking",
            "Monitor",
            "Residents"

    };

    int [] icons = {
            R.drawable.ic_scan,
            R.drawable.ic_register,
            R.drawable.ic_visitor,
            R.drawable.ic_vehicle,
            R.drawable.ic_courier_packet,
            R.drawable.ic_facility_booking,
            R.drawable.ic_monitor,
            R.drawable.ic_resident
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View carItemView = layoutInflater.inflate(R.layout.dashboard_item, viewGroup, false);
        ViewHolder ret = new ViewHolder(carItemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Get car item dto in list.
        String carItem = items[position];
        int drawableId = icons[position];
        if(carItem != null) {
            // Set car item title.
            viewHolder.getTvTitle().setText(carItem);
            // Set car image resource id.
            viewHolder.getIvImage().setImageResource(drawableId);

            viewHolder.getIvImage().setOnClickListener(v -> {
                if(position == 0){
                    Intent i = new Intent(v.getContext(), VisitorActivity.class);
                    v.getContext().startActivity(i);
                } else if(position == 1 ) {
                    Intent i = new Intent(v.getContext(), StaffActivity.class);
                    v.getContext().startActivity(i);
                } else if(position == 5) {
                    Intent i = new Intent(v.getContext(), CourierActivity.class);
                    v.getContext().startActivity(i);
                }
            });
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
