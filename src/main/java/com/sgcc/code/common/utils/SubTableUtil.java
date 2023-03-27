package com.sgcc.code.common.utils;

public class SubTableUtil {

    private static  final  Long MAX_RECORD_NUM_FOR_TABLE=500000L;
    /**
     * 获取按月拆分表的索引值 2019 之前的放入 0索引的表中
     * @param yearMonth:格式： 年-月（2019-03）
     *  开始时间 2019-01开始
     * @return
     */
    public  final static int getIndexByYearMonth(String yearMonth){
        String sts[]=yearMonth.split("-");
        int year=Integer.parseInt(sts[0]);
        if(year<2019){
            return 0;
        }
        int month=Integer.parseInt(sts[1]);
        return 12*(year-2019)+month;
    }

    /**
     * 根据Id获取索引 默认时 50万
     * @param value 当前分表的值
     * @return
     */
    public final static int getIndexByValue(Long value){
        return getIndexByValue(value,MAX_RECORD_NUM_FOR_TABLE);
    }

    /**
     * 根据Id获取索引
     * @param value
     * @param maxRecordNumForTable 每张表的最大记录数
     * @return
     */
    public final static int getIndexByValue(Long value,Long maxRecordNumForTable){
        if(value==0L){
            return 1;
        }
        int tableIndex=Long.valueOf((value/maxRecordNumForTable)).intValue();
        if(Long.valueOf((value%maxRecordNumForTable))>0L){
            tableIndex++;
        }
        return tableIndex;
    }
}
