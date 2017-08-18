package com.example.ben.fileexplorer.Main;

import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.ben.fileexplorer.R;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Ben on 03.03.2017.
 */

public class PictureViewHolder extends RecyclerView.ViewHolder{
    private ImageView _imageView;
    private String _fileType;
    
    public PictureViewHolder(View v, String fileType) {
        super(v);
        imageView = (ImageView) v.findViewById(R.id.image_preview);
        _fileType = fileType;
    }
    
    public void bindPhoto(String path) {
        if (_fileType.equals("Pictures")) {
            Picasso.with(_imageView.getContext())
                    .load(new File(path))
                    .fit().into(_imageView);
        } else {
            _imageView.setImageBitmap(ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND));
        }
    }
}
