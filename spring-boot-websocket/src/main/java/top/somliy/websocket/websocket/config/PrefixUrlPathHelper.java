//package top.somliy.websocket.websocket.config;
//
//import org.springframework.web.util.UrlPathHelper;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 类名： @ClassName PrefixUrlPathHelper url匹配设置
// * 创建人：@author zhao dong
// * 类描述：@Description: url匹配设置
// * 创建时间: 2023/10/9 09:45
// */
//public class PrefixUrlPathHelper extends UrlPathHelper {
//
//    private final String prefix;
//
//    public PrefixUrlPathHelper(String prefix) {
//        this.prefix = prefix;
//    }
//
//    @Override
//    public String resolveAndCacheLookupPath(HttpServletRequest request) {
//        //获得原本的Path
//        String path = super.resolveAndCacheLookupPath(request);
//        //如果是指定前缀就返回对应的通配路径
//        if (path.startsWith(prefix)) {
//            return prefix + "/**";
//        }
//        return path;
//    }
//}