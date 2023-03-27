package com.sgcc.code.entity.io.shell;

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
 * 脚本管理
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 09:59:00 CST 2023
 */
@ApiModel(value="脚本管理请求体",description="脚本管理请求体")
public class ShellViewIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "脚本ID不能为空!")
	@Min(value = -1000000000, message = "脚本ID值不能小于-1000000000！")
	@Max(value = 1000000000, message = "脚本ID值不能大于1000000000！")
    @ApiModelProperty(value="脚本ID",name="id",example = "1")
    private Integer id;

	public ShellViewIo() {
		super();
	}

	public ShellViewIo(Integer id) {
		super();
		this.id = id;
	}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


	@Override
    public String toString() {
        return "ShellViewIo{" +
    	" id=" + id +
        "}";
    }

}