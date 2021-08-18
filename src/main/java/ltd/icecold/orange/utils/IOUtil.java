package ltd.icecold.orange.utils;


import java.io.*;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class IOUtil {
    /**
     *
     * @param f 文件
     * @return 返回文件内的字符串 使用UTF-8编码
     */
    public static String readFile(File f) {
        if(!f.exists()) {
            throw new NullPointerException("文件不存在");
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
     *
     * @param f 要写入的文件
     * @param data 数据
     */
    public static void writeFile(File f, String data) {
        writeFile(f, data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     *
     * @param f 要写入的文件
     * @param data 数据
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
