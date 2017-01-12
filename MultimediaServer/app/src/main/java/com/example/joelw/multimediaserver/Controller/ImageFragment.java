package com.example.joelw.multimediaserver.Controller;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joelw.multimediaserver.Model.GetXMLTask;
import com.example.joelw.multimediaserver.Model.Media;
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
public class ImageFragment extends Fragment {
    List<Media> imageData = new ArrayList<Media>();
    ListView mListView;


    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        if (MainActivity.sUrl != "") {
            GetXMLTask task = new GetXMLTask();
            task.setMediaType("image");
            task.execute(new String[]{MainActivity.sUrl});
            //We're waiting for data from server
            try {
                task.get(3000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            imageData = task.getMediaData();
            mListView = (ListView) rootView.findViewById(R.id.image_list);
            if(imageData!=null) {

                ViewListAdapter adapter = new ViewListAdapter(getActivity(), imageData, R.layout.image_view);
                mListView.setAdapter(adapter);
            }
        }
        return rootView;
    }

}
