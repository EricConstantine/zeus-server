package com.sgcc.code.entity.io.shell;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 脚本管理
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 09:59:00 CST 2023
 */
@ApiModel(value="脚本管理默认返回参数类",description="脚本管理接口默认返回类")
public class ShellIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="脚本ID",name="id",example = "1")
    private Integer id;

    @ApiModelProperty(value="用户ID",name="userid",example = "1")
    private String userid;

    @ApiModelProperty(value="脚本标题",name="title",example = "1")
    private String title;
    
    @ApiModelProperty(value="脚本描述",name="shellDescribe",example = "1")
    private String shellDescribe;
    
    @ApiModelProperty(value="IP",name="shellIp",example = "1")
    private String shellIp;
    
    @ApiModelProperty(value="端口号",name="shellPort",example = "1")
    private String shellPort;
    
    @ApiModelProperty(value="用户名",name="shellUsername",example = "1")
    private String shellUsername;
    
    @ApiModelProperty(value="密码",name="shellPassword",example = "1")
    private String shellPassword;
    
    @ApiModelProperty(value="脚本内容",name="shellContent",example = "1")
    private String shellContent;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    

	public ShellIo() {
		super();
	}

    public ShellIo(Integer id, String userid, String title, String shellDescribe, String shellIp, String shellPort, String shellUsername, String shellPassword, String shellContent, Timestamp createTime) {
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
        return "ShellIo{" +
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