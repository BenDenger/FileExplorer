package com.example.ben.fileexplorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FileExplorerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);
        getFragmentManager().beginTransaction().replace(R.id.content_main,new FileExplorerFragment()).commit();
    }
}
