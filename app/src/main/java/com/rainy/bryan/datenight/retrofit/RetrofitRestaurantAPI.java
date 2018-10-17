package com.rainy.bryan.datenight.retrofit;

import com.rainy.bryan.datenight.models.RestaurantModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitRestaurantAPI {

    @GET("echo?user_content_key=rzebLG2wcN93cxH_0vHYuHt4OgJuPxgA_cTR7KsajzEfC-U2bWvMCu6vqOCdGLq" +
            "jmOn1cQOPXSOQegwGDmYmCf3znpPo9-N7m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECj" +
            "ZEnMGcjhvouzk59k5SRp9Sc2jT0r8tOolGi5t2niP9dD5deV9g_ag-91xhuZeKw3JVKT9kyJWi4P0p&lib" +
            "=MRCO9HorCYzS-zkxSSw-v2keCV68nZa4q")
    Call<List<RestaurantModel>> getEvents();
}
