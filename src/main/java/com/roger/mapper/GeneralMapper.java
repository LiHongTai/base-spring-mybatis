package com.roger.mapper;

import java.util.List;
import java.util.Map;

public interface GeneralMapper {
    /**
     * 根据主键查询单个对象
     * @param param
     * @return
     */
    Map<String,Object> selectByPrimaryKey(Map<String,Object> param);

    /**
     * 根据所传递的参数查询对象集合
     * @param param
     * @return
     */
    List<Map<String,Object>> selectAdvanced(Map<String,Object> param);


    int insert(Map<String,Object> param);
}
