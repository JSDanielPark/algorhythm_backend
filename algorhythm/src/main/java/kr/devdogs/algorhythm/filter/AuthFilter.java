package kr.devdogs.algorhythm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.devdogs.algorhythm.visiter.dto.VisitCount;
import kr.devdogs.algorhythm.visiter.service.VisitService;
import kr.devdogs.algorhythm.visiter.service.VisitServiceImpl;
import kr.devdogs.algorhythm.member.dto.Member;
import kr.devdogs.algorhythm.utils.WebUtils;

/**
 * 
 * @author Daniel
 * 방문자수 카운팅을 위한 필터. 로직은 변경이 필요해보인다.
 */

@Configuration
public class AuthFilter {
	@Bean                                                                                         
	public FilterRegistrationBean filterRegistration(){                                       
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new Filter() {
			@Override
			public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
					throws IOException, ServletException {
				System.out.println("siobal");
				HttpServletRequest request = (HttpServletRequest) req;
				HttpServletResponse response = (HttpServletResponse) res;
				if(request.getSession().getAttribute(Member.SESSION_KEY_NO) == null) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
				} else {
					chain.doFilter(req, res);
				}
			}
			
			@Override public void init(FilterConfig arg0) throws ServletException {}
			@Override public void destroy() {}
		});
		registrationBean.setEnabled(true);
		registrationBean.addUrlPatterns("/**");
	    return registrationBean;                                                                  
	}                                
}
