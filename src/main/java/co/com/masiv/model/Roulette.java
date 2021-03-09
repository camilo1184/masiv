package co.com.masiv.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Roulette")
@Data
@Builder
public class Roulette implements Serializable {

  @Id
  private String id;
  private String name;
  private String description;
  private boolean status;

}
