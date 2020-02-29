package com.winston.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LiuHeAllDataVo  implements Serializable {

    /**
     * 年
     */
    private int year;

    /**
     * 所有数据
     */
    private List<Integer> datas;
}
