package com.sgcc.code.entity.io.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * <p>
 * 配置
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
@ApiModel(value="配置请求体",description="配置请求体")
public class ConfigViewIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "主键id不能为空!")
	@Min(value = -1000000000, message = "主键id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "主键id值不能大于1000000000！")
    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;

	public ConfigViewIo() {
		super();
	}

	public ConfigViewIo(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ConfigViewIo{" +
				"id=" + id +
				'}';
	}
}