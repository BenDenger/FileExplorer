package com.example.ben.fileexplorer.FileExplorer;

import com.example.ben.fileexplorer.FileExplorer.FileGridView.SelectedFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamindenger on 27.02.17.
 */

public class DirectoryIterator {
   private List<SelectedFile> values;

    public DirectoryIterator()
    {
       values = new ArrayList<>();
    }
    private interface PicOrVideo {
        boolean containsFile(File f);
    }

    public PicOrVideo video = new DirectoryIterator.PicOrVideo() {
        @Override
        public boolean containsFile(File f) {
            if (f.getName().endsWith(".mp4") ||
                    f.getName().endsWith(".mpeg4"))
                return true;
            return false;
        }
    };

   public PicOrVideo picture=  new DirectoryIterator.PicOrVideo() {
        @Override
        public boolean containsFile(File f) {
            if (f.getName().endsWith(".png") ||
                    f.getName().endsWith(".jpg") ||
                    f.getName().endsWith(".bmp") ||
                    f.getName().endsWith(".jpeg"))
                return true;
            return false;
        }
    };

    public List<SelectedFile> getPaths(File root, PicOrVideo picOrVideo) {
        File listFile[] = root.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (File f : listFile) {
                if (f.isDirectory() && f.canRead() && f.canWrite() && !f.isHidden()) {
                    getPaths(f, picOrVideo);
                } else if (picOrVideo.containsFile(f)) {
                    String tmp = f.getPath().substring(0, f.getPath().lastIndexOf('/'));
                    SelectedFile file = new SelectedFile();
                    file.setPath(tmp);
                    if (!values.contains(file)) {
                        values.add(file);
                    }
                }
            }
        }
        return values;
    }
    public List<SelectedFile> getFiles(String directory,PicOrVideo picOrVideo) {
        File file = new File(directory);
        File listFile[] = file.listFiles();
        for (File f : listFile) {
            if (picOrVideo.containsFile(f)) {
                SelectedFile selectedFile = new SelectedFile();
                selectedFile .setPath(f.getPath());
                if (!values.contains(selectedFile)) {
                    values.add(selectedFile);
                }
            }
        }
        return values;
    }
}
