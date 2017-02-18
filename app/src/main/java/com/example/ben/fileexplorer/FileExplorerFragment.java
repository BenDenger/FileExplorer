package com.example.ben.fileexplorer;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileExplorerFragment extends Fragment {
    List<String> values = new ArrayList();


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("onViewCreated: ", "created");
        String dir = getArguments().getString("dir");
        if (dir.equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            TextView tv = (TextView) getActivity().findViewById(R.id.tvPath);
            tv.setText(dir);
            getPaths(new File(dir));
        } else {
            getFiles(dir);
            Log.e("onViewCreated: ", "dir");
        }

        ListView lv = (ListView) getActivity().findViewById(R.id.lvFiles);
        lv.setAdapter(new ArrayAdapter(getContext(), R.layout.file_list_row, R.id.textView, values));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemClick: ", position + "");
                FileExplorerFragment fragment = new FileExplorerFragment();
                Bundle b = new Bundle();
                b.putString("dir", values.get(0));
                fragment.setArguments(b);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
                transaction.replace(R.id.frame, fragment).commit();
            }
        });
    }

    private void getFiles(String directory) {
        File file = new File(directory);
        File listFile[] = file.listFiles();
        for (File f :
                listFile) {
            values.add(f.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_file_explorer, container, false);
    }

    public void getPaths(File root) {
        File listFile[] = root.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File f : listFile) {
                if (f.isDirectory()) {
                    getPaths(f);
                } else if (f.getName().endsWith(".png") ||
                        f.getName().endsWith(".jpg") ||
                        f.getName().endsWith(".bmp") ||
                        f.getName().endsWith(".jpeg")) {
                    String tmp = f.getPath().substring(0, f.getPath().lastIndexOf('/'));
                    if (!values.contains(tmp)) {
                        values.add(tmp);
                    }
                }
            }
        }
    }
    
}
