package com.wechat.sell.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestsBean {
    private Integer testId;
    private String testName;
    private Date testDate;
}

