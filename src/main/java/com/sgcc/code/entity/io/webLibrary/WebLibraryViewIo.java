package com.sgcc.code.entity.io.webLibrary;

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
 * 网页库
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 17:53:18 CST 2023
 */
@ApiModel(value="网页库请求体",description="网页库请求体")
public class WebLibraryViewIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "网页库ID不能为空!")
	@Min(value = -1000000000, message = "网页库ID值不能小于-1000000000！")
	@Max(value = 1000000000, message = "网页库ID值不能大于1000000000！")
    @ApiModelProperty(value="网页库ID",name="id",example = "1")
    private Integer id;

	public WebLibraryViewIo() {
		super();
	}

	public WebLibraryViewIo(Integer id) {
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
        return "WebLibraryViewIo{" +
    	" id=" + id +
        "}";
    }

}