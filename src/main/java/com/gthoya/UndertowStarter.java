package com.gthoya;
import com.gthoya.configuration.WebApplicationInitializer;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainerInitializerInfo;
import org.springframework.web.SpringServletContainerInitializer;

import javax.servlet.ServletException;
import java.util.HashSet;
import java.util.Set;

public class UndertowStarter {
    // private static final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] ar) throws Exception {
        UndertowStarter undertowStarter = new UndertowStarter();
        undertowStarter.getUndertowServer().start();
        // semaphore.acquire();
    }

    private Undertow getUndertowServer() throws ServletException {
        Set<Class<?>> handlers = new HashSet<Class<?>>();
        handlers.add(WebApplicationInitializer.class);
        ServletContainerInitializerInfo servletContainerInitializerInfo = new ServletContainerInitializerInfo(SpringServletContainerInitializer.class, handlers);

        DeploymentInfo servletBuilder = Servlets.deployment()
                .addServletContainerInitializer(servletContainerInitializerInfo)
                .setClassLoader(UndertowStarter.class.getClassLoader())
                .setContextPath("/")
                .setDeploymentName("spring-application");

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();

        PathHandler path = Handlers.path(Handlers.redirect(servletBuilder.getContextPath()))
                .addPrefixPath(servletBuilder.getContextPath(), manager.start());

        return Undertow.builder()
                .addHttpListener(System.getProperty("server.port") == null ? 8080 : Integer.parseInt(System.getProperty("server.port")), "localhost")
                .setHandler(path).build();
    }
}
