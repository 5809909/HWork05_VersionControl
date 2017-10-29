/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.a5809909.hwork05_versioncontrol.vcbackend;

import com.example.Config;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        InputStream inputStream = getServletContext().getResourceAsStream("/WEB-INF/config.json");
        Reader reader = new InputStreamReader(inputStream);
        resp.setContentType("application/json");
        Config config = new Gson().fromJson(reader, Config.class);
        new Gson().toJson(config, resp.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/plain");
        if (name == null) {
            resp.getWriter().println("Please enter a name");
        }
        resp.getWriter().println("Hello " + name);
    }
}
