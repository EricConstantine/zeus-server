package com.sgcc.code.entity.io.zonetree;

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
 * 空间树
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 09:58:13 CST 2022
 */
@ApiModel(value="空间树请求体",description="空间树请求体")
public class ZonetreeViewIo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="空间树id",name="id",example = "1")
	private String id;

	@ApiModelProperty(value="项目id",name="projectid",example = "1")
	private String projectid;

	public ZonetreeViewIo() {
		super();
	}

	public ZonetreeViewIo(String id, String projectid) {
		this.id = id;
		this.projectid = projectid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	@Override
	public String toString() {
		return "ZonetreeViewIo{" +
				"id='" + id + '\'' +
				", projectid='" + projectid + '\'' +
				'}';
	}
}