package cz.upce.nnpia_semestralni_prace.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.upce.nnpia_semestralni_prace.dto.LeagueDto;
import cz.upce.nnpia_semestralni_prace.dto.MatchDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    @Column
    private Integer homeTeamScore;

    @Column
    private Integer awayTeamScore;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    @ToString.Exclude
    @JsonManagedReference
    private Club homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    @ToString.Exclude
    @JsonManagedReference
    private Club awayTeam;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    @ToString.Exclude
    @JsonManagedReference
    private League league;

    public Match(LocalDateTime date, Integer homeTeamScore, Integer awayTeamScore, Club homeTeam, Club awayTeam, League league) {
        this.date = date;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.league = league;
    }

    public MatchDto toDto() {
        return new MatchDto(
                getId(),
                getDate(),
                getHomeTeamScore(),
                getAwayTeamScore(),
                getHomeTeam(),
                getAwayTeam(),
                getLeague()
        );
    }
}
