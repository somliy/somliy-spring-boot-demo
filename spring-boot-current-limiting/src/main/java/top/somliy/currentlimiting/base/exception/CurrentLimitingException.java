package top.somliy.currentlimiting.base.exception;

import top.somliy.currentlimiting.base.result.ResultCodeEnum;

/**
 * 类名： @ClassName CurrentLimitingException 限流异常
 * 创建人：@author zhao dong
 * 类描述：@Description: 限流异常
 * 创建时间: 2023/8/7 16:55
 */
public class CurrentLimitingException extends RuntimeException {
    private static final long serialVersionUID = -8871267893816382735L;
    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 自定义错误码以及错误描述
     *
     * @param message 信息
     */
    public CurrentLimitingException(String message) {
        super(message);
        this.code = ResultCodeEnum.CURRENT_LIMITING_ERROR.getCode();
    }

    /**
     * 自定义错误码以及错误描述
     *
     * @param code    错误码
     * @param message 信息
     */
    public CurrentLimitingException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 默认异常
     *
     * @param resultCodeEnum 枚举
     */
    public CurrentLimitingException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMsg());
        this.code = resultCodeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
