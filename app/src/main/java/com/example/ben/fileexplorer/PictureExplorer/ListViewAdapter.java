package com.example.ben.fileexplorer.PictureExplorer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.ben.fileexplorer.R;

import java.util.List;

/**
 * Created by benjamindenger on 19.02.17.
 */

public class ListViewAdapter extends ArrayAdapter {

    private Context context;
    private List<String> files;

    public ListViewAdapter(Context c, int resource, List<String> objects) {
        super(c, resource, objects);
        files = objects;
        context = c;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FileViewHolder viewholder = null;
        if (row == null && context != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.file_list_row, parent, false);
            viewholder = new FileViewHolder(row);
            row.setTag(viewholder);
        } else {
            viewholder = (FileViewHolder) row.getTag();
        }
        viewholder.getFilename().setText(files.get(position));
        return row;
    }
}
