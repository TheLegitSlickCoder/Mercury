package sample;

import java.io.File;

public class FileManager {
    public boolean pathExists(String path) {
        File f = new File(path);
        return f.exists();
    }
}
