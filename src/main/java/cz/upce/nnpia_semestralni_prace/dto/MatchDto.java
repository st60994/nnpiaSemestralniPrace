package cz.upce.nnpia_semestralni_prace.dto;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.League;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
    private Long id;
    private LocalDateTime date;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Club homeTeam;
    private Club awayTeam;
    private League league;

}
