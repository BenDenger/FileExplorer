package com.example.ben.fileexplorer.FileExplorer.PathList;

import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ben.fileexplorer.R;

public class FileExplorerFragment extends Fragment {
    private View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments().getString("filetype").equals("Pictures"))
            myView = inflater.inflate(R.layout.fragment_picture_explorer, container, false);
        else
            myView = inflater.inflate(R.layout.fragment_video_explorer, container, false);
        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String filetype = getArguments().getString("filetype");
        FileExplorerFrame fragment = new FileExplorerFrame();
        Bundle b = new Bundle();
        b.putString("filetype",filetype);
        fragment.setArguments(b);
        if(filetype.equals("Pictures"))
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_pictures, fragment).commit();
        else
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_video, fragment).commit();
    }

}
