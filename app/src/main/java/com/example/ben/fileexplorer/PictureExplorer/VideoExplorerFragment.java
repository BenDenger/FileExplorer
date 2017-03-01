package com.example.ben.fileexplorer.PictureExplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ben.fileexplorer.R;

public class VideoExplorerFragment extends Fragment {
    private View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_video_explorer, container, false);
        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FileExplorerFrame fragment = new FileExplorerFrame();
        Bundle b = new Bundle();
        b.putString("filetype", getArguments().getString("filetype"));
        fragment.setArguments(b);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_video, fragment).commit();
    }
}
