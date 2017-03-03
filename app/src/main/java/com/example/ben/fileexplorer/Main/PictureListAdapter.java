package com.example.ben.fileexplorer.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ben.fileexplorer.R;

/**
 * Created by Ben on 03.03.2017.
 */

public class PictureListAdapter extends RecyclerView.Adapter<PictureViewHolder> {

    private Context context;
    private String[] files;
    private String filetype;

    public PictureListAdapter(Context c, String[] objects, String fileType) {
        files = objects;
        context = c;
        filetype = fileType;
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_list_row, parent, false);
        return new PictureViewHolder(inflatedView,filetype);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        holder.bindPhoto(files[position]);
    }

    @Override
    public int getItemCount() {
        return files.length;
    }
}

