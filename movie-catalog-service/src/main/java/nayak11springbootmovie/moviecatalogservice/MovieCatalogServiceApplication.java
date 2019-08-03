package nayak11springbootmovie.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/*
 * Service Discovery
 * 
 * - client-side service discovery
 *  	-> All the services registers with the discovery server  
 * 		-> Client asks Discovery server like what service it need and discovery server in return returns respective service
 * 		-> Client makes request to that service
 * Disadvantage : Extra hop!
 * 
 * - server-side service discovery
 * 		-> All the services registers with the server
 * 		-> client tells server to pass the message to the respective server 
 * Advantage : No extra hop
 * 
 * Model spring cloud uses is, client side service discovery.
 * 
 * Technology - Eureka
 * 
 * Netflix created and open sourced these libraries like :
 * - Eureka 
 * - Ribbon
 * - Hysterix
 * - Zuul
 * 
 * 
 * */

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

	
/*
 * @LoadBalanced does service discovery in a load balanced way. We are basically telling REST template, don't go to the service directly,
 *  the URL given by us is not the actual URL, it is basically a hint what service you need to discover. 
 * */
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
