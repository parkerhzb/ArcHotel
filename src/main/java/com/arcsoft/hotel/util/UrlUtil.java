package com.arcsoft.hotel.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

public class UrlUtil {
//    private final Logger LOG = LogManager.getLogger(this.getClass());

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url      发送请求的 URL
     * @param paramMap 请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送GET请求
     *
     * @param url        String
     * @param parameters Map【String, Object】
     * @return
     */
    public static String sendGet(String url, Map<String, Object> parameters) {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=")
                            .append(java.net.URLEncoder.encode(String.valueOf(parameters.get(name)), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=")
                            .append(java.net.URLEncoder.encode(String.valueOf(parameters.get(name)), "UTF-8"))
                            .append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            // System.out.println(full_url);
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(full_url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            // for (String key : headers.keySet()) {
            // System.out.println(key + "\t：\t" + headers.get(key));
            // }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    // 发送GET 请求举例
    // ******************************************************************************
    // Map<String,Object> params = new HashMap<String,Object>();
    // params.put("user", "lgg");
    // String result = HttpUtil.sendGet("https://mp.csdn.net/postedit", params);
    // System.out.println("结果是:"+result);
    // ******************************************************************************

    // 发送POST一般请求
    // ******************************************************************************
    // Map<String,Object> params = new HashMap<String,Object>();
    // params.put("user", "lgg");
    // String result = HttpUtil.sendPost("https://mp.csdn.net/postedit", params);
    // System.out.println("结果是:"+result);
    // ******************************************************************************

    // 发送POST的json请求
    // ******************************************************************************
    // User user = new User();
    // user.setName("张三");
    // user.setAge(18);
    // Map<String,Object> params = new HashMap<String,Object>();
    // params.put("queryJson", JSON.toJSONString(user));
    // String result = HttpUtil.sendPost("https://mp.csdn.net/postedit", params);
    // System.out.println("结果是:"+result);
    // ******************************************************************************
}

