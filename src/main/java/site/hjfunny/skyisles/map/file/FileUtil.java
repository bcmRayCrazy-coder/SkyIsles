package site.hjfunny.skyisles.map.file;

import java.io.File;

public class FileUtil {
    public static void delete(File file) {
        if (file.isDirectory()) {
            //noinspection ConstantConditions
            for (File subfile : file.listFiles()) {
                delete(subfile);
            }
        } else {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
    }
}
