package id.ac.umn.trashare.network;

import java.util.List;
import java.util.Map;

import id.ac.umn.trashare.models.BankSampah;
import id.ac.umn.trashare.models.Hadiah;
import id.ac.umn.trashare.models.Kegiatan;
import id.ac.umn.trashare.models.Member;
import id.ac.umn.trashare.models.Sampah;
import id.ac.umn.trashare.models.Yayasan;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TrashareService {

    // Service Yayasan
    @GET("yayasan")
    Call<List<Yayasan>> getAllYayasan();

    @GET("yayasan/{id}")
    Call<Yayasan> getOneYayasan(@Path("id") String idYayasan);

    @POST("yayasan")
    Call<Yayasan> createYayasan(@Body Yayasan yayasan);

    @POST("yayasan/login")
    Call<Yayasan> loginYayasan(@Body Map<String, String> body);

    @PUT("yayasan/{id}")
    Call<Yayasan> updateYayasan(@Path("id") String idYayasan, @Body Yayasan yayasan);

    @DELETE("yayasan/{id}")
    Call<Boolean> deleteYayasan(@Path("id") String idYayasan);

    // Service Bank Sampah
    @GET("bank-sampah")
    Call<List<BankSampah>> getAllBankSampah();

    @GET("bank-sampah/{id}")
    Call<BankSampah> getOneBankSampah(@Path("id") String idBankSampah);

    @POST("bank-sampah")
    Call<BankSampah> createBankSampah(@Body BankSampah bankSampah);

    @POST("bank-sampah/login")
    Call<BankSampah> loginBankSampah(@Body Map<String, String> body);

    @PUT("bank-sampah/{id}")
    Call<BankSampah> updateBankSampah(@Path("id") String idBankSampah, @Body BankSampah bankSampah);

    @DELETE("bank-sampah/{id}")
    Call<Boolean> deleteBankSampah(@Path("id") String idBankSampah);

    // Service Member
    @GET("member")
    Call<List<Member>> getAllMember();

    @GET("member/{id}")
    Call<Member> getOneMember(@Path("id") String idMember);

    @POST("member")
    Call<Member> createMember(@Body Member member);

    @POST("member/login")
    Call<Member> loginMember(@Body Map<String, String> body);

    @PUT("member/{id}")
    Call<Member> updateMember(@Path("id") String idMember, @Body Member member);

    @DELETE("member/{id}")
    Call<Boolean> deleteMember(@Path("id") String idYayasan);

    // Service Kegiatan
    @GET("kegiatan")
    Call<List<Kegiatan>> getAllKegiatan();

    @GET("kegiatan/{id}")
    Call<Kegiatan> getOneKegiatan(@Path("id") String idKegiatan);

    @POST("kegiatan")
    Call<Kegiatan> createKegiatan(@Body Kegiatan kegiatan);

    @PUT("kegiatan/{id}")
    Call<Kegiatan> updateKegiatan(@Path("id") String idKegiatan, @Body Kegiatan kegiatan);

    @DELETE("kegiatan/{id}")
    Call<Boolean> deleteKegiatan(@Path("id") String idKegiatan);

    // Service Hadiah
    @GET("hadiah")
    Call<List<Hadiah>> getAllHadiah();

    @GET("hadiah/{id}")
    Call<Hadiah> getOneHadiah(@Path("id") String idHadiah);

    @POST("hadiah")
    Call<Hadiah> createHadiah(@Body Hadiah hadiah);

    @PUT("hadiah/{id}")
    Call<Hadiah> updateHadiah(@Path("id") String idHadiah, @Body Hadiah hadiah);

    @DELETE("hadiah/{id}")
    Call<Boolean> deleteHadiah(@Path("id") String idHadiah);

    // Service Sampah
    @GET("sampah")
    Call<List<Sampah>> getAllSampah();

    @GET("sampah/{id}")
    Call<Sampah> getOneSampah(@Path("id") String idSampah);

    @POST("sampah")
    Call<Sampah> createSampah(@Body Sampah sampah);

    @PUT("sampah/{id}")
    Call<Sampah> updateSampah(@Path("id") String idSampah, @Body Sampah sampah);

    @DELETE("sampah/{id}")
    Call<Boolean> deleteSampah(@Path("id") String idSampah);
}
