package com.example.ben.fileexplorer.FileExplorer.FileGridView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ben.fileexplorer.FileExplorer.DirectoryIterator;
import com.example.ben.fileexplorer.R;

import java.util.ArrayList;
import java.util.List;


public class FileGridViewFragment extends Fragment {
    List<SelectedFile> values = new ArrayList();
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
            gridview.setAdapter(new FileGridViewAdapter(getActivity(), values, fileType));

            gridview.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
            gridview.setMultiChoiceModeListener(new GridView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                    values.get(position).setSelected(checked);
                    ((FileGridViewAdapter) gridview.getAdapter()).notifyDataSetChanged();
                    Log.e("onItemChecked: ", ""+ checked);
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.setTitle("Dateien auswählen");
                    menu.add("Hinzufügen");
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return true;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId())
                    {
                        case 0: {
                            List<String> files = new ArrayList<>();
                            for (SelectedFile file :
                                    values) {
                                if (file.isSelected()){
                                    files.add(file.getPath());
                                }
                            }
                            Intent intent = new Intent();
                            intent.putExtra("filetype",fileType);
                            intent.putExtra("files",files.toArray(new String[files.size()]));
                            getActivity().setResult(Activity.RESULT_OK,intent);
                            getActivity().finish();
                            break;
                        }
                    }
                    return true;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Toast.makeText(getActivity(), values.get(position).getPath(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("files",new String[]{values.get(position).getPath()});
                    intent.putExtra("filetype",fileType);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                }
            });
            /*gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    values.get(position).setSelected(true);
                    ((GridViewAdapter) gridview.getAdapter()).notifyDataSetChanged();
                    return true;
                }
            });*/
        }
    }
}
