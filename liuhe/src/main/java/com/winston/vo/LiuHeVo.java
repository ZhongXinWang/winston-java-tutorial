package com.winston.vo;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection="liuhe")
public class LiuHeVo  implements Serializable {

    @Indexed
    private int year;
    private List<LiuHeData> data;
}
