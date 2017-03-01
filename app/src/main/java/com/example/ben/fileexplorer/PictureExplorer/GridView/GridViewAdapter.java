package com.example.ben.fileexplorer.PictureExplorer.GridView;

import android.content.Context;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by benjamindenger on 19.02.17.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> files;
private String filetype;
    public GridViewAdapter(Context c, List<String> objects, String fileType) {
        files = objects;
        context = c;
        filetype = fileType;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public String getItem(int position) {
        return files.get(position);
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(480, 480));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        String path = getItem(position);
        Log.e( "getView: ",path );
        if(filetype.equals("Pictures")) {
            Picasso.with(context)
                    .load(new File(path))
                    .fit().into(imageView);
        }else{
           imageView.setImageBitmap(ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND));
        }
        return imageView;
    }
}
