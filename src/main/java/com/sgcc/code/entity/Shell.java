package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 脚本管理
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 09:59:00 CST 2023
 */
public class Shell implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private String userid;

    /**
     * 脚本标题
     */
    private String title;

    /**
     * 脚本描述
     */
    private String shellDescribe;

    /**
     * IP
     */
    private String shellIp;

    /**
     * 端口号
     */
    private String shellPort;

    /**
     * 用户名
     */
    private String shellUsername;

    /**
     * 密码
     */
    private String shellPassword;

    /**
     * 脚本内容
     */
    private String shellContent;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;


	public Shell() {
		super();
	}

    public Shell(Integer id, String userid, String title, String shellDescribe, String shellIp, String shellPort, String shellUsername, String shellPassword, String shellContent, Timestamp createTime) {
        this.id = id;
        this.userid = userid;
        this.title = title;
        this.shellDescribe = shellDescribe;
        this.shellIp = shellIp;
        this.shellPort = shellPort;
        this.shellUsername = shellUsername;
        this.shellPassword = shellPassword;
        this.shellContent = shellContent;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShellDescribe() {
        return shellDescribe;
    }

    public void setShellDescribe(String shellDescribe) {
        this.shellDescribe = shellDescribe;
    }

    public String getShellIp() {
        return shellIp;
    }

    public void setShellIp(String shellIp) {
        this.shellIp = shellIp;
    }

    public String getShellPort() {
        return shellPort;
    }

    public void setShellPort(String shellPort) {
        this.shellPort = shellPort;
    }

    public String getShellUsername() {
        return shellUsername;
    }

    public void setShellUsername(String shellUsername) {
        this.shellUsername = shellUsername;
    }

    public String getShellPassword() {
        return shellPassword;
    }

    public void setShellPassword(String shellPassword) {
        this.shellPassword = shellPassword;
    }

    public String getShellContent() {
        return shellContent;
    }

    public void setShellContent(String shellContent) {
        this.shellContent = shellContent;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Shell{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", title='" + title + '\'' +
                ", shellDescribe='" + shellDescribe + '\'' +
                ", shellIp='" + shellIp + '\'' +
                ", shellPort='" + shellPort + '\'' +
                ", shellUsername='" + shellUsername + '\'' +
                ", shellPassword='" + shellPassword + '\'' +
                ", shellContent='" + shellContent + '\'' +
                ", createTime=" + createTime +
                '}';
    }

}