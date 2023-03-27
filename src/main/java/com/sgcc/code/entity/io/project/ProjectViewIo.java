package com.sgcc.code.entity.io.project;

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
 * 项目
 * </p>
 *
 * @author yangrui
 * @since Thu Dec 29 17:20:21 CST 2022
 */
@ApiModel(value="项目请求体",description="项目请求体")
public class ProjectViewIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "项目id不能为空!")
	@Min(value = -1000000000, message = "项目id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "项目id值不能大于1000000000！")
    @ApiModelProperty(value="项目id",name="id",example = "1")
    private Long id;

	public ProjectViewIo() {
		super();
	}

	public ProjectViewIo(Long id) {
		super();
		this.id = id;
	}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	@Override
    public String toString() {
        return "ProjectViewIo{" +
    	" id=" + id +
        "}";
    }

}