package com.github.a5809909.hwork05_versioncontrol.http;

import android.support.annotation.VisibleForTesting;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    private HttpURLConnection mConnection;

    @Override
    public void request(final String url, final ResponseListener listener) {
        try {
            final InputStream inputStream = openStream(url);
            listener.onResponse(inputStream);
            mConnection.disconnect();
        } catch (final Throwable t) {
            listener.onError(t);
        } finally {
            if (mConnection != null) {
                mConnection.disconnect();
            }
        }
    }

    @VisibleForTesting
    InputStream openStream(final String pURL) throws IOException {
        mConnection = (HttpURLConnection) (new URL(pURL)).openConnection();
        return mConnection.getInputStream();
    }

    public interface ResponseListener {

        void onResponse(InputStream pInputStream) throws Exception;

        void onError(Throwable pThrowable);
    }
}
