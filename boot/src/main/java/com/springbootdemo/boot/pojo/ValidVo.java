package com.springbootdemo.boot.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wangwenjie
 * @date 2019-12-13
 */
@Data
public class ValidVo {
    @NotBlank(message = "内容不能为空")
    private String message;

}
