package site.hjfunny.skyisles.map.file;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class ZipFileUtil {
    /**
     * 解压文件
     * @param file 文件
     * @param homeParentDir 目标目录
     * @throws IOException 解压失败
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void unzipFileIntoDirectory(File file, File homeParentDir) throws IOException {
        if (!file.exists()) return;
        ZipFile zipFile = new ZipFile(file);
        Enumeration<?> files = zipFile.entries();
        File f;
        FileOutputStream fos = null;

        while (files.hasMoreElements()) {
            try {
                ZipEntry entry = (ZipEntry) files.nextElement();
                InputStream eis = zipFile.getInputStream(entry);
                byte[] buffer = new byte[1024];
                int bytesRead;

                f = new File(homeParentDir.getAbsolutePath(), entry.getName());

                if (entry.isDirectory()) {
                    f.mkdirs();
                    continue;
                } else {
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                }

                fos = new FileOutputStream(f);

                while ((bytesRead = eis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException ignored) {
                        ignored.printStackTrace();
                    }
                }
            }
        }
    }
}