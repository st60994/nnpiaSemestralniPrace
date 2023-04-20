package cz.upce.nnpia_semestralni_prace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDto {
    private Long id;
    private String name;
    private LocalDateTime foundationDate;
    private String imgPath;
}
