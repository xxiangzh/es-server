package com.xzh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author 向振华
 * @date 2018/11/21 15:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//es注解，设置索引名称以及类型
@Document(indexName = "xzhes", type = "info")
public class EsEntity {

    //id(需要添加@Id注解,或会自动识别名称为id的字段为id,其余字段没有限制)
    @Id
    private Integer id;
    //名称
    private String name;
    //性别
    private String sex;
    //年龄
    private Integer age;

}