package com.common.api.census.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/****
 * handler 统计模型
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class MHCountModel implements Serializable {

  /***
   * 调用次数统计
   */
  private Long count = 0L;

  /***
   * 最大调用次数
   * -1 --> 不限制
   */
  private Long maxCount = -1L;

  /***
   * 自增一
   * @return
   */
  public boolean autoAddOne() {
    long temp = count.longValue() + 1;
    if (this.maxCount == -1) {
      this.count = temp;
      return true;
    }
    if (temp > this.maxCount) { //超出最大调用次数
      return false;
    }
    this.count = temp;
    return true;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    if (count < 0) {
      log.warn("count must > 0");
      return;
    }
    this.count = count;
  }

  public Long getMaxCount() {
    return maxCount;
  }

  public void setMaxCount(Long maxCount) {
    if (maxCount < 0) {
      log.warn("maxCount must > 0");
      return;
    }
    this.maxCount = maxCount;
  }

}
