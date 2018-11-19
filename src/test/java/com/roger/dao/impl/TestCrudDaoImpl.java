package com.roger.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.roger.SpringBaseTestSuit;
import com.roger.constant.QueryParamConstant;
import com.roger.dao.CrudDao;
import com.roger.db.GeneralQueryParam;
import com.roger.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCrudDaoImpl extends SpringBaseTestSuit {

    @Autowired(required = false)
    private CrudDao crudDao;

    @Test
    public void testSelectByPrimaryKey(){
        Map<String,Object> paramMap = new HashMap<>();
        //主键列
        paramMap.put(QueryParamConstant.PRIMARY_KEY,"id");
        //主键值
        paramMap.put(QueryParamConstant.PRIMARY_VALUE,79);
        //表名
        paramMap.put(QueryParamConstant.TABLE_NAME,"user");
        //查询列名
        List<String> columnNameList = new ArrayList<>();
        columnNameList.add("id");
        columnNameList.add("user_name userName");//起别名
        columnNameList.add("age");
        columnNameList.add("phone");
        columnNameList.add("created_time createdTime");
        columnNameList.add("updated_time updatedTime");
        paramMap.put(QueryParamConstant.QUERY_COLUMN_NAME_LIST,columnNameList);

        Map<String, Object> resultMap = crudDao.selectByPrimaryKey(paramMap);
        System.out.println(JSON.parseObject(JSON.toJSONString(resultMap),User.class));
    }

    @Test
    public void testSelectAdvanced(){
        GeneralQueryParam generalQueryParam = new GeneralQueryParam();

        //排序字段 表的列名
        String orderExp = "updated_time desc";
        generalQueryParam.setOrderExp(orderExp);
        //启动分页
        generalQueryParam.setEnablePage(true);
        generalQueryParam.setPageSize(2);
        generalQueryParam.setPageNo(1);

        List<User> users = crudDao.selectAdvanced(User.class, generalQueryParam);
        System.out.println(users);

        generalQueryParam.setPageNo(2);
        users = crudDao.selectAdvanced(User.class, generalQueryParam);
        System.out.println(users);
    }
}
