package com.sgcc.code.entity.io.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Size;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author yangrui
 * @since Wed Dec 28 14:46:28 CST 2022
 */
@ApiModel(value="用户默认返回参数类",description="用户接口默认返回类")
public class UserIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Integer id;
    
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;
    
    @ApiModelProperty(value="用户名称",name="username",example = "1")
    private String username;
    
    @ApiModelProperty(value="用户密码",name="password",example = "1")
    private String password;
    
    @ApiModelProperty(value="用户类型 1系统管理员 2普通用户",name="type",example = "1")
    private Integer type;

    @ApiModelProperty(value="用户昵称",name="nickname",example = "1")
    private String nickname;

    @ApiModelProperty(value="用户头像",name="picture",example = "1")
    private String picture;

    @ApiModelProperty(value="邮箱",name="email",example = "1")
    private String email;

    @ApiModelProperty(value="手机号",name="phone",example = "1")
    private String phone;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    

	public UserIo() {
		super();
	}

    public UserIo(Integer id, String userid, String username, String password, Integer type, String nickname, String picture, String email, String phone, Timestamp createTime) {
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
        return "UserIo{" +
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