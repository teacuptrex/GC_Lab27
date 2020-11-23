package co.grandcircus.gcLab27;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

	public List<Movie> findByTitleContaining(String keyword);	
	
	public List<Movie> findByCategoryIs(String category);
	
}
