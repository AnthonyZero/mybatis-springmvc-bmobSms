package cn.pingjinsite.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CrossDomainAuthorizationFilter
 * @Description: 跨域请求的过滤器
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月10日 下午5:25:22
 */
public class CrossDomainAuthorizationFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletResponse response = (HttpServletResponse) arg1;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("P3P", "CP=CAO PSA OUR");
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
