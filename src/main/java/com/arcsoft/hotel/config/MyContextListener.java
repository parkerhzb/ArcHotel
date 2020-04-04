//package com.arcsoft.hotel.config;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
///**
// * @author HZB
// * @version 1.0
// * @date 2020/3/27
// */
//@WebListener
//@Component
//public class MyContextListener implements ServletContextListener {
//    private SSHConnection conexionssh;
//
//    public MyContextListener() {
//        super();
//    }
//
//    /**
//     * @see ServletContextListener#contextInitialized(ServletContextEvent)
//     */
//    @Override
//    public void contextInitialized(ServletContextEvent arg0) {
//        // 建立连接
//        System.out.println("Context initialized ... !\n");
//        try {
//            conexionssh = new SSHConnection();
//            conexionssh.SSHConnection();
//            System.out.println("**********\n成功建立SSH连接！\n**********");
//        } catch (Throwable e) {
//            System.out.println("**********\nSSH连接失败！\n************");
//            e.printStackTrace(); // error connecting SSH server
//        }
//    }
//
//    /**
//     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
//     */
//    @Override
//    public void contextDestroyed(ServletContextEvent arg0) {
//        // 断开连接
//        System.out.println("Context destroyed ... !\n");
//        try {
//            conexionssh.closeSSH(); // disconnect
//            System.out.println("**********\n成功断开SSH连接!\n**********");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("**********\n断开SSH连接出错！\n**********");
//        }
//    }
//}