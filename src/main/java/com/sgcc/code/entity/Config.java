package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 配置
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 项目id
     */
    private Integer projectid;
    
    /**
     * 配置名称
     */
    private String name;
    
    /**
     * 配置描述
     */
    private String configDescribe;
    
    /**
     * 生成代码根目录
     */
    private String path;
    
    /**
     * 数据库类型 1mysql 2oracle
     */
    private Integer dbtype;
    
    /**
     * 数据库地址
     */
    private String dbhost;

    /**
     * 数据集端口
     */
    private Integer dbport;

    /**
     * 数据库名称
     */
    private String dbname;
    
    /**
     * 数据库用户
     */
    private String dbuser;
    
    /**
     * 数据库密码
     */
    private String dbpassword;
    
    /**
     * 用户id
     */
    private String userid;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    

	public Config() {
		super();
	}

    public Config(Long id, Integer projectid, String name, String configDescribe, String path, Integer dbtype, String dbhost, Integer dbport, String dbname, String dbuser, String dbpassword, String userid, Date createTime) {
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
        return "Config{" +
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