package com.sgcc.code.entity.io.config;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 配置
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
@ApiModel(value="配置默认返回参数类",description="配置接口默认返回类")
public class ConfigIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private Integer projectid;
    
    @ApiModelProperty(value="配置名称",name="name",example = "1")
    private String name;
    
    @ApiModelProperty(value="配置描述",name="configDescribe",example = "1")
    private String configDescribe;
    
    @ApiModelProperty(value="生成代码根目录",name="path",example = "1")
    private String path;
    
    @ApiModelProperty(value="数据库类型 1mysql 2oracle",name="dbtype",example = "1")
    private Integer dbtype;
    
    @ApiModelProperty(value="数据库地址",name="dbhost",example = "1")
    private String dbhost;

    @ApiModelProperty(value="数据库端口",name="dbport",example = "1")
    private Integer dbport;

    @ApiModelProperty(value="数据库名称",name="dbname",example = "1")
    private String dbname;
    
    @ApiModelProperty(value="数据库用户",name="dbuser",example = "1")
    private String dbuser;
    
    @ApiModelProperty(value="数据库密码",name="dbpassword",example = "1")
    private String dbpassword;
    
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;

    //扩展字段
    @ApiModelProperty(value="项目名称",name="projectName",example = "1")
    private String projectName;

	public ConfigIo() {
		super();
	}

    public ConfigIo(Long id, Integer projectid, String name, String configDescribe, String path, Integer dbtype, String dbhost, Integer dbport, String dbname, String dbuser, String dbpassword, String userid, Date createTime, String projectName) {
        this.id = id;
        this.projectid = projectid;
        this.name = name;
        this.configDescribe = configDescribe;
        this.path = path;
        this.dbtype = dbtype;
        this.dbhost = dbhost;
        this.dbport = dbport;
        this.dbname = dbname;
        this.dbuser = dbuser;
        this.dbpassword = dbpassword;
        this.userid = userid;
        this.createTime = createTime;
        this.projectName = projectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfigDescribe() {
        return configDescribe;
    }

    public void setConfigDescribe(String configDescribe) {
        this.configDescribe = configDescribe;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getDbtype() {
        return dbtype;
    }

    public void setDbtype(Integer dbtype) {
        this.dbtype = dbtype;
    }

    public String getDbhost() {
        return dbhost;
    }

    public void setDbhost(String dbhost) {
        this.dbhost = dbhost;
    }

    public Integer getDbport() {
        return dbport;
    }

    public void setDbport(Integer dbport) {
        this.dbport = dbport;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "ConfigIo{" +
                "id=" + id +
                ", projectid=" + projectid +
                ", name='" + name + '\'' +
                ", configDescribe='" + configDescribe + '\'' +
                ", path='" + path + '\'' +
                ", dbtype=" + dbtype +
                ", dbhost='" + dbhost + '\'' +
                ", dbport=" + dbport +
                ", dbname='" + dbname + '\'' +
                ", dbuser='" + dbuser + '\'' +
                ", dbpassword='" + dbpassword + '\'' +
                ", userid='" + userid + '\'' +
                ", createTime=" + createTime +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}