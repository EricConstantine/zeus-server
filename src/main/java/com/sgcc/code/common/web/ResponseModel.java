package com.sgcc.code.common.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgcc.code.common.utils.BeanUtil;
import com.sgcc.code.common.utils.UapPage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 统一响应处理类
 *
 * @author Administrator
 */
public final class ResponseModel<T> implements Serializable {

    private int code = 200;

    private String msg = "操作成功！";

    private T data;

    private ResponseHeader header;

    public ResponseModel() {
    }

    public ResponseModel(T data) {
        this.data = data;
    }

    public ResponseModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseModel(String msg, T data) {
        this.data = data;
        this.msg = msg;
    }

    public ResponseModel(Integer code,String msg, T data) {
        this.code=code;
        this.data = data;
        this.msg = msg;
    }

    /****************************添加返回报文头*****************************/
    public ResponseModel(RequestHeader requestHeader) {
        this.header = getHeader(requestHeader);
    }

    public ResponseModel(T data,RequestHeader requestHeader) {
        this.data = data;
        this.header = getHeader(requestHeader);
    }

    public ResponseModel(Integer code, String msg,RequestHeader requestHeader) {
        this.code = code;
        this.msg = msg;
        this.header = getHeader(requestHeader);
    }

    public ResponseModel(String msg, T data,RequestHeader requestHeader) {
        this.data = data;
        this.msg = msg;
        this.header = getHeader(requestHeader);
    }

    public ResponseModel(Integer code,String msg, T data,RequestHeader requestHeader) {
        this.code=code;
        this.data = data;
        this.msg = msg;
        this.header = getHeader(requestHeader);
    }
    /****************************添加返回报文头*****************************/


    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel<T>(data);
    }

    public static <T> ResponseModel<T> success(String msg, T data) {
        return new ResponseModel<T>(msg, data);
    }


    public static ResponseModel<String> success() {
        return new ResponseModel<String>();
    }


    public static <T> ResponseModel<T> failure(String msg) {
        return new ResponseModel(500, msg);
    }

    /****************************添加返回报文头*****************************/
    public static <T> ResponseModel<T> success(T data,RequestHeader requestHeader) {
        return new ResponseModel<T>(data,requestHeader);
    }

    public static <T> ResponseModel<T> success(String msg, T data,RequestHeader requestHeader) {
        return new ResponseModel<T>(msg, data,requestHeader);
    }


    public static ResponseModel<String> success(RequestHeader requestHeader) {
        return new ResponseModel<String>(requestHeader);
    }


    public static <T> ResponseModel<T> failure(String msg,RequestHeader requestHeader) {
        return new ResponseModel(500, msg,requestHeader);
    }
    /****************************添加返回报文头*****************************/

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public ResponseHeader getHeader() {
        return header;
    }

    public ResponseHeader getHeader(RequestHeader requestHeader){
        header = new ResponseHeader();
        if(requestHeader!=null) {
            header.setReqID(requestHeader.getReqID());
            header.setRespCode(String.valueOf(this.code));
            header.setRespDesc(this.msg);
            header.setRespID(UUID.randomUUID().toString());
            header.setRespTime(LocalDate.now());
        }else{
            header.setReqID("0");
            header.setRespCode("0");
            header.setRespDesc("放行请求");
            header.setRespID(UUID.randomUUID().toString());
            header.setRespTime(LocalDate.now());
        }
        return header;
    }

    /**
     * 获取响应的分页对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public final <T> UapPage<T> getPageData(Class<T> clazz) {
        if (data == null || data.equals("")) {
            return new UapPage<T>();
        }
        String jsonStr = JSONObject.toJSONString(getData());
        if (jsonStr == null || jsonStr.equals("")) {
            return new UapPage<T>();
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("records");
        List<T> list = new ArrayList<T>();
        if (jsonArray != null && jsonArray.size() > 0) {
            String arraystr = jsonArray.toJSONString();
            list = JSON.parseArray(arraystr, clazz);
        }
        jsonObject.remove("records");
        jsonStr = jsonObject.toJSONString();
        UapPage<T> page = JSON.parseObject(jsonStr, UapPage.class);
        page.setRecords(list);
        return page;
    }

    public final <V> ResponseModel<UapPage<V>> convertPage(Class<V> clazz) {
        IPage<T> ipage = (IPage<T>) this.data;
        UapPage<V> vp = new UapPage<V>();
        vp.setCurrent(Long.valueOf(ipage.getCurrent()).intValue());
        vp.setPages(Long.valueOf(ipage.getPages()).intValue());
        vp.setSize(Long.valueOf(ipage.getSize()).intValue());
        vp.setTotal(Long.valueOf(ipage.getTotal()).intValue());
        List<V> list = BeanUtil.convert(ipage.getRecords(), clazz);
        vp.setRecords(list);
        return success(vp);
    }

    public final <V> ResponseModel<V> convert(Class<V> clazz) {
        V v = BeanUtil.convert(this.data, clazz);
        return success(v);
    }

    @Override
    public String toString() {
        return "[code:" + code + ",msg:" + msg + ",data:" + data + "]";
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }
}
