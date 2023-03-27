package com.sgcc.code.entity.io.versions;

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
 * 版本
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 16:35:58 CST 2023
 */
@ApiModel(value="版本请求体",description="版本请求体")
public class VersionsViewIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "主键id不能为空!")
	@Size(max = 32, message = "主键id最多32个字符！")
    @ApiModelProperty(value="主键id",name="versionid",example = "1")
    private String versionid;

	public VersionsViewIo() {
		super();
	}

	public VersionsViewIo(String versionid) {
		super();
		this.versionid = versionid;
	}

    public String getVersionid() {
        return this.versionid;
    }

    public void setVersionid(String versionid) {
        this.versionid = versionid;
    }


	@Override
    public String toString() {
        return "VersionsViewIo{" +
    	" versionid=" + versionid +
        "}";
    }

}