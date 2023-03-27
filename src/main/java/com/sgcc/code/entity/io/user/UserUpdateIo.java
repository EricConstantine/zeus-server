package com.sgcc.code.entity.io.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author yangrui
 * @since Wed Dec 28 14:46:28 CST 2022
 */
@ApiModel(value="用户修改参数类",description="修改用户请求体")
public class UserUpdateIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "主键id不能为空!")
	@Min(value = -1000000000, message = "主键id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "主键id值不能大于1000000000！")
    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Integer id;
    
	//@NotNull(message = "用户id不能为空!")
	@Size(max = 32, message = "用户id最多32个字符！")
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;
    
	//@NotNull(message = "用户名称不能为空!")
	@Size(max = 100, message = "用户名称最多100个字符！")
    @ApiModelProperty(value="用户名称",name="username",example = "1")
    private String username;
    
	@NotNull(message = "用户密码不能为空!")
	@Size(max = 100, message = "用户密码最多100个字符！")
    @ApiModelProperty(value="用户密码",name="password",example = "1")
    private String password;
    
	//@NotNull(message = "用户类型 1系统管理员 2普通用户不能为空!")
	@Min(value = -1000000000, message = "用户类型 1系统管理员 2普通用户值不能小于-1000000000！")
	@Max(value = 1000000000, message = "用户类型 1系统管理员 2普通用户值不能大于1000000000！")
    @ApiModelProperty(value="用户类型 1系统管理员 2普通用户",name="type",example = "1")
    private Integer type;

    //@NotNull(message = "用户昵称不能为空！")
    @Size(max = 32, message = "用户昵称最多32个字符！")
    @ApiModelProperty(value="用户昵称",name="nickname",example = "1")
    private String nickname;

    //@NotNull(message = "用户头像不能为空！")
    @Size(max = 100, message = "用户头像最多100个字符！")
    @ApiModelProperty(value="用户头像",name="picture",example = "1")
    private String picture;

    //@NotNull(message = "邮箱不能为空！")
    @Size(max = 32, message = "邮箱最多32个字符！")
    @ApiModelProperty(value="邮箱",name="email",example = "1")
    private String email;

    //@NotNull(message = "手机号不能为空！")
    @Size(max = 32, message = "手机号最多32个字符！")
    @ApiModelProperty(value="手机号",name="phone",example = "1")
    private String phone;
    
	//@NotNull(message = "创建时间不能为空!")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
	public UserUpdateIo() {
		super();
	}

    public UserUpdateIo(Integer id, String userid, String username, String password, Integer type, String nickname, String picture, String email, String phone, Date createTime) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserUpdateIo{" +
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