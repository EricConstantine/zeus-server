package com.sgcc.code.entity.io.zonetree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 空间树
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 09:58:13 CST 2022
 */
@ApiModel(value="空间树删除参数类",description="删除空间树请求体")
public class ZonetreeDeleteIo implements Serializable {

    private static final long serialVersionUID = 1L;

    //@NotNull(message = "空间树id不能为空!")
    //@Size(max = 32, message = "空间树id最多32个字符！")
    @ApiModelProperty(value="空间树id",name="id",example = "1")
    private String id;

    //@NotNull(message = "项目id不能为空!")
    //@Size(max = 32, message = "项目id最多32个字符！")
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private String projectid;

    public ZonetreeDeleteIo() {
        super();
    }

    public ZonetreeDeleteIo(String id, String projectid) {
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
        return "ZonetreeDeleteIo{" +
                "id='" + id + '\'' +
                ", projectid='" + projectid + '\'' +
                '}';
    }
}
