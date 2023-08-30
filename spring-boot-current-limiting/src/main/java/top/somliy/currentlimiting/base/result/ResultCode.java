package top.somliy.currentlimiting.base.result;

import lombok.Builder;

import java.io.Serializable;

/**
 * 类名： @ClassName ResultCode 返回值code
 * 创建人：@author zhao dong
 * 类描述：@Description: 返回值code
 * 创建时间: 2023/8/7 16:31
 */
@Builder
public class ResultCode implements Serializable {
    /**
     * 默认成功
     */
    public static final ResultCode SUCCESS = dispose(ResultCodeEnum.SUCCESS);
    /**
     * 默认失败
     */
    public static final ResultCode ERROR = dispose(ResultCodeEnum.ERROR);
    /**
     * 通用业务异常
     */
    public static final ResultCode BIZ_ERROR = dispose(ResultCodeEnum.BIZ_ERROR);
    /**
     * 文件超出最大限制
     */
    public static final ResultCode FILE_OUT_MAX = dispose(ResultCodeEnum.FILE_OUT_MAX);
    /**
     * 文件格式不正确
     */
    public static final ResultCode FILE_FORMAT_ERROR = dispose(ResultCodeEnum.FILE_FORMAT_ERROR);
    /**
     * 参数错误
     */
    public static final ResultCode PARAM_ERROR = dispose(ResultCodeEnum.PARAM_ERROR);
    /**
     * Json解析异常
     */
    public static final ResultCode JSON_FORMAT_ERROR = dispose(ResultCodeEnum.JSON_FORMAT_ERROR);
    /**
     * 网络超时
     */
    public static final ResultCode NETWORK_TIMEOUT = dispose(ResultCodeEnum.NETWORK_TIMEOUT);
    /**
     * 未知的接口
     */
    public static final ResultCode UNKNOWN_INTERFACE = dispose(ResultCodeEnum.UNKNOWN_INTERFACE);
    /**
     * 请求方式不支持
     */
    public static final ResultCode REQ_MODE_NOT_SUPPORTED = dispose(ResultCodeEnum.REQ_MODE_NOT_SUPPORTED);
    /**
     * 系统异常
     */
    public static final ResultCode SYS_ERROR = dispose(ResultCodeEnum.SYS_ERROR);
    private static final long serialVersionUID = -6269841958947880397L;
    /**
     * 状态码
     */
    private int code;
    /**
     * 状态信息
     */
    private String msg;

    public ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private static ResultCode dispose(ResultCodeEnum codeEnum) {
        return ResultCode.builder().code(codeEnum.getCode()).msg(codeEnum.getMsg()).build();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
