//package com.arcsoft.hotel.config;
//
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.Session;
//
///**
// * @author HZB
// * @version 1.0
// * @date 2020/3/27
// */
//public class SSHConnection {
//    String user = "root";// 服务器登录名
//
//    String password = "Sfx1017cyt0526";  // 登陆密码
//
//    String host = "101.132.173.219";//服务器公网IP
//
//    int port = 22;// 跳板机ssh开放的接口   默认端口 22
//
//    int local_port = 3306;// 这个是本地的端口，很重要！！！选取一个没有占用的port即可
//
//    String remote_host = "127.0.0.1";//"172.19.185.112";// 要访问的mysql所在的host    服务器局域网IP（127.0.0.1也行）
//
//    int remote_port = 3307;// 服务器上数据库端口号
//
//    Session session = null;
//    /**
//     *    建立SSH连接
//     */
//    public void SSHConnection() throws Exception{
//        try {
//            JSch jsch = new JSch();
//            session = jsch.getSession(user, host, port);
//            session.setPassword(password);
//            session.setConfig("StrictHostKeyChecking", "no");
//            // 日志打印自己脑补
//            session.connect();
//            session.setPortForwardingL(local_port, remote_host, remote_port);
//        } catch (Exception e) {
//            // do something
//        }
//    }
//    /**
//     *    断开SSH连接
//     */
//    public void closeSSH () throws Exception
//    {
//        this.session.disconnect();
//    }
//}
