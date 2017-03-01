package com.example.ben.fileexplorer.PictureExplorer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ben.fileexplorer.PictureExplorer.GridView.GridViewFragment;
import com.example.ben.fileexplorer.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileExplorerFrame extends Fragment {
    private List<String> values = new ArrayList();
    private ListView lv;
    private int contentId;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView) view.findViewById(R.id.lvFiles);
        DirectoryIterator iterator =new DirectoryIterator();

        switch (getArguments().getString("filetype")) {
            case "Pictures": {
                contentId = R.id.content_pictures;
                values = iterator.getPaths(new File("/"), iterator.picture);
                break;
            }
            case "Videos": {
                contentId = R.id.content_video;
                values = iterator.getPaths(new File("/"), iterator.video);
                break;
            }
        }
        initListView();
    }

    private void initListView() {
        //lv = (ListView) getActivity().findViewById(R.id.lvFiles);
        lv.setAdapter(new ListViewAdapter(getActivity(), R.layout.file_list_row, values));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!values.get(position).contains(".")) {
                    GridViewFragment fragment = new GridViewFragment();
                    Bundle b = new Bundle();
                    b.putString("dir", values.get(position));
                    b.putString("filetype",getArguments().getString("filetype"));
                    fragment.setArguments(b);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(contentId, fragment)
                            .addToBackStack(getArguments().getString("filetype")).commit();
                } else {
                    Toast.makeText(getActivity(), values.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_picture_explorer, container, false);
    }
}
