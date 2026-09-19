package com.power.travel.core;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    // 默认的成功消息
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    //生成一个默认的成功结果，不包含数据
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    //生成一个包含数据的成功结果
    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    //生成一个包含自定义消息的成功结果
    public static Result genSuccessMsgResult(String msg) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(msg);
    }

    //生成一个失败结果
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    //生成一个数据错误结果
    public static Result genResult(String message) {
        return new Result()
                .setCode(ResultCode.DATA_ERROR)
                .setMessage(message);
    }

}
