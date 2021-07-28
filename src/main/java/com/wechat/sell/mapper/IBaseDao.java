package com.wechat.sell.mapper;

//import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface IBaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
