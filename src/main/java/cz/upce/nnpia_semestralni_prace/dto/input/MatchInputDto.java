package cz.upce.nnpia_semestralni_prace.dto.input;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.League;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchInputDto {
    private LocalDateTime date;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Long homeTeamId;
    private Long awayTeamId;
    private Long leagueId;
}
