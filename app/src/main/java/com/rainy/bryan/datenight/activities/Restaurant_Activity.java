package com.rainy.bryan.datenight.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rainy.bryan.datenight.R;
import com.rainy.bryan.datenight.adapters.RestaurantRecyclerAdapter;
import com.rainy.bryan.datenight.models.RestaurantModel;
import com.rainy.bryan.datenight.retrofit.RetrofitInstance;
import com.rainy.bryan.datenight.retrofit.RetrofitRestaurantAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Restaurant_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    RestaurantRecyclerAdapter adapter;

    ArrayList<RestaurantModel> restaurantModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_);

        //Setting up and enabling the back button in the support bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Restaurants");

        //Setting up the array
        restaurantModelArrayList = new ArrayList<>();

        //Setting recycler viewer to any size
        recyclerView = findViewById(R.id.restaurantRecyclerView);
        recyclerView.setHasFixedSize(false);

        adapter = new RestaurantRecyclerAdapter(restaurantModelArrayList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitRestaurantAPI retrofitRestaurantAPI = RetrofitInstance.getRetrofitInstance().create(RetrofitRestaurantAPI.class);

        Call<List<RestaurantModel>> call = retrofitRestaurantAPI.getEvents();

        call.enqueue(new Callback<List<RestaurantModel>>() {
            @Override
            public void onResponse(Call<List<RestaurantModel>> call, Response<List<RestaurantModel>> response) {

                List<RestaurantModel> eventsList = response.body();

                for (int i = 0; i < eventsList.size(); i++) {

                    RestaurantModel restaurantModel = new RestaurantModel();

                    restaurantModel.setName(eventsList.get(i).getName());
                    restaurantModel.setAddress((eventsList.get(i).getAddress()));
                    restaurantModel.setLastVisited(eventsList.get(i).getLastVisited());
                    restaurantModel.setType(eventsList.get(i).getType());
                    restaurantModel.setMoney(eventsList.get(i).getMoney());
                    restaurantModel.setGoogleMaps(eventsList.get(i).getGoogleMaps());

                    //Check to see if the URL is empty.  If it is, Picasso will cause the app to crash
                    //because it does not like a null/empty field.  For whatever reason, I can't just
                    //pass eventsList.get(i).getImageURL into the if statement, it must be stored in
                    //a different variable.  By passing "none" at least it's not empty and Picasso
                    //will use the default value in the RecyclerAdapter.
                    String storedImageURL;
                    storedImageURL = eventsList.get(i).getPicture();
                    if (storedImageURL == null || storedImageURL == "") {
                        restaurantModel.setPicture("none");
                    }
                    else {
                        restaurantModel.setPicture(eventsList.get(i).getPicture());
                    }

                    restaurantModelArrayList.add(restaurantModel);

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<RestaurantModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
