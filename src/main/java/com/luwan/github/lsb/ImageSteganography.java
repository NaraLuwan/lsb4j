package com.luwan.github.lsb;

import com.luwan.github.lsb.utils.AesCrypt;
import com.luwan.github.lsb.utils.ImageUtils;
import com.luwan.github.lsb.utils.LsbUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author luwan
 * @date 2019/5/29
 */
public class ImageSteganography {

    private static char[] KEY = {'l', 's', 'b'};

    /**
     * 将数据隐写到图片
     *
     * @param data      待隐写的数据
     * @param password  AES加密数据的密码
     * @param imagePath 图片路径
     * @return
     */
    public static boolean toImg(String data, String password, String imagePath) {
        File imageFile = new File(imagePath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imageFile);
            BufferedImage image = LsbUtils.streamToImage(inputStream);

            final int imageLength = image.getHeight() * image.getWidth();
            final int startingOffset = LsbUtils.calculateStartingOffset(null, imageLength);
            // hide text
            Steganography steganography = new Steganography();
            data = AesCrypt.encrypt(password, data);
            data = encryptDecrypt(password + "|" + data);
            steganography.encode(image, data, startingOffset);
            return ImageIO.write(image, ImageUtils.getFileExt(imageFile), imageFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't find file " + imagePath, e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * 从图片中读取隐写的数据
     *
     * @param inputStream
     * @return
     */
    public static String fromImg(InputStream inputStream) {
        try {
            BufferedImage image = LsbUtils.streamToImage(inputStream);

            final int imageLength = image.getWidth() * image.getHeight();
            final int startingOffset = LsbUtils.calculateStartingOffset(null, imageLength);

            Steganography steganography = new Steganography();
            String data = steganography.decode(image, startingOffset);
            data = encryptDecrypt(data);
            int indexOf = data.indexOf("|");
            String password = data.substring(0, indexOf);
            data = data.substring(indexOf);
            return AesCrypt.decrypt(password, data);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't find file ", e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * 简单加解密
     *
     * @param input
     * @return
     */
    private static String encryptDecrypt(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ KEY[i % KEY.length]));
        }
        return output.toString();
    }

}
