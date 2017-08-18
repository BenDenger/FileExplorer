package com.example.ben.fileexplorer.FileExplorer.PathList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.ben.fileexplorer.FileExplorer.FileGridView.SelectedFile;
import com.example.ben.fileexplorer.R;

import java.util.List;

/**
 * Created by benjamindenger on 19.02.17.
 */

public class PathListViewAdapter extends ArrayAdapter {
    private Context context;
    private List<SelectedFile> files;

    public PathListViewAdapter(Context c, int resource, List<SelectedFile> objects) {
        super(c, resource, objects);
        files = objects;
        context = c;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView; // y tho? You could aswell just work with "convertView"
        PathViewHolder viewholder = null;
        
        if (row == null && context != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.file_list_row, parent, false);
            viewholder = new PathViewHolder(row);
            row.setTag(viewholder);
        } else {
            viewholder = (PathViewHolder) row.getTag();
        }
        
        viewholder.getPathname().setText(files.get(position).getPath());
        return row;
    }
}
