package com.example.joelw.multimediaserver.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joelw.multimediaserver.Model.Media;
import com.example.joelw.multimediaserver.Model.GetXMLTask;
import com.example.joelw.multimediaserver.R;
import com.example.joelw.multimediaserver.View.MovieActivity;
import com.example.joelw.multimediaserver.View.ViewListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    List<Media> movieData = new ArrayList<Media>();
    ListView mListView;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        if (MainActivity.sUrl != "") {
            GetXMLTask task = new GetXMLTask();
            task.setMediaType("movie");
            task.execute(new String[]{MainActivity.sUrl});
            //We're waiting for data from server
            try {
                task.get(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            movieData = task.getMediaData();

            mListView = (ListView) rootView.findViewById(R.id.movie_list);
            mListView.setOnItemClickListener(new MovieFragment.ListClickHandler());
            movieData.add(new Media("movie","Film 1", "Video prise en ligne !","http://ia802302.us.archive.org/27/items/Pbtestfilemp4videotestmp4/video_test.mp4",Color.BLUE));

            if(movieData!=null) {
                ViewListAdapter adapter = new ViewListAdapter(getActivity(), movieData, R.layout.list_view);
                mListView.setAdapter(adapter);
            }
        }
        return rootView;
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            TextView listText = (TextView) view.findViewById(R.id.name);
            String text = listText.getText().toString();
            Media m = getMediaFromTitle(text);
            if(m!=null)
            {
                Intent intent = new Intent(getActivity(), MovieActivity.class);
                intent.putExtra("media", m);
                startActivity(intent);
            }
        }
    }

    private Media getMediaFromTitle(String name){
        Media media = new Media();
        for(Media m : movieData)
            if(m.name.equals(name))
                media = m;
        return media;
    }

 }
