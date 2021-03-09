package co.com.masiv.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouletteDto {

  private String code;
  private String tittle;
  private String description;
  private boolean status;
  private Date creationDate;
  private Date opening;
  private Date closing;

}
