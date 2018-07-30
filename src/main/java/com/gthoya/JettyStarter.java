package com.gthoya;

import com.gthoya.configuration.ApplicationContextConfiguration;
import com.gthoya.configuration.AspectConfiguration;
import com.gthoya.configuration.DataSourceConfiguration;
import com.gthoya.configuration.ServletContextConfiguration;
import com.gthoya.configuration.WebApplicationInitializer;
import org.apache.jasper.servlet.JspServlet;
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
        servletContextHandler.setContextPath("/");
        servletContextHandler.setResourceBase("webapp");

        servletContextHandler.setClassLoader(
                Thread.currentThread().getContextClassLoader()
        );

        servletContextHandler.addServlet(new ServletHolder(new DispatcherServlet(getServletContext())), "/*");
        servletContextHandler.addEventListener(new ContextLoaderListener(getContextLoaderListenerContext()));

        ServletHolder servletHolder = servletContextHandler.addServlet(JspServlet.class, "*.jsp");
        servletHolder.setInitParameter("classpath", servletContextHandler.getClassPath());

        return servletContextHandler;
    }

    private WebApplicationContext getServletContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebApplicationInitializer.class);
        context.register(ServletContextConfiguration.class);

        return context;
    }

    private WebApplicationContext getContextLoaderListenerContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationContextConfiguration.class);
        context.register(DataSourceConfiguration.class);
        context.register(AspectConfiguration.class);

        return context;
    }
}
