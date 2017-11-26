package cn.pingjinsite.framework.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    /**
     * @Title: getParamAsMap
     * @Description: 获取页面请求参数对
     * @param request
     * @return Map
     */
    public static Map getParamAsMap(HttpServletRequest request) {
        Map resultmap = new HashMap();
        Map map = request.getParameterMap();
        Iterator keyIterator = (Iterator) map.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = (String) keyIterator.next();
            String value = ((String[]) (map.get(key)))[0];
            resultmap.put(key, value);
        }
        return resultmap;
    }
}
