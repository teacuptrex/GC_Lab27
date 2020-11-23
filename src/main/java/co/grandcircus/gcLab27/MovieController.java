package co.grandcircus.gcLab27;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
	
	@Autowired
	private MovieRepository movieRepo;
	
	@GetMapping("/movielist")
	public List<Movie> getMovieList(){
		
		List<Movie> movies = movieRepo.findAll();
		return movies;
		
	}
	
	@GetMapping("/bytitle/{keyword}")
	public List<Movie> getMoviesByTitle(@PathVariable String keyword) {
		
		List<Movie> movies = movieRepo.findByTitleContaining(keyword);
		return movies;
		
	}
	
	@GetMapping("/bycategory/{category}")
	public List<Movie> getMoviesByCategory(@PathVariable String category) {
		
		List<Movie> movies = movieRepo.findByCategoryIs(category);
		return movies;
	}
	
	@GetMapping("/moviedetail/{id}")
	public Movie getMovieById(@PathVariable Long id) {
		Movie movie = movieRepo.findById(id).orElse(null);
		return movie;
	}
	
	@GetMapping("/randommovie")
	public Movie getRandomMovie() {
		List<Movie> movies = movieRepo.findAll();
		int x = 1 + (int) (Math.random() * movies.size());
		return movieRepo.findById((long) x).orElse(null);
		
	}
	
	@GetMapping("/randommovie/bycategory/{category}")
	public Movie getRandomMovie(@PathVariable String category) {
		List<Movie> movies = movieRepo.findByCategoryIs(category);
		int x = 1 + (int) (Math.random() * movies.size());
		
		
		return movies.get(x);
		
	}
	
	@GetMapping("/randommovie/{r}")
	public List<Movie> getRandomMovies(@PathVariable int r) {
		List<Movie> movies = movieRepo.findAll();
		List<Movie> results = new ArrayList<>();
		
		
		for(int i = 0; i < r; i++) {
			int x = 1 + (int) (Math.random() * (movies.size()-i));
			results.add(movies.get(x));
			
		}
		
		return results;
		
	}
	
	@GetMapping("/categories")
	public List<String> getCategoryList(){
		
		List<Movie> movies = movieRepo.findAll();
		List<String> results = new ArrayList<>();
		
		for(Movie movie : movies) {
			results.add(movie.getCategory());
		}
		
		return results;
		
	}
	

}
