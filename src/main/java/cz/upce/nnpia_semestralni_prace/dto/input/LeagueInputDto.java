package cz.upce.nnpia_semestralni_prace.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueInputDto {
    private String name;
    @Past
    private LocalDateTime foundationDate;
    private String imgPath;
}
