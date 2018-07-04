package id.ac.umn.trashare.network;

import java.util.List;

import id.ac.umn.trashare.models.Yayasan;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TrashareService {

    @GET("yayasan")
    Call<List<Yayasan>> getAllYayasan();

    @GET("yayasan/{id}")
    Call<Yayasan> getOneYayasan(@Path("id") String idYayasan);

    @POST("yayasan")
    Call<Yayasan> createYayasan(@Body Yayasan yayasan);

    @PUT("yayasan/{id}")
    Call<Yayasan> updateYayasan(@Path("id") String idYayasan, @Body Yayasan yayasan);

    @DELETE("yayasan/{id}")
    Call<Boolean> deleteYayasan(@Path("id") String idYayasan);
}
