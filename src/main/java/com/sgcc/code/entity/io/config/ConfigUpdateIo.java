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
@ApiModel(value="配置修改参数类",description="修改配置请求体")
public class ConfigUpdateIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "主键id不能为空!")
	@Min(value = -1000000000, message = "主键id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "主键id值不能大于1000000000！")
    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
	//@NotNull(message = "项目id不能为空!")
    @Min(value = -1000000000, message = "项目id值不能小于-1000000000！")
    @Max(value = 1000000000, message = "项目id值不能大于1000000000！")
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private Integer projectid;
    
	//@NotNull(message = "配置名称不能为空!")
	@Size(max = 32, message = "配置名称最多32个字符！")
    @ApiModelProperty(value="配置名称",name="name",example = "1")
    private String name;
    
	//@NotNull(message = "配置描述不能为空!")
	@Size(max = 200, message = "配置描述最多200个字符！")
    @ApiModelProperty(value="配置描述",name="configDescribe",example = "1")
    private String configDescribe;
    
	//@NotNull(message = "生成代码根目录不能为空!")
	@Size(max = 100, message = "生成代码根目录最多100个字符！")
    @ApiModelProperty(value="生成代码根目录",name="path",example = "1")
    private String path;
    
	//@NotNull(message = "数据库类型 1mysql 2oracle不能为空!")
	@Min(value = -1000000000, message = "数据库类型 1mysql 2oracle值不能小于-1000000000！")
	@Max(value = 1000000000, message = "数据库类型 1mysql 2oracle值不能大于1000000000！")
    @ApiModelProperty(value="数据库类型 1mysql 2oracle",name="dbtype",example = "1")
    private Integer dbtype;
    
	//@NotNull(message = "数据库地址不能为空!")
	@Size(max = 100, message = "数据库地址最多100个字符！")
    @ApiModelProperty(value="数据库地址",name="dbhost",example = "1")
    private String dbhost;

    //@NotNull(message = "数据库端口不能为空！")
    @Min(value = -1000000000, message = "数据库端口值不能小于-1000000000！")
    @Max(value = 1000000000, message = "数据库端口值不能大于1000000000！")
    @ApiModelProperty(value="数据库端口",name="dbport",example = "1")
    private Integer dbport;
    
	//@NotNull(message = "数据库名称不能为空!")
	@Size(max = 100, message = "数据库名称最多100个字符！")
    @ApiModelProperty(value="数据库名称",name="dbname",example = "1")
    private String dbname;
    
	//@NotNull(message = "数据库用户不能为空!")
	@Size(max = 100, message = "数据库用户最多100个字符！")
    @ApiModelProperty(value="数据库用户",name="dbuser",example = "1")
    private String dbuser;
    
	//@NotNull(message = "数据库密码不能为空!")
	@Size(max = 100, message = "数据库密码最多100个字符！")
    @ApiModelProperty(value="数据库密码",name="dbpassword",example = "1")
    private String dbpassword;
    
	//@NotNull(message = "用户id不能为空!")
	@Size(max = 32, message = "用户id最多32个字符！")
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;
    
	//@NotNull(message = "创建时间不能为空!")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
	public ConfigUpdateIo() {
		super();
	}

    public ConfigUpdateIo(Long id, Integer projectid, String name, String configDescribe, String path, Integer dbtype, String dbhost, Integer dbport, String dbname, String dbuser, String dbpassword, String userid, Date createTime) {
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

    @Override
    public String toString() {
        return "ConfigUpdateIo{" +
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
                '}';
    }
}