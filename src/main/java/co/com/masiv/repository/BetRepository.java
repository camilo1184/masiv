package co.com.masiv.repository;

import co.com.masiv.model.Bet;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends CrudRepository<Bet, String> {

  List<Bet> findByIdRoulette(String idRoulette);
}
