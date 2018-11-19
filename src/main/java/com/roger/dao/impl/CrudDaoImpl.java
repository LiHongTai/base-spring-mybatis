package com.roger.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roger.constant.QueryParamConstant;
import com.roger.dao.CrudDao;
import com.roger.db.GeneralQueryParam;
import com.roger.db.SqlColumn;
import com.roger.db.SqlColumnFactory;
import com.roger.mapper.GeneralMapper;
import com.roger.utils.GeneralMapperReflectUtil;
import com.roger.utils.PersistentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrudDaoImpl implements CrudDao {

    @Autowired
    private GeneralMapper generalMapper;

    @Override
    public Map<String, Object> selectByPrimaryKey(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = generalMapper.selectByPrimaryKey(paramMap);
        if (CollectionUtils.isEmpty(resultMap)) {
            return null;
        }
        return resultMap;
    }

    @Override
    public <T> List<T> selectAdvanced(Class<T> clazz, GeneralQueryParam generalQueryParam) {
        List<T> resultList = new ArrayList<>();
        List<String> columnNameList = GeneralMapperReflectUtil.getAllColumnNames(clazz);
        generalQueryParam.setQueryCoulmn(columnNameList);

        List<Map<String, Object>> resultMapList = this.selectAdvancedByColumn(clazz, generalQueryParam);
        for(Map<String,Object> resultMap : resultMapList){
            T target = GeneralMapperReflectUtil.parseToJavaBean(resultMap,clazz);
            if(target != null) {
                resultList.add(target);
            }
        }

        return resultList;
    }

    private <T> List<Map<String, Object>> selectAdvancedByColumn(Class<T> clazz, GeneralQueryParam generalQueryParam) {

        String tableName = PersistentUtil.getTableName(clazz);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(QueryParamConstant.TABLE_NAME, tableName);
        paramMap.put(QueryParamConstant.QUERY_COLUMN_NAME_LIST, generalQueryParam.getQueryCoulmn());
        paramMap.put(QueryParamConstant.WHERE_CONDITION_EXP,generalQueryParam.getConditionExp());
        paramMap.put(QueryParamConstant.WHERE_CONDITION_PARAM,generalQueryParam.getConditionParam());
        paramMap.put(QueryParamConstant.ORDER_BY_EXP,generalQueryParam.getOrderExp());

        if(generalQueryParam.isEnablePage()){
            Map<String,Object> pageMap = new HashMap<>();
            int startRowNo = (generalQueryParam.getPageNo() -1) * generalQueryParam.getPageSize();
            pageMap.put(QueryParamConstant.START_ROW_NO,startRowNo);
            pageMap.put(QueryParamConstant.PAGE_SIZE,generalQueryParam.getPageSize());
            paramMap.put(QueryParamConstant.PAGE,pageMap);
        }

        return generalMapper.selectAdvanced(paramMap);
    }

    @Override
    public <T> int insert(T target) {
        if(target == null){
            return 0;
        }
        String tableName = PersistentUtil.getTableName(target.getClass());
        Map<String, Object> paramMap = new HashMap<>();
        List<SqlColumn> sqlColumnList = new ArrayList<>();
        paramMap.put(QueryParamConstant.TABLE_NAME,tableName);
        paramMap.put(QueryParamConstant.INSERT_SQL_COLUMN_LIST,sqlColumnList);

        List<Field> fieldList = PersistentUtil.getPersistentFields(target.getClass());
        for(Field field : fieldList){
            SqlColumn sqlColumn = SqlColumnFactory.createSqlColumn(target,field);
            sqlColumnList.add(sqlColumn);
        }

        return generalMapper.insert(paramMap);
    }
}
