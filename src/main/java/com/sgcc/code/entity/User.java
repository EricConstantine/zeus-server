package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author yangrui
 * @since Wed Dec 28 14:46:28 CST 2022
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 用户id
     */
    private String userid;
    
    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
    
    /**
     * 用户类型 1系统管理员 2普通用户
     */
    private Integer type;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String picture;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    

	public User() {
		super();
	}

    public User(Integer id, String userid, String username, String password, Integer type, String nickname, String picture, String email, String phone, Timestamp createTime) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.type = type;
        this.nickname = nickname;
        this.picture = picture;
        this.email = email;
        this.phone = phone;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", nickname='" + nickname + '\'' +
                ", picture='" + picture + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}