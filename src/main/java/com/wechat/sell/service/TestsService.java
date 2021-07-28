package com.wechat.sell.service;

import com.wechat.sell.bean.TestsBean;
import com.wechat.sell.mapper.TestsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestsService {
    @Autowired
    TestsMapper testsMapper;

    public TestsBean getBean(int id){
        return testsMapper.getBean(id);
    }
}
