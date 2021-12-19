package com.example.dailybuzz;

import android.content.AsyncTaskLoader;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoaderClass extends AsyncTaskLoader {
    URL url;
    List<NewsDataClass> newsList=new ArrayList<>();

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public LoaderClass(@NonNull @NotNull Context context, URL url) {
        super(context);
        this.url=url;
    }

    @Nullable

    @Override
    public List<NewsDataClass> loadInBackground() {
            String jsonResponse=null;

        if (url==null){
            return null;
        }

        jsonResponse=QueryUtils.makeHTTPRequest(url);
        newsList=QueryUtils.parseJsonResponse(jsonResponse);

        if (newsList==null){
            return null;
        }

        return newsList;
    }
}
