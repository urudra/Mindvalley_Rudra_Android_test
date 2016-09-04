package com.r.mindvalley.api;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Laukik on 04-Sep-16.
 */
public class ServiceGenerator {

    private static final String TAG = ServiceGenerator.class.getSimpleName();
        // Base api Urls
        private static final String BASE_URL = "http://pastebin.com/";
        // customized date and time format to use in {@link retrofit2.converter.gson.GsonConverterFactory} 2016-05-29 T 15:42:02-04:00
        // FIXME: 7/9/16 check for GMT and -04:00 and TimeZone
        private static final String DATE_FORMAT = "yyyy-mm-dd'T'HH:mm:ss";
        // Customized {@link Gson}
        private static final Gson GSON = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat(DATE_FORMAT)
                .create();
        // {@link Retrofit.Builder} to use in {@link #createService}
        private static final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GSON));

        /**
         * use it when don't have Authorization parameters
         *
         * @param serviceClass
         * @param <S>
         * @return
         */
        public static <S> S createService(Class<S> serviceClass) {
            return createService(serviceClass, null, null);
        }

        /**
         * To set Authorization header parameter in request meta data
         *
         * @param serviceClass
         * @param username
         * @param password
         * @param <S>
         * @return
         */
        private static <S> S createService(Class<S> serviceClass, String username, String password) {
            Log.d(TAG, "createService() called with: " + "serviceClass = [" + serviceClass + "], username = [" + username + "], password = [" + password + "]");
            AtomicReference<OkHttpClient> mOkHttpClient = new AtomicReference<OkHttpClient>();
            if (username != null && password != null) {
                final String credential = username + ":" + password;
                final String basic = "Basic " + Base64.encodeToString(credential.getBytes(), Base64.NO_WRAP);
                mOkHttpClient.set(new OkHttpClient.Builder()
                        .addInterceptor(new AuthorizationBasicInterceptor(basic)).build());

            } else {// if there is no credentials just make new instance of okHttpClient
                mOkHttpClient.set(new OkHttpClient());
            }
            final Retrofit retrofit = builder.client(mOkHttpClient.get()).build();
            return retrofit.create(serviceClass);
        }

/**
 * To make implementation {@link Interceptor} in {@link OkHttpClient} and set mBasicCredential
 * as Authorization parameter in header
 */
private static class AuthorizationBasicInterceptor implements Interceptor {
    private final String mBasicCredential;

    public AuthorizationBasicInterceptor(String basic) {
        this.mBasicCredential = basic;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request original = chain.request();

        // add own headers into {@link #original} and replace with original
        final Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", mBasicCredential)
                .method(original.method(), original.body());

        return chain.proceed(requestBuilder.build());
    }
}
}
