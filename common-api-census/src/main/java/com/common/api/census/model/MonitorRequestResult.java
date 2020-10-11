package com.common.api.census.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : baochaohe
 * @version : v1.0
 * @description TODO  handler 返回结果模型
 * @date : 2020/10/11 23:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorRequestResult<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;


}
