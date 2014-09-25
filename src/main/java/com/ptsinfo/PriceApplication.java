package com.ptsinfo;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.ServerConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class PriceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PriceApplication.class, args);
	}

	@Bean
	public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
		factory.addServerCustomizers(server -> {
			HttpConfiguration httpConfig = new HttpConfiguration();
			httpConfig.setSendServerVersion(false);
			httpConfig.setSendDateHeader(false);
			ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
			InetSocketAddress address = new InetSocketAddress(factory.getAddress(), factory.getPort());
			connector.setHost(address.getHostName());
			connector.setPort(address.getPort());
			server.setConnectors(new Connector[]{connector});
		});
		return factory;
	}
}