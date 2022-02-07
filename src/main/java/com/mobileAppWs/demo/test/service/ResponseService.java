package com.mobileAppWs.demo.test.service;

import com.mobileAppWs.demo.test.model.CommonResult;
import com.mobileAppWs.demo.test.model.ListResult;
import com.mobileAppWs.demo.test.model.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

  private enum CommonResponse {
    SUCCESS(0, "성공하였습니다.");

    int code;
    String msg;

  CommonResponse(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }
  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }


  }
  private void setSuccessResult(CommonResult result) {
    result.setSuccess(true);
    result.setCode(CommonResponse.SUCCESS.getCode());
    result.setMsg(CommonResponse.SUCCESS.getMsg());
  }

  public <T> ListResult<T> getListResult(List<T> list) {
    ListResult<T> result = new ListResult<>();
    setSuccessResult(result);
    result.setList(list);

    return result;
  }

  public <T> SingleResult<T> getSingleResult(T data) {
    SingleResult<T> result = new SingleResult<>();
    setSuccessResult(result);
    result.setData(data);
    return result;
  }
}
