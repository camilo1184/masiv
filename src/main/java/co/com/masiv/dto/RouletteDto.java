package co.com.masiv.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouletteDto {

  private String code;
  private String tittle;
  private String description;
  private boolean moment;

}
