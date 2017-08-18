package com.example.ben.fileexplorer.FileExplorer.FileGridView;

/**
 * Created by Ben on 02.03.2017.
 */

public class SelectedFile {
    private String path;
    private boolean selected;
    
    public SelectedFile() {
        selected = false;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object obj) {
        return path.equals(((SelectedFile)obj).getPath());
    }
}
