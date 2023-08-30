package top.somliy.currentlimiting.util;

/**
 * 类名： @ClassName LuaUtil lua工具类
 * 创建人：@author zhao dong
 * 类描述：@Description: lua工具类
 * 创建时间: 2023/7/27 16:00
 */
public final class LuaUtil {
    private LuaUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取限流lua表达式
     *
     * @return 表达式
     */
    public static String buildLuaLimitScript() {
        String lua = "local c" +
                "\n c = redis.call('get',KEYS[1])" +
                // 调用不超过最大值，则直接返回
                "\n if c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\n return c;" +
                "\n end" +
                // 执行计算器自加
                "\n c = redis.call('incr',KEYS[1])" +
                "\n if tonumber(c) == 1 then" +
                // 从第一次调用开始限流，设置对应键值的过期
                "\n redis.call('expire',KEYS[1],ARGV[2])" +
                "\n end" +
                "\n return c;";
        return lua;
    }
}
