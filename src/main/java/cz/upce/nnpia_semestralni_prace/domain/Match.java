package cz.upce.nnpia_semestralni_prace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "home_team_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Club homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Club awayTeam;

    @ManyToOne
    @JoinColumn(name = "league_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private League league;
}
