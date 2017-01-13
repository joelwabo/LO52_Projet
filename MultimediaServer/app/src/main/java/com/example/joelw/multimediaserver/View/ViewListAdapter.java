package com.example.joelw.multimediaserver.View;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joelw.multimediaserver.Model.Media;
import com.example.joelw.multimediaserver.R;

import java.util.List;

import static android.graphics.drawable.Drawable.createFromPath;

/**
 * Created by joelw on 29/12/2016.
 */

public class ViewListAdapter extends ArrayAdapter<Media> {

    int ressource;
    public ViewListAdapter(Context context, List<Media> medias, int res) {
        super(context, android.R.layout.simple_list_item_1, medias);
        ressource = res;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(getContext()).inflate(ressource, parent, false);
        //Filling list view with data
        MovieViewHolder viewHolder = (MovieViewHolder) view.getTag();
        if(viewHolder == null){
            viewHolder = new MovieViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.description = (TextView) view.findViewById(R.id.description);
            viewHolder.cover = (ImageView) view.findViewById(R.id.cover);
            view.setTag(viewHolder);
        }
        Media media = getItem(position);

        viewHolder.name.setText(media.name);
        viewHolder.description.setText(media.description);
        if(media.type.equals("music"))
            viewHolder.cover.setImageResource(media.id);
        if(media.type.equals("movie"))
            if(media.id != -1)
                viewHolder.cover.setImageDrawable(new ColorDrawable(media.id));
            else
                viewHolder.cover.setImageDrawable(createFromPath (media.cover));
        if(media.type.equals("image"))
            if(media.id != -1)
                viewHolder.cover.setImageResource(media.id);
            else
                viewHolder.cover.setImageDrawable(createFromPath (media.path));
        return view;
    }

    private class MovieViewHolder{
        public TextView name;
        public TextView description;
        public ImageView cover;
    }
}
