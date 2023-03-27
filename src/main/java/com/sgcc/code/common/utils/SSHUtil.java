package com.sgcc.code.common.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * SSH工具类（可远程执行其它Linux机器上的Shell命令）
 * Created by jimmy on 2015/7/6.
 * http://code.taobao.org/p/y-lib/src/trunk/src/main/java/com/cnblogs/yjmyzz/utils/SSHUtil.java
 */
public class SSHUtil {

    /**
     * 连接到主机
     *
     * @param hostname
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    private static Connection getConnection(String hostname, int port, String username, String password) throws Exception {
        Connection conn = null;
        try {
            conn = new Connection(hostname, port);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
        } catch (Exception e) {
            throw new IOException("username or password error.");
        }
        return conn;
    }

    /**
     * 执行远程命令
     *
     * @param hostname 远程主机IP
     * @param username 用户名
     * @param password 密码
     * @param command  需要执行的命令
     * @param timeout  超时时间（秒）
     * @return
     * @throws Exception
     */
    public static String execRemoteCommand(String hostname, int port, String username, String password, String command, long timeout)
            throws Exception {
        //Connection conn = getConnection(hostname, port, username, password);
        StringBuilder sb = new StringBuilder();
        Session session = null;
        Connection conn = null;
        try {
            conn = new Connection(hostname, port);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                return "连接失败，用户名或密码错误！";
            }
            session = conn.openSession();
            session.requestPTY("vt100", 80, 24, 640, 480, null);
            session.execCommand(command);
            InputStream stdout = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            long start = System.currentTimeMillis();
            char[] arr = new char[512];
            int read;
            int i = 0;
            while (true) {
                read = br.read(arr, 0, arr.length);
                if (read < 0 || (System.currentTimeMillis() - start) > timeout * 1000) {
                    break;
                }
                sb.append(new String(arr, 0, read));
                i++;
            }
        } finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return sb.toString();
    }

    /**
     * 执行远程命令（默认20秒超时）
     *
     * @param hostname 远程主机IP
     * @param username 用户名
     * @param password 密码
     * @param command  需要执行的命令
     * @return
     * @throws Exception
     */
    public static String execRemoteCommand(String hostname, int port, String username, String password, String command)
            throws Exception {
        return execRemoteCommand(hostname, port, username, password, command, 20);
    }

    /**
     * 批量执行远程命令
     *
     * @param hostname 远程主机IP
     * @param username 用户名
     * @param password 密码
     * @param command  需要执行的命令列表
     * @param timeout  超时时间（秒）
     * @return
     * @throws Exception
     */
    public static String execRemoteCommand(String hostname, int port, String username, String password, String[] command, long timeout)
            throws Exception {
        Connection conn = getConnection(hostname, port, username, password);
        StringBuilder sb = new StringBuilder();
        Session session = null;
        try {
            for (int t = 0; t < command.length; t++) {
                session = conn.openSession();
                session.requestPTY("vt100", 80, 24, 640, 480, null);
                session.execCommand(command[t]);
                InputStream stdout = new StreamGobbler(session.getStdout());
                BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                long start = System.currentTimeMillis();
                char[] arr = new char[512];
                int read;
                int i = 0;
                while (true) {
                    read = br.read(arr, 0, arr.length);
                    if (read < 0 || (System.currentTimeMillis() - start) > timeout * 1000) {
                        break;
                    }
                    sb.append(new String(arr, 0, read));
                    i++;
                }
                session.close();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return sb.toString();
    }

    /**
     * 批量执行远程命令(默认5秒超时)
     *
     * @param hostname 远程主机IP
     * @param username 用户名
     * @param password 密码
     * @param command  需要执行的命令列表
     * @return
     * @throws Exception
     */
    public static String execRemoteCommand(String hostname, int port, String username, String password, String[] command)
            throws Exception {
        return execRemoteCommand(hostname, port, username, password, command, 5);
    }
}