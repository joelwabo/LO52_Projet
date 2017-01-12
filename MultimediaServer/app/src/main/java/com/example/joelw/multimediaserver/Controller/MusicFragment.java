package com.example.joelw.multimediaserver.Controller;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joelw.multimediaserver.Model.Media;
import com.example.joelw.multimediaserver.R;
import com.example.joelw.multimediaserver.View.ViewListAdapter;
import com.example.joelw.multimediaserver.View.MusicActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {
    List<Media> musicData = new ArrayList<Media>();
    ListView mListView;

    public MusicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);
        mListView = (ListView) rootView.findViewById(R.id.music_list);
        mListView.setOnItemClickListener(new ListClickHandler());
        populateMusic(getActivity());
        if(musicData!=null) {
            ViewListAdapter adapter = new ViewListAdapter(getActivity(), musicData, R.layout.list_view);
            mListView.setAdapter(adapter);
        }
        return rootView;
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            TextView listText = (TextView) view.findViewById(R.id.description);
            String text = listText.getText().toString();
            Media m = getMediaFromTitle(text);
            if(m!=null)
            {
                Intent intent = new Intent(getActivity(), MusicActivity.class);
                intent.putExtra("media", m);
                startActivity(intent);
            }
        }
    }

    private void populateMusic(Context context) {
        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get musique information
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int path= musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            //add songs to list
            do {
                int thisId = musicCursor.getInt(idColumn);
                String sTitle = musicCursor.getString(titleColumn);
                String sArtist = musicCursor.getString(artistColumn);
                String sPath= musicCursor.getString(path);
                musicData.add(new Media(thisId,sArtist,sTitle,sPath));
            }
            while (musicCursor.moveToNext());
        }
    }

    private Media getMediaFromTitle(String des){
        Media media = new Media();
        for(Media m : musicData)
            if(m.description.equals(des))
                media = m;
        return media;
    }

}
