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
    private ImageView imageView;
    private String filetype;
    public PictureViewHolder(View v,String Filetype) {
        super(v);
        imageView = (ImageView) v.findViewById(R.id.image_preview);
        filetype = Filetype;
    }
    public void bindPhoto(String Path) {
        if (filetype.equals("Pictures")) {
            Picasso.with(imageView.getContext())
                    .load(new File(Path))
                    .fit().into(imageView);
        } else {
            imageView.setImageBitmap(ThumbnailUtils.createVideoThumbnail(Path, MediaStore.Video.Thumbnails.MINI_KIND));
        }
    }
}
