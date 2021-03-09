package co.com.masiv.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Bet")
@Data
@Builder
public class Bet {

  @Id
  private String id;
  private int number;
  private String color;
  private double amount;

}
