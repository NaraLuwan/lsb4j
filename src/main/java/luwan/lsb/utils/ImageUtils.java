package luwan.lsb.utils;

import java.io.File;

/**
 * @author NaraLuwan
 * @date 2019/5/29
 */
public class ImageUtils {

    private ImageUtils() {
        // Prevent initialization
    }

    /**
     * 获取图片拓展名
     *
     * @param image
     * @return
     */
    public static String getFileExt(File image) {
        String name = image.getName();
        return name.substring(name.indexOf(".") + 1);
    }
}
