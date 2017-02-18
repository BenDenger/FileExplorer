package com.example.ben.fileexplorer;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileExplorerFragment extends Fragment {

    private String directory;


    public FileExplorerFragment()
    {
        directory = "/";
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String[] directories = dir.list();
        final List values = new ArrayList();
        if(directories != null){
            for(String file: directories)
            {
                values.add(file);
            }
        }
        ListView lv = (ListView)getActivity().findViewById(R.id.lvFiles);
        lv.setAdapter(new ArrayAdapter(getContext(),R.layout.file_list_row,R.id.textView,values));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemClick: ", position+"");
                FileExplorerFragment fragment = new FileExplorerFragment();
                fragment.setDirectory(values.get(position).toString());
                getFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_file_explorer, container, false);
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
