package nayak11springbootmovie.moviecatalogservice.models;

public class Rating {
	
	private String movieId;
	private int rating;
	
	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Rating(String movieId, int rating) {
		super();
		this.movieId = movieId;
		this.rating = rating;
	}


	public String getMovieId() {
		return movieId;
	}


	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

}
