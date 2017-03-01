package com.example.ben.fileexplorer.PictureExplorer.GridView;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ben.fileexplorer.PictureExplorer.DirectoryIterator;
import com.example.ben.fileexplorer.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GridViewFragment extends Fragment {
    List<String> values = new ArrayList();
    View myView;
    String fileType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFiles(getArguments().getString("dir"));
        setGridView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fileType = getArguments().getString("filetype");
        if (fileType.equals("Pictures")) {
            myView = inflater.inflate(R.layout.fragment_picture_grid_view, container, false);
        } else {
            myView = inflater.inflate(R.layout.fragment_video_grid_view, container, false);
        }
        return myView;
    }

    private void getFiles(String directory) {
        DirectoryIterator iterator = new DirectoryIterator();
        if (fileType.equals("Pictures")) {
            values = iterator.getFiles(directory, iterator.picture);
        } else {
            values = iterator.getFiles(directory, iterator.video);
        }
    }

    private void setGridView() {
        if (values.size() > 0) {
            final GridView gridview;
            if (fileType.equals("Pictures")) {
                gridview = (GridView) getActivity().findViewById(R.id.gridview_pictures);
            } else {
                gridview = (GridView) getActivity().findViewById(R.id.gridview_video);
            }
            gridview.setAdapter(new GridViewAdapter(getActivity(), values, fileType));
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Toast.makeText(getActivity(), values.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
