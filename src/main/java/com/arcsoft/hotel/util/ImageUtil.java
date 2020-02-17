package com.arcsoft.hotel.util;

import sun.misc.BASE64Decoder;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;

public class ImageUtil {

    /**
     * 图片转化为byte数组
     *
     * @param path
     * @return
     */
    public byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    /**
     * byte数组转化为图片
     *
     * @param data
     * @param path
     */
    public void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) {
            return;
        }
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    /**
     * byte数组到16进制字符串
     *
     * @param data
     * @return
     */
    public String byte2string(byte[] data) {
        if (data == null || data.length <= 1) {
            return "0x";
        }
        if (data.length > 200000) {
            return "0x";
        }
        StringBuffer sb = new StringBuffer();
        int buf[] = new int[data.length];
        //byte数组转化成十进制
        for (int k = 0; k < data.length; k++) {
            buf[k] = data[k] < 0 ? (data[k] + 256) : (data[k]);
        }
        //十进制转化成十六进制
        for (int k = 0; k < buf.length; k++) {
            if (buf[k] < 16) {
                sb.append("0" + Integer.toHexString(buf[k]));
            } else {
                sb.append(Integer.toHexString(buf[k]));
            }
        }
        return "0x" + sb.toString().toUpperCase();
    }

    /*
    BASE64图片转码，string file数据转存图片,路径为path
     */
    public void savePhoto(String file, String path) throws IOException {
        /////图片数据接收
        String StrFace = file.replace(' ', '+');
        //StrFace= URLDecoder.decode(StrFace,"UTF-8");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(StrFace);
        // Base64解码
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {// 调整异常数据
                b[i] += 256;
            }
        }
        // 生成jpeg图片
        OutputStream out = new FileOutputStream(path);////////新生成的图片
        out.write(b);
        out.flush();
        out.close();
    }
}