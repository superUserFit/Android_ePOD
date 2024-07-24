package com.example.epod.utils;

//      http://192.168.1.101/1ofis/application/backend;
//      https://1ofis.infollective.com/application/backend;

import android.content.Context;
import com.example.epod.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class Request {
    public static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.1.101/1ofis/application/backend/";
//    private static final String BASE_URL = "https://1ofis.infollective.com/application/backend/";

    public static Retrofit getRetrofitInstance(Context context) {
        if(retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(getSSLSocketFactory(context), getTrustManager(context))
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static SSLSocketFactory getSSLSocketFactory(Context context) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream inputStream = context.getResources().openRawResource(R.raw.cert);
            Certificate certificate = certificateFactory.generateCertificate(inputStream);
            inputStream.close();

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", certificate);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            javax.net.ssl.SSLContext sslContext = javax.net.ssl.SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new java.security.SecureRandom());

            return sslContext.getSocketFactory();

        } catch (CertificateException certError) {
            throw new RuntimeException(certError);
        } catch (IOException inputOutputError) {
            throw new RuntimeException(inputOutputError);
        } catch (KeyStoreException keyStoreError) {
            throw new RuntimeException(keyStoreError);
        } catch (NoSuchAlgorithmException algorithmError) {
            throw new RuntimeException(algorithmError);
        } catch (KeyManagementException keyManagementError) {
            throw new RuntimeException(keyManagementError);
        }
    }

    private static X509TrustManager getTrustManager(Context context) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream inputStream = context.getResources().openRawResource(R.raw.cert);
            Certificate certificate = certificateFactory.generateCertificate(inputStream);
            inputStream.close();

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", certificate);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            return (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
