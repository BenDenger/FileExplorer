package com.example.ben.fileexplorer.FileExplorer.FileGridView;

import android.content.Context;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by benjamindenger on 19.02.17.
 */

public class FileGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<SelectedFile> files;
private String filetype;
    public FileGridViewAdapter(Context c, List<SelectedFile> objects, String fileType) {
        files = objects;
        context = c;
        filetype = fileType;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public SelectedFile getItem(int position) {
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
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        if(files.get(position).isSelected()) {
            imageView.setBackgroundColor(Color.BLUE);
        }else{
            imageView.setBackgroundColor(Color.WHITE);
        }
        String path = getItem(position).getPath();
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
