package com.arcsoft.hotel.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by halfsking on 2016/3/29.
 */
public class ImageCompressUtil {

    // private static final ILog LOG = LogFactory.getLog(ImageCompressUtil.class);

    public static byte[] compressImage(byte[] data) {
        return compressImage(data, 600, 400, 0.85f);
    }

    public static byte[] compressImage(byte[] data, int width, int height) {
        return compressImage(data, width, height, 0.85f);
    }

    public static byte[] compressImage(byte[] data, int width, int height, float quality) {
        width = width > 600 ? 600 : width;//限定宽度为600
        height = height > 400 ? 400 : height;//限定高度为400
        quality = quality < 0.8f ? 0.8f : quality;
        int maxLength = 71 * 1024;//70K以下不压缩
        if (data == null || data.length < maxLength) {
            return data;
        }
        try {
            InputStream inputStream = new ByteArrayInputStream(data);
            Image oriImg = ImageIO.read(inputStream);
            int ori_width = oriImg.getWidth(null);
            int ori_height = oriImg.getHeight(null);
            if (width > ori_width || height > ori_height) {
                return data;
            }
            float ori_rate = (float) ori_width / (float) ori_height;
            float rate = (float) width / (float) height;
            if (ori_rate > rate) {
                return compressByWidth(ori_width, ori_height, width, oriImg, quality);
            } else {
                return compressByHeight(ori_width, ori_height, height, oriImg, quality);
            }
        } catch (IOException e) {
            System.out.println("ImageCompressUtil compressImage IOException failed," + e);
        }
        System.out.println("ImageCompressUtil compressImage failed,now return the origin data.");
        return data;
    }

    /**
     * 以宽度为基准，等比例放缩图片
     *
     * @param w int 新宽度
     */
    private static byte[] compressByWidth(int ori_width, int ori_height, int w, Image oriImg, float quality) throws IOException {
        int h = (int) (ori_height * w / ori_width);
        return compress(w, h, oriImg, quality);
    }

    /**
     * 以高度为基准，等比例缩放图片
     *
     * @param h int 新高度
     */
    private static byte[] compressByHeight(int ori_width, int ori_height, int h, Image oriImg, float quality) throws IOException {
        int w = (int) (ori_width * h / ori_height);
        return compress(w, h, oriImg, quality);
    }

    /**
     * 压缩/放大图片到固定的大小
     *
     * @param w int 新宽度
     * @param h int 新高度
     */
    private static byte[] compress(int w, int h, Image oriImg, float quality) throws IOException {

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        //image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        image.getGraphics().drawImage(oriImg.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 可以正常实现bmp、png、gif转jpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(image);
        /* 压缩质量 */
        jep.setQuality(quality, true);
        encoder.encode(image, jep);// JPEG编码
        return out.toByteArray();
    }
}