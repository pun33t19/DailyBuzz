package com.example.dailybuzz;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dailybuzz.Adapter.NewsItems;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.net.URL;
import java.util.List;


public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsDataClass>> {

    private static final String NEWS_URL = "https://inshortsapi.vercel.app/news?category=all";
    NewsItems adapter;
    RecyclerView rv;
    CircularProgressIndicator circularProgressIndicator;
    View divider;
    TextView textView;

    public HomeFragment() {
       super(R.layout.fragment_home);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        rv=view.findViewById(R.id.news_recycler_view);
        circularProgressIndicator=view.findViewById(R.id.progress);
        textView=view.findViewById(R.id.title_text_view);
        divider=view.findViewById(R.id.divider);



       
        textView.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);

        ConnectivityManager cm= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        rv.setVisibility(View.GONE);
        circularProgressIndicator.setVisibility(View.VISIBLE);

        if (info!=null && info.isConnectedOrConnecting()){
            getActivity().getLoaderManager().initLoader(0,null,this);
            Log.v("TAG","Loader initialised");
        }

        return view;
    }

    public void updateUI(List<NewsDataClass> newsList){
        if (newsList==null){
            Log.v("TAG","Empty response");
            return;
        }
        adapter=new NewsItems(getContext(),newsList);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
    }

    @Override
    public Loader<List<NewsDataClass>> onCreateLoader(int i, Bundle bundle) {
        URL url=QueryUtils.stringToUrl(NEWS_URL);
        return new LoaderClass(getContext(),url);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsDataClass>> loader, List<NewsDataClass> newsDataClasses) {
        updateUI(newsDataClasses);
        circularProgressIndicator.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
        rv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsDataClass>> loader) {

    }
}