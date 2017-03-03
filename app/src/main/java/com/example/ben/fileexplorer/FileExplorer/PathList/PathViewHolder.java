package com.example.ben.fileexplorer.FileExplorer.PathList;

import android.view.View;
import android.widget.TextView;

import com.example.ben.fileexplorer.R;

/**
 * Created by benjamindenger on 19.02.17.
 */

public class PathViewHolder {
    private TextView pathname;

    public PathViewHolder(View v)
    {
        pathname = (TextView)v.findViewById(R.id.filename);
    }

    public TextView getPathname() {
        return pathname;
    }

    public void setPathname(TextView filename) {
        this.pathname = filename;
    }
}
