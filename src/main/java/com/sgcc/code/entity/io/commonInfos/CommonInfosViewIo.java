package com.sgcc.code.entity.io.commonInfos;

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
 * 项目公共配置信息
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:22 CST 2023
 */
@ApiModel(value="项目公共配置信息请求体",description="项目公共配置信息请求体")
public class CommonInfosViewIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "主键id不能为空!")
	@Min(value = -1000000000, message = "主键id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "主键id值不能大于1000000000！")
    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;

	public CommonInfosViewIo() {
		super();
	}

	public CommonInfosViewIo(Long id) {
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
        return "CommonInfosViewIo{" +
    	" id=" + id +
        "}";
    }

}