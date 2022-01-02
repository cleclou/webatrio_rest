package com.webatriorest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = { "com.webatriorest", "com.webatrio" })
@Configuration
@EnableAutoConfiguration
public class SpringBootApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(SpringBootApp.class);
	private static final String HTTP_DEFAULT_PORT = "8080";
	
	private static void logApplicationStartup(final Environment env) throws UnknownHostException {
		String protocol = "http"; //$NON-NLS-1$
		if (env.getProperty("server.ssl.key-store") != null) { //$NON-NLS-1$
			protocol = "https"; //$NON-NLS-1$
		}
		final String serverPort = Optional.ofNullable(env.getProperty("server.port")).orElse(HTTP_DEFAULT_PORT); //$NON-NLS-1$
		String contextPath = env.getProperty("server.servlet.context-path"); //$NON-NLS-1$
		if (StringUtils.isEmpty(contextPath)) {
			contextPath = "/"; //$NON-NLS-1$
		}
		final String hostAddress = InetAddress.getLocalHost().getHostAddress();
		LOG.info("\n----------------------------------------------------------\n\t" // //$NON-NLS-1$
				+ "Application '{} ({})' is running!\n\tAccess URLs:\n\t" // //$NON-NLS-1$
				+ "Local: \t\t{}://localhost:{}{}\n\t" // //$NON-NLS-1$
				+ "External: \t{}://{}:{}{}\n\t" // //$NON-NLS-1$
				+ "Profile(s): \t{}\n----------------------------------------------------------", // //$NON-NLS-1$
				env.getProperty("spring.application.name"), env.getProperty("application.version"), protocol, //$NON-NLS-1$ //$NON-NLS-2$
				serverPort, contextPath, protocol, hostAddress,
				serverPort, contextPath, env.getActiveProfiles());
	}
	
	public static void main(String[] args) throws UnknownHostException{
		final Environment env = SpringApplication.run(SpringBootApp.class, args).getEnvironment();
		logApplicationStartup(env);
	}
}
