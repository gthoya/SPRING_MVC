package com.gthoya;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class JettyStarter {
    public static void main(String[] args) throws Exception {
        JettyStarter jettyStarter = new JettyStarter();

        Server server = new Server(System.getProperty("server.port") == null ? 8080 : Integer.parseInt(System.getProperty("server.port")));
        server.setHandler(jettyStarter.getServletContextHandler());
        server.start();
        server.join();
    }

    private ServletContextHandler getServletContextHandler() throws Exception {
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setResourceBase("webapp");
        servletContextHandler.setContextPath("/");

        WebApplicationContext webApplicationContext = getWebApplicationContext();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletHolder springServletHolder = new ServletHolder("spring-application", dispatcherServlet);
        servletContextHandler.addServlet(springServletHolder, "/");
        servletContextHandler.addEventListener(new ContextLoaderListener(webApplicationContext));

        return servletContextHandler;
    }

    private WebApplicationContext getWebApplicationContext() {
        AnnotationConfigWebApplicationContext webAppInitializer = new AnnotationConfigWebApplicationContext();
        webAppInitializer.setConfigLocation("com.gthoya.configuration");

        return webAppInitializer;
    }
}
