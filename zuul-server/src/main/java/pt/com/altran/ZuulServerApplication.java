package pt.com.altran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}
}