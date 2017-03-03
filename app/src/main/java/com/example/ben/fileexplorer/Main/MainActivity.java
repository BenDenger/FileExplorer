package com.example.ben.fileexplorer.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ben.fileexplorer.R;
import com.example.ben.fileexplorer.Tablayout.TabLayoutActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btnSelectFile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, TabLayoutActivity.class), 55);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 55) {
                RecyclerView listView = (RecyclerView)findViewById(R.id.picture_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                listView.setLayoutManager(layoutManager);
                listView.setAdapter(new PictureListAdapter(this,data.getStringArrayExtra("files"),data.getStringExtra("filetype")));
            }
        }
    }
}
