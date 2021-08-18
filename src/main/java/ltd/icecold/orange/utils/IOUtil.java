package ltd.icecold.orange.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class IOUtil {
    /**
     * @param f File
     * @return Returns the string in the file using UTF-8 encoding
     */
    public static String readFile(File f) {
        if (!f.exists()) {
            throw new NullPointerException("File does not exist");
        }
        try {
            BufferedInputStream biss = new BufferedInputStream(new FileInputStream(f));
            byte[] b = new byte[biss.available()];
            biss.read(b);
            biss.close();
            return new String(b, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param f    File to be written
     * @param data Data
     */
    public static void writeFile(File f, String data) {
        writeFile(f, data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param f    File to be written
     * @param data Data
     */
    public static void writeFile(File f, byte[] data) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedOutputStream boss = new BufferedOutputStream(new FileOutputStream(f));
            boss.write(data);
            boss.flush();
            boss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
