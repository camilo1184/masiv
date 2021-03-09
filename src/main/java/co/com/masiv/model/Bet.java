package co.com.masiv.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Bet")
@Data
@Builder
public class Bet implements Serializable {

  @Id private String id;
  @Indexed
  private String idRoulette;
  private int number;
  private String color;
  private double amount;
  private Date creationDate;
  private double amountPaid;
  private boolean winner;
}
