package com.common.api.census.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorRequestResult<T> implements Serializable {



  private Integer code;

  private String msg;

  private  T data;




}
