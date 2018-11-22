package com.xzh.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 向振华
 * @date 2018/11/22 11:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    //名称
    @ApiModelProperty(value = "名称")
    private String name;

    //性别
    @ApiModelProperty(value = "性别")
    private String sex;

    //年龄
    @ApiModelProperty(value = "年龄")
    private Integer age;
}
