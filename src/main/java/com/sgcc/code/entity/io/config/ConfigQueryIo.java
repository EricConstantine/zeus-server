package com.sgcc.code.entity.io.config;

import java.io.Serializable;
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
 * 配置
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
@ApiModel(value="配置查询参数类",description="查询配置请求体")
public class ConfigQueryIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @Max(value = 1000000000, message = "项目id值不能大于1000000000")
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private Integer projectid;

	@Size(max = 32, message = "配置名称最多32个字符！")
    @ApiModelProperty(value="配置名称",name="name",example = "1")
    private String name;

	@Size(max = 200, message = "配置描述最多200个字符！")
    @ApiModelProperty(value="配置描述",name="configDescribe",example = "1")
    private String configDescribe;

	@Size(max = 100, message = "生成代码根目录最多100个字符！")
    @ApiModelProperty(value="生成代码根目录",name="path",example = "1")
    private String path;

	@Max(value = 1000000000, message = "数据库类型 1mysql 2oracle值不能大于1000000000")
    @ApiModelProperty(value="数据库类型 1mysql 2oracle",name="dbtype",example = "1")
    private Integer dbtype;

	@Size(max = 100, message = "数据库地址最多100个字符！")
    @ApiModelProperty(value="数据库地址",name="dbhost",example = "1")
    private String dbhost;

    @Max(value = 1000000000, message = "数据库端口值不能大于1000000000")
    @ApiModelProperty(value="数据库端口",name="dbport",example = "1")
    private Integer dbport;

	@Size(max = 100, message = "数据库名称最多100个字符！")
    @ApiModelProperty(value="数据库名称",name="dbname",example = "1")
    private String dbname;

	@Size(max = 100, message = "数据库用户最多100个字符！")
    @ApiModelProperty(value="数据库用户",name="dbuser",example = "1")
    private String dbuser;

	@Size(max = 100, message = "数据库密码最多100个字符！")
    @ApiModelProperty(value="数据库密码",name="dbpassword",example = "1")
    private String dbpassword;

	@Size(max = 32, message = "用户id最多32个字符！")
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;

    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;

	public ConfigQueryIo() {
		super();
	}

    public ConfigQueryIo(Integer projectid, String name, String configDescribe, String path, Integer dbtype, String dbhost, Integer dbport, String dbname, String dbuser, String dbpassword, String userid, Date createTime) {
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

    @Override
    public String toString() {
        return "ConfigQueryIo{" +
                "projectid=" + projectid +
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
                '}';
    }
}