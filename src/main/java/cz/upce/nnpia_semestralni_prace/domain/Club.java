package cz.upce.nnpia_semestralni_prace.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.upce.nnpia_semestralni_prace.dto.ClubDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String nickName;

    @Column
    private LocalDate foundationDate;

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
    @JsonBackReference
    private List<Match> homeMatches = new ArrayList<>();

    @OneToMany(mappedBy = "awayTeam")
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private List<Match> awayMatches = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "club_leagues",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id")
    )
    @ToString.Exclude
    @JsonBackReference
    private List<League> leagues = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private List<Player> clubPlayers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "country_id")
    @ToString.Exclude
    @JsonManagedReference
    private Country clubCountry;

    public Club(String name, String nickName, LocalDate foundationDate, String coachName, String location, String imgPath, String description, Country clubCountry, List<League> leagues) {
        this.name = name;
        this.nickName = nickName;
        this.foundationDate = foundationDate;
        this.coachName = coachName;
        this.location = location;
        this.imgPath = imgPath;
        this.description = description;
        this.clubCountry = clubCountry;
        this.leagues = leagues;
    }

    public ClubDto toDto() {
        return new ClubDto(
                getId(),
                getName(),
                getNickName(),
                getFoundationDate(),
                getCoachName(),
                getLocation(),
                getImgPath(),
                getDescription(),
                getClubCountry()
        );
    }
}
