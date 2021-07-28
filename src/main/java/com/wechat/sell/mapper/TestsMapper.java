package com.wechat.sell.mapper;

import com.wechat.sell.bean.TestsBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author 13540
 * @version 1.0
 * @date 2021-03-07 22:20
 */
@Mapper
public interface TestsMapper {
    //根据id获取数据
    public TestsBean getBean(int id);
}
