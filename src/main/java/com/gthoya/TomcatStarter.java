package com.gthoya;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;

import java.io.File;

public class TomcatStarter {

    public static void main(String[] args) throws Exception {
        String webAppDirLocation = "src/main/webapp";

        Tomcat tomcat = new Tomcat();
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webAppDirLocation).getAbsolutePath());
        ((StandardJarScanner)ctx.getJarScanner()).setScanManifest(false);

        tomcat.setPort(System.getProperty("server.port") == null ? 8080 : Integer.parseInt(System.getProperty("server.port")));
        tomcat.start();
        tomcat.getServer().await();
    }
}
