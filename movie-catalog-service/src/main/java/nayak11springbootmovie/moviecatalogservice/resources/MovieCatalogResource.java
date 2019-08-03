package nayak11springbootmovie.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import nayak11springbootmovie.moviecatalogservice.models.CatalogItem;
import nayak11springbootmovie.moviecatalogservice.models.Movie;
import nayak11springbootmovie.moviecatalogservice.models.Rating;
import nayak11springbootmovie.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		//get all rated movie ID's
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId,UserRating.class);
				
	
		return ratings.getUserRating().stream().map(rating -> {
			//For each movie id call info service and get details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId() , Movie.class);
			
			//Put them all together
			return new CatalogItem(movie.getName(), "Desc", rating.getRating() );
		}).collect(Collectors.toList());
	}
}



/*  sWEB CLIENT
	Movie movie = webClientBuilder.build()//Using a Builder pattern and give us the client
		.get()
		.uri("http://localhost:8082/movies/"+rating.getMovieId())
		.retrieve()
		.bodyToMono(Movie.class)
		.block();
*/