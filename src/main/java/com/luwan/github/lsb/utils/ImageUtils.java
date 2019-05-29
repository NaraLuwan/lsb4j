package com.luwan.github.lsb.utils;


import com.luwan.github.lsb.base.Constants;
import com.luwan.github.lsb.base.ImageType;

import javax.activation.MimetypesFileTypeMap;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;

/**
 * Utility class holding methods for image processing
 *
 * @author luwan
 * @date 2019/5/29
 */
public class ImageUtils {

    private ImageUtils() {
        // Prevent initialization
    }

    /**
     * Check if the given message has <b>alpha</b> channel pixels
     *
     * @param image The image to be inspected for alpha channel
     * @return <b>true</b> if the image contains alpha channel pixels, otherwise - <b>false</b>
     */
    public static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage) image;
            return bimage.getColorModel().hasAlpha();
        }

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }

    /**
     * Gets the file MIME type
     *
     * @param image The file to be inspected
     * @return The file type
     */
    public static ImageType getImageMimeType(final File image) {
        final MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
        final String mimeType = mimeTypesMap.getContentType(image);

        ImageType imageType = null;

        if (ImageType.JPG.equals(mimeType)) {
            imageType = ImageType.JPG;
        } else if (ImageType.PNG.equals(mimeType)) {
            imageType = ImageType.PNG;
        }

        return imageType;
    }

    /**
     * Converts <b>String</b> value to value of <b>ImageType</b> enumeration.
     *
     * @param imageTypeStr Image type as String
     * @return Image type as enumeration value
     */
    public static ImageType getImageType(String imageTypeStr) {
        ImageType imageType;

        if (Constants.PARAM_IMG_TYPE_JPG.equals(imageTypeStr)) {
            imageType = ImageType.JPG;
        } else if (Constants.PARAM_IMG_TYPE_PNG.equals(imageTypeStr)) {
            imageType = ImageType.PNG;
        } else {
            throw new RuntimeException("Not supported media type!");
        }

        return imageType;
    }

    /**
     * Get file extension.
     *
     * @param image image file
     * @return file's extension
     */
    public static String getFileExt(File image) {
        String name = image.getName();
        int lastDotIndex = name.indexOf(".");
        return name.substring(lastDotIndex + 1);
    }
}
