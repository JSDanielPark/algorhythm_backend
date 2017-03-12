package kr.devdogs.algorhythm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class AlgorhythmConfiguration {
	private static final String FORWARD_PAGE = "forward:/html/index.html";
	
	/**
	 *  ROOT경로로 접속 시 Web Application 페이지로 포워딩.
	 *  또한 지정된 URL로 접속시 react로의 연결을 위한 서버사이드 라우팅을 위함 
	 */
    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
            		registry.addViewController("/").setViewName(FORWARD_PAGE);
            		registry.addViewController("/loginForm").setViewName(FORWARD_PAGE);
            		registry.addViewController("/join").setViewName(FORWARD_PAGE);
            }
        };
    }

}