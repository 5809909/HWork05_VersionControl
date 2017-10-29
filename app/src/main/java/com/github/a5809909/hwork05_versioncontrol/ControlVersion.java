package com.github.a5809909.hwork05_versioncontrol;

import com.example.Config;
import com.github.a5809909.hwork05_versioncontrol.http.HttpClient;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;


class ControlVersion {

    private Config mConfiguration;

    ControlVersion() {
        final String url = Constants.CONFIG_URL;
        final ConfigurationResponseListener listener = new ConfigurationResponseListener();
        new HttpClient().request(url, listener);
        mConfiguration = listener.getConfig();
    }

    Boolean isCorrectVersion() {
        return mConfiguration.getVersion() <= BuildConfig.VERSION_CODE;
    }

    Boolean isForceUpdate() {
        return mConfiguration.getUpdate();
    }


    private static class ConfigurationResponseListener implements HttpClient.ResponseListener {

        private Config mConfiguration;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) throws Exception {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                mConfiguration = new GsonBuilder()
                        .setLenient()
                        .create().fromJson(inputStreamReader, Config.class);
            } finally {
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (final Exception ignored) {
                    }
                }
            }
        }

        public Throwable getThrowable() {
            return mThrowable;
        }

        @Override
        public void onError(final Throwable pThrowable) {
            mThrowable = pThrowable;
        }

        Config getConfig() {
            return mConfiguration;
        }
    }

}



