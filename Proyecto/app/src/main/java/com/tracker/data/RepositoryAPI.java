package com.tracker.data;

import com.tracker.models.BasicResponse;
import com.tracker.models.serie.VideosResponse;
import com.tracker.models.actor.PersonResponse;
import com.tracker.models.seasons.Season;
import com.tracker.models.serie.SerieResponse;
import com.tracker.util.Constants;
import com.tracker.util.Util;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tracker.util.Constants.API_KEY;
import static com.tracker.util.Constants.API_KEY_STRING;
import static com.tracker.util.Constants.BASE_URL;
import static com.tracker.util.Constants.GET_PEOPLE_API_EXTRAS;
import static com.tracker.util.Constants.GET_SERIE_API_EXTRAS;
import static com.tracker.util.Constants.MADRID;
import static com.tracker.util.Constants.POP_DESC;
import static com.tracker.util.Constants.TRAILER;

/**
 * Class that manages the Retrofit API calls
 */
public class RepositoryAPI {

    private static RepositoryAPI repoTMDB;
    private String language;

    public static RepositoryAPI getInstance() {
        if (repoTMDB == null) {
            repoTMDB = new RepositoryAPI();
        }
        return repoTMDB;
    }

    private RepositoryAPI() {
        language = Util.getLanguage();
    }

    private DataTMDB getRetrofitService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder().addQueryParameter(API_KEY_STRING, API_KEY).build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                })
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                        .build();
        return retrofit.create(DataTMDB.class);
    }

    public Observable<BasicResponse> getTrendingSeries() {
        return getRetrofitService().getTrendingSeries();
    }

    public Observable<BasicResponse> getNewSeries() {
        return getRetrofitService().getNewSeries(2020, language, POP_DESC);
    }

    public Observable<SerieResponse.Serie> getSerie(int idSerie) {
        Observable<SerieResponse.Serie> obsSerie = getRetrofitService().getSerie(idSerie, language, GET_SERIE_API_EXTRAS);
        Observable<VideosResponse> obsVideo = getRetrofitService().getTrailer(idSerie);
        return Observable.zip(obsSerie, obsVideo, (serie, videosResponse) -> {
            List<VideosResponse.Video> trailers = videosResponse.results;
            for (VideosResponse.Video v : trailers) {
                if (v.type.equals(TRAILER)) {
                    serie.video = v;
                    break;
                }
            }
            return serie;
        });
    }

    public Observable<Season> getSeason(SerieResponse.Serie serie, int numSeason) {
        return getRetrofitService().getSeason(serie.id, numSeason, language);
    }

    public Single<List<Season>> getSeasons(int idSerie, int firstSeason, int numSeasons) {
        return Observable.range(firstSeason, numSeasons)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(i -> getRetrofitService().getSeason(idSerie, i, language))
                .toList();
    }

    public Observable<PersonResponse.Person> getPerson(int idPerson) {
        return getRetrofitService().getPerson(idPerson, language, GET_PEOPLE_API_EXTRAS);
    }

    public Observable<PersonResponse> searchPerson(String query) {
        return getRetrofitService().searchPerson(query, language);
    }

    public Observable<SerieResponse> searchSerie(String query) {
        return getRetrofitService().searchSerie(query, language);
    }

    public Observable<BasicResponse> getByGenre(int idGenre) {
        return getRetrofitService().getSeriesByGenre(idGenre, language, MADRID, POP_DESC);
    }

    public Observable<BasicResponse> getByNetwork(int idNetwork) {
        return getRetrofitService().getSeriesByNetwork(idNetwork, language, MADRID, POP_DESC);
    }

}
