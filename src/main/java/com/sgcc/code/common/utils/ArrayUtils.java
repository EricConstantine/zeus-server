package com.sgcc.code.common.utils;


import java.util.*;

/**
 * Created by lcy on 2018/4/20.
 */
public class ArrayUtils {
    public static void main(String[] args) {
    }

    /**
     * 是否在数组中
     *
     * @param src
     * @param arr
     * @return
     */
    public static boolean isInList(String src, String[] arr) {
        for (String s : arr) {
            if (s.equalsIgnoreCase(src)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 把一个大数组拆成多个小数组
     */
    public static <T> List<List<T>> split(List<T> list, int max) {
        List<List<T>> result = new ArrayList<>();
        List<T> temp = new ArrayList<T>();
        for (int i = 0; i < list.size(); i++) {
            if (temp.size() >= max) {
                result.add(temp);
                temp = new ArrayList<T>();
            }
            temp.add(list.get(i));
            if (i == list.size() - 1) {
                result.add(temp);
            }
        }
        return result;
    }

    //数组拆分
    public static <T> boolean split(List<List<T>> allList, List<T> itemList, int itemListSize, int i, int iMax) {
        if (i == iMax) {
            allList.add(itemList);
            return true;
        }
        if (itemList.size() >= itemListSize) {
            allList.add(itemList);
            return true;
        }
        return false;
    }

    /**
     * 数据转换
     */
    public static String[] listToArr(List<String> list) {
        return list.toArray(new String[]{});
    }

    public static <T> T[] listToArrT(List<T> list) {
        return (T[]) list.toArray();
    }

    public static <T> List<T> arrToList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }

    public static <T> List<T> objectToList(T o) {
        List<T> list = new ArrayList<T>();
        list.add(o);
        return list;
    }

    /**
     * 排序
     */
    public static void sort(List list) {
        Collections.sort(list);
    }

    public static <T> List<T> mapToList(Map map) {
        return new ArrayList<T>(map.values());
    }

    //取交集，若source为空，返回target
    public static <T> List<T> retainAndCheck(List<T> source, List<T> target) {
        if (StringUtils.isNull(source)) {
            return target;
        }
        if (StringUtils.isNull(target)) {
            return null;
        }
        source.retainAll(target);
        if (StringUtils.isNull(source)) {
            return null;
        }
        return source;
    }

    //原list不在范围list中的集合
    public static <T> List<T> notContains(List<T> source, List<T> list) {
        if (StringUtils.isNull(source) || StringUtils.isNull(list)) {
            return source;
        }
        List<T> newSource = new ArrayList<T>();
        for (T item : source) {
            if (!list.contains(item)) {
                newSource.add(item);
            }
        }
        return newSource;
    }

    //校验数据权限使用
    public static <T> boolean checkPermission(List<T> source, T id) {
        return StringUtils.isNull(source) || source.contains(id);
    }

    public static <T> boolean checkNotPermission(List<T> source, T id) {
        return !checkPermission(source, id);
    }

    public static boolean isSizeGreater(Collection list, int i) {
        return StringUtils.isNotNull(list) && list.size() > i;
    }

//    public static boolean isSizeLess(Collection list, int i) {
//
//    }

}
