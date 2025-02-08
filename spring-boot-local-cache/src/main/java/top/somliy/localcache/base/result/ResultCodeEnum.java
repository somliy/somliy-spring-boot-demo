package top.somliy.localcache.base.result;

/**
 * 类名： @ClassName ResultCodeEnum 返回值枚举
 * 创建人：@author zhao dong
 * 类描述：@Description: 返回值枚举
 * 创建时间: 2023/8/7 16:32
 */
public enum ResultCodeEnum {
    /**
     * 枚举code值
     */
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    BIZ_ERROR(1000, "通用业务异常"),
    FILE_OUT_MAX(9000, "文件超出最大限制"),
    FILE_FORMAT_ERROR(9001, "文件格式不正确"),
    PARAM_ERROR(9050, "参数错误"),
    JSON_FORMAT_ERROR(9051, "Json解析异常"),
    SQL_ERROR(9052, "Sql解析异常"),
    NETWORK_TIMEOUT(9510, "网络超时"),
    UNKNOWN_INTERFACE(9520, "未知的接口"),
    REQ_MODE_NOT_SUPPORTED(9530, "请求方式不支持"),
    SYS_ERROR(9999, "系统异常"),
    /**
     * 限流服务
     */
    CURRENT_LIMITING_ERROR(30001, "限流服务异常"),
    CURRENT_LIMITING_NUM_ERROR(30002, "服务已限流"),

    ;
    /**
     * 状态码
     */
    private final int code;

    /**
     * 状态信息
     */
    private final String msg;

    ResultCodeEnum(int code, String msg) {
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
