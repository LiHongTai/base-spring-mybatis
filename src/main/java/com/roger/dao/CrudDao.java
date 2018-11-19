package com.roger.dao;

import com.roger.db.GeneralQueryParam;

import java.util.List;
import java.util.Map;

public interface CrudDao {

    Map<String, Object> selectByPrimaryKey(Map<String, Object> paramMap);

    <T> List<T> selectAdvanced(Class<T> clazz, GeneralQueryParam generalQueryParam);

    <T> int insert(T target);
}
