package com.github.a5809909.hwork05_versioncontrol.http;

public interface IHttpClient {

    void request(String url, HttpClient.ResponseListener listener);
}
