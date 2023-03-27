package com.sgcc.code.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgcc.code.common.web.RequestHeader;
import com.sgcc.code.common.web.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * bean 工具
 */
public class BeanUtil {
 private static  final Logger logger= LoggerFactory.getLogger(BeanUtil.class);

    public static  final  <M,V> V convert(M m,Class<V> clazz){
        try {
            if(m==null){
                return null;
            }
            V v;
            if (m instanceof Map) {
                v = JSON.parseObject(JSON.toJSONString(m, SerializerFeature.WriteMapNullValue),clazz);
            }else{
                v=clazz.newInstance();
                BeanUtils.copyProperties(m,v);//性能高，但容易报错一点
            }
            return v;
        }catch (Exception ex){
            logger.error("转换bean异常",ex);
        }
        return null;
    }

    public static final <M,V> List<V> convert(List<M> list,Class<V> clazz){
        if(list==null){
            return null;
        }
        if(list.size()==0){
            return new ArrayList<V>();
        }
        List<V> vs=new ArrayList<V>();
        for(M m:list){
           V v=convert(m,clazz);
           if(v==null){
               continue;
           }
            vs.add(v);
        }
        return vs;
    }

    public static final <M,T> ResponseModel<T> convert(ResponseModel<M> responseModel, Class<T> clazz, RequestHeader requestHeader){
        if(responseModel==null){
            return null;
        }
        if(responseModel.getCode()!=0){
            return ResponseModel.failure(responseModel.getMsg());
        }
        M m = responseModel.getData();
        T t=convert(m,clazz);
        return new ResponseModel(responseModel.getCode(),responseModel.getMsg(),t,requestHeader);
    }


    public static final <M,T> ResponseModel<List<T>> convertComplicated(ResponseModel<List<M>> responseModel,Class<T> clazz, RequestHeader requestHeader){
        if(responseModel==null){
            return null;
        }
        if(responseModel.getCode()!=0){
            return ResponseModel.failure(responseModel.getMsg());
        }
        List<M> m = responseModel.getData();
        List<T> t=convert(m,clazz);
        return new ResponseModel(responseModel.getCode(),responseModel.getMsg(),t,requestHeader);
    }

//    public static final <M,T> ResponseModel<IPage<T>> convertComplicatedPage(ResponseModel<IPage<M>> responseModel, Class<T> clazz, RequestHeader requestHeader){
//        if(responseModel==null){
//            return null;
//        }
//        if(responseModel.getCode()!=0){
//            return ResponseModel.failure(responseModel.getMsg());
//        }
//        IPage<M> _page = responseModel.getData();
//        List<M> m = _page.getRecords();
//        List<T> t=convert(m,clazz);
//        IPage<T> page = new Page(_page.getCurrent(), _page.getSize(), _page.getTotal(), _page.isSearchCount());
//        page.setRecords(t);
//        return new ResponseModel(responseModel.getCode(),responseModel.getMsg(),page,requestHeader);
//    }

    //转换Mybatisplus的分页对象为自己的分页对象 并转换分页中的实体泛型
    public static final <V,T> UapPage<V> convertPage(IPage<T> ipage, Class<V> clazz) {
        UapPage<V> uapPage = new UapPage<V>();
        uapPage.setCurrent(Long.valueOf(ipage.getCurrent()).intValue());
        uapPage.setPages(Long.valueOf(ipage.getPages()).intValue());
        uapPage.setSize(Long.valueOf(ipage.getSize()).intValue());
        uapPage.setTotal(Long.valueOf(ipage.getTotal()).intValue());
        List<V> list = BeanUtil.convert(ipage.getRecords(), clazz);
        uapPage.setRecords(list);
        return uapPage;
    }

    //转换Mybatisplus的分页对象为自己的分页对象
    public static final <V> UapPage<V> convertPage(IPage<V> ipage) {
        UapPage<V> uapPage = new UapPage<V>();
        uapPage.setCurrent(Long.valueOf(ipage.getCurrent()).intValue());
        uapPage.setPages(Long.valueOf(ipage.getPages()).intValue());
        uapPage.setSize(Long.valueOf(ipage.getSize()).intValue());
        uapPage.setTotal(Long.valueOf(ipage.getTotal()).intValue());
        List<V> list = ipage.getRecords();
        uapPage.setRecords(list);
        return uapPage;
    }

    //转换自己的分页对象中的实体类泛型
    public static final <V,T> UapPage<V> convertPage(UapPage<T> uapPage, Class<V> clazz) {
        UapPage<V> uapPage_2 = new UapPage<V>();
        uapPage_2.setCurrent(Long.valueOf(uapPage.getCurrent()).intValue());
        uapPage_2.setPages(Long.valueOf(uapPage.getPages()).intValue());
        uapPage_2.setSize(Long.valueOf(uapPage.getSize()).intValue());
        uapPage_2.setTotal(Long.valueOf(uapPage.getTotal()).intValue());
        List<V> list = BeanUtil.convert(uapPage.getRecords(), clazz);
        uapPage_2.setRecords(list);
        return uapPage_2;
    }

}
