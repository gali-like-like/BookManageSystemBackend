package com.sky.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "响应结果的封装")
public class Result {
	@Schema(description = "响应状态码,1表示成功,0表示失败")
    private Integer code;
	@Schema(description = "响应消息")
    private String msg;
	@Schema(description = "响应数据")
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {
    }
    
    private Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
	
	// 返回正确结果
	public static Result success(String msg,Object data) {
	    return new Result(1,msg,data);
	}
	// 返回错误结果
	public static Result fail(String msg) {
	    return new Result(0,msg,null);
	}
	
	@Override
	public String toString() {
	    return "Result{" +
	            "code=" + code +
	            ", msg='" + msg + '\'' +
	            ", data=" + data +
	            '}';
	}
}
