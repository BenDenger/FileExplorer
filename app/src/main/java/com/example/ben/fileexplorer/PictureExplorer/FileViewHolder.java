package com.example.ben.fileexplorer.PictureExplorer;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ben.fileexplorer.R;

/**
 * Created by benjamindenger on 19.02.17.
 */

public class FileViewHolder {
    private TextView filename;

    public FileViewHolder(View v)
    {
        filename = (TextView)v.findViewById(R.id.filename);
    }

    public TextView getFilename() {
        return filename;
    }

    public void setFilename(TextView filename) {
        this.filename = filename;
    }
}
