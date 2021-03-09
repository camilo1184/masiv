package co.com.masiv.delegate;

import co.com.masiv.dto.BetDto;
import org.springframework.http.ResponseEntity;

public interface IBetDelegate {

  ResponseEntity<String> createBet(BetDto betDto);

}
