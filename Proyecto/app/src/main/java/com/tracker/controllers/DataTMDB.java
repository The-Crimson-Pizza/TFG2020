package com.tracker.controllers;

import com.tracker.models.BasicResponse;
import com.tracker.models.VideosResponse;
import com.tracker.models.people.Person;
import com.tracker.models.seasons.Season;
import com.tracker.models.series.Serie;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataTMDB {
    @Headers("Accept: application/json")
    @GET("trending/tv/day")
    Call<BasicResponse> getTrendingSeries();

    @Headers("Accept: application/json")
    @GET("discover/tv")
    Call<BasicResponse> getNewSeries(@Query("first_air_date_year") int year,
                                     @Query("language") String language,
                                     @Query("sort_by") String sort);

    @Headers("Accept: application/json")
    @GET("tv/{id_serie}")
    Call<Serie> getSerie(@Path("id_serie") int id,
                         @Query("language") String language,
                         @Query("append_to_response") String append);


    @Headers("Accept: application/json")
    @GET("tv/{id_serie}/season/{season_number}")
    Call<Season> getSeason(@Path("id_serie") int id,
                           @Path("season_number") int season,
                           @Query("language") String language);
//63247
//    38940

    @Headers("Accept: application/json")
    @GET("person/{person_id}")
    Call<Person> getPerson(@Path("person_id") int idPersona,
                           @Query("language") String language,
                           @Query("append_to_response") String append);

    @Headers("Accept: application/json")
    @GET("tv/{tv_id}/videos")
    Call<VideosResponse> getVideo(@Path("tv_id") int idSerie);



    @Headers("Accept: application/json")
    @GET("tv/{tv_id}/videos")
    Observable<VideosResponse> getTrailer(@Path("tv_id") int idSerie);

    @Headers("Accept: application/json")
    @GET("tv/{id_serie}")
    Observable<Serie> getTv(@Path("id_serie") int id,
                         @Query("language") String language,
                         @Query("append_to_response") String append);


}
