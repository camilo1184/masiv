package co.com.masiv.repository;

import co.com.masiv.model.Roulette;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouletteRepository extends CrudRepository<Roulette, String> {}