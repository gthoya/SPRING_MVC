package com.gthoya.configuration;

import com.gthoya.constant.CommonConstant;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApplicationContextConfiguration.class, DataSourceConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { AspectConfiguration.class, ServletContextConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter(CommonConstant.DEFAULT_CHARSET, true, true);
        MultipartFilter multipartFilter = new MultipartFilter();

        return new Filter[] { characterEncodingFilter, multipartFilter };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfig = new MultipartConfigElement("/Users/kakao/workspace/file-storage", 10485760, 104857600, 1048576);

        registration.setMultipartConfig(multipartConfig);
    }
}
