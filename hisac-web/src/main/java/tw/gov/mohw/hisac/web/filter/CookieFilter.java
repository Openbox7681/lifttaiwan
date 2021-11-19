package tw.gov.mohw.hisac.web.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet Filter implementation class httponly
 */
public class CookieFilter implements Filter {
    /**
     * @see Filter#destroy()
     */
    public void destroy() {

    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // pass the request along the filter chain 做其他的filter
        chain.doFilter(request, response);
        //建立httpservletresponse obj
        HttpServletResponse response2 = (HttpServletResponse) response;
        //建立httpservletrequest obj
        HttpServletRequest request2 = (HttpServletRequest) request;
        if (response2.containsHeader("SET-COOKIE"))
        {
            //取得Jsessionid的值
            if (request2 !=null && request2.getSession() != null) {
                String sessionid = request2.getSession().getId();
                //將其設定成httponly
                response2.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + ";HttpOnly");
            }
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}