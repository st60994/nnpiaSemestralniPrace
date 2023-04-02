package cz.upce.nnpia_semestralni_prace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String nickName;

    @Column
    private LocalDateTime foundationDate;

    @Column
    private String coachName;

    @Column
    private String location;

    @Column
    private String imgPath;

    @Column
    private String description;

    @OneToMany(mappedBy = "homeTeam")
    @EqualsAndHashCode.Exclude
    private List<Match> homeMatches = Collections.emptyList();

    @OneToMany(mappedBy = "awayTeam")
    @EqualsAndHashCode.Exclude
    private List<Match> awayMatches = Collections.emptyList();

    @ManyToMany
    @JoinTable(
            name = "club_leagues",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<League> leagues = Collections.emptyList();

    @OneToMany(mappedBy = "club")
    @EqualsAndHashCode.Exclude
    private List<Player> clubPlayers = Collections.emptyList();

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Country clubCountry;
}
