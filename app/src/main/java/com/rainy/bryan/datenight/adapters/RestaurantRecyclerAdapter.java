package com.rainy.bryan.datenight.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rainy.bryan.datenight.R;
import com.rainy.bryan.datenight.models.RestaurantModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<RestaurantModel> restaurantModelArrayList;

    public RestaurantRecyclerAdapter(ArrayList<RestaurantModel> restaurantModelArrayList) {
        super();
        this.restaurantModelArrayList = restaurantModelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final RestaurantModel restaurantModel = restaurantModelArrayList.get(position);

        TextView nameDisplay = holder.nameDisplay;
        nameDisplay.setText(restaurantModel.getName());

        //Set the image using Picasso
        if (restaurantModel.getPicture() != null || restaurantModel.getPicture() != "") {

            Picasso.with(context)
                    .load(restaurantModel.getPicture())
                    .error(R.drawable.ic_launcher_background)       //Handles 404, but not null
                    .into(holder.pictureDisplay);
        }
        else {
            Picasso.with(context).load(R.drawable.ic_launcher_background).into(holder.pictureDisplay);
        }

//        //Setting listener for when user taps on a card
//        ConstraintLayout constraintLayout = holder.constraintLayout;
//        constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, GameDetailPage_Activity.class);
//
//                intent.putExtra("name", cabinGamesModel.getName());
//                intent.putExtra("imageURL", cabinGamesModel.getImageURL());
//                intent.putExtra("description", cabinGamesModel.getDescription());
//                intent.putExtra("tutorial", cabinGamesModel.getTutorial());
//                intent.putExtra("manual", cabinGamesModel.getManual());
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return restaurantModelArrayList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameDisplay;
        public TextView addressDisplay;
        public TextView lastVisitedDisplay;
        public TextView typeDisplay;
        public TextView moneyDisplay;
        public ConstraintLayout constraintLayout;
        public ImageView pictureDisplay;

        public ViewHolder(View itemView) {

            super(itemView);

            nameDisplay = itemView.findViewById(R.id.restaurantName);
            addressDisplay = itemView.findViewById(R.id.restaurantAddress);
            lastVisitedDisplay = itemView.findViewById(R.id.restaurantLastVisited);
            typeDisplay = itemView.findViewById(R.id.restaurantType);
            moneyDisplay = itemView.findViewById(R.id.restaurantMoney);
            constraintLayout = itemView.findViewById(R.id.restaurantConstraintLayout);
            pictureDisplay = itemView.findViewById(R.id.restaurantImage);

            context = itemView.getContext();
        }
    }
}
