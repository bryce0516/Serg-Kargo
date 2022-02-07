package com.mobileAppWs.demo.test.model;

public class SingleResult<T> extends CommonResult {
  private T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "SingleResult{" +
            "data=" + data +
            '}';
  }
}
