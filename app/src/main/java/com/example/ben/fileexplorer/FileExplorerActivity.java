package com.example.ben.fileexplorer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FileExplorerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);
        FileExplorerFragment fragment = new FileExplorerFragment();
        Bundle b = new Bundle();
        b.putString("dir", Environment.getExternalStorageDirectory().getAbsolutePath());
        fragment.setArguments(b);
        getFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
    }
}
