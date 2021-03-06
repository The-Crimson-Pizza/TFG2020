package com.thecrimsonpizza.tvtracker.util;

public class Constants {

    //    API
    public static final String API_KEY_STRING = "api_key";
    public static final String API_KEY = "18f61adb80d286bb036df43e60d7aae6";
    //    URLS
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_URL_WEB_MOVIE = "https://www.themoviedb.org/movie/";
    public static final String BASE_URL_WEB_PERSON = "https://www.themoviedb.org/person/";
    public static final String BASE_URL_WEB_TV = "https://www.themoviedb.org/tv/";
    public static final String BASE_URL_IMAGES_POSTER = "https://image.tmdb.org/t/p/w300/";
    public static final String BASE_URL_IMAGES_PORTRAIT = "https://image.tmdb.org/t/p/w185/";
    public static final String BASE_URL_IMAGES_BACK = "https://image.tmdb.org/t/p/w780/";
    public static final String BASE_URL_IMAGES_NETWORK = "https://image.tmdb.org/t/p/w92/";
    public static final String BASE_URL_INSTAGRAM = "https://www.instagram.com/";
    public static final String BASE_URL_INSTAGRAM_U = "https://www.instagram.com/_u/";
    //    ID'S
    public static final String ID_SERIE = "id_serie";
    public static final String ID_ACTOR = "id_actor";
    public static final String ID_SEASON = "id_season";
    public static final String ID_GENRE = "id_genre";
    public static final String ID_NETWORK = "id_network";
    //    API CALLS
    public static final String GET_SERIE_API_EXTRAS = "credits,similar,external_ids";
    public static final String GET_PEOPLE_API_EXTRAS = "tv_credits,movie_credits,external_ids";
    public static final String POP_DESC = "popularity.desc";
    public static final String TRAILER = "Trailer";
    public static final String MADRID = "Madrid";
    //    DATE FORMAT
    public static final String FORMAT_DEFAULT = "yyyy-MM-dd";
    public static final String FORMAT_LONG = "EEE dd, MMMM yyyy";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_HOURS = "HH:mm";
    public static final String FORMAT_MINUTES = "%d:%02d";
    //    SHARED PREFERENCES
    public static final String FAV_TEMP_DATA = "TEMP_DATA";
    public static final String MY_PREFS = "myPrefs";
    public static final String FIRST_OPENED = "first_opened";
    //    INTENT KEYS
    public static final String URL_WEBVIEW = "URL";
    public static final String ID = "id";
    //    EXTRAS
    public static final String SEASON_ID_EXTRA = "SEASON_ID";
    public static final String SERIE_NOMBRE_EXTRA = "SERIE_NOMBRE";
    public static final String SEASON_NUMBER_EXTRA = "SEASON_NUMBER";
    //    NOTIFICACIONES
    public static final String NEW_SEASON_NOTIFICATION_BUNDLE_CHANNEL_ID = "new_season_notification_bundle_channel";
    public static final String NEW_SEASON_NOTIFICATION_BUNDLE_CHANNEL_NAME = "New Season Notification Bundle Channel";
    public static final String NEW_SEASON_NOTIFICATION_CHANNEL_ID = "new_season_notification_channel";
    public static final String NEW_SEASON_NOTIFICATION_CHANNEL_NAME = "New Season Notification Channel";
    public static final String GROUP_KEY_SEASON_NEW = "group_key_season_new";
    //    OTROS
    public static final String GENRE = "genre";
    public static final String NETWORKS = "network";
    public static final String COM_INSTAGRAM_ANDROID = "com.instagram.android";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String SEASON_EPISODE_FORMAT = "%02dx%02d - %s";
    public static final String YOUTUBE = "YouTube";

    private Constants() {
//        Empty constructor
    }
}
