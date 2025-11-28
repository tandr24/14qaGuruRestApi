package DTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UsersSummaryDTO {
    private Integer page;
    private Integer per_page;
    private Integer total;
}
