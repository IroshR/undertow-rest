package com.iroshnk.undertow;

import com.iroshnk.undertow.config.RestResourceConfig;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletException;

public class UndertowRestApplication {
    private static Undertow server;
    private static DeploymentManager deploymentManager;
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8070;

    public static void main(String[] args) throws ServletException {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    private static void start(final String host, final int port) throws ServletException {
        PathHandler path = Handlers.path();

        server = Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(path)
                .build();

        server.start();

        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(UndertowRestApplication.class.getClassLoader())
                .setContextPath("/")
                .setResourceManager(new ClassPathResourceManager(UndertowRestApplication.class.getClassLoader()))
                .addServlets(
                        Servlets.servlet("jerseyServlet", ServletContainer.class)
                                .setLoadOnStartup(1)
                                .addInitParam("javax.ws.rs.Application", RestResourceConfig.class.getName())
                                .addMapping("/api/*"))
                .setDeploymentName("Demo.war");

        deploymentManager = Servlets.defaultContainer().addDeployment(servletBuilder);
        deploymentManager.deploy();

        path.addPrefixPath("/", deploymentManager.start());

    }
}
