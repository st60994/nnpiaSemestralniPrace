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
import java.util.Objects;

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
    @JsonManagedReference("club-homeMatches")
    private List<Match> homeMatches = new ArrayList<>();

    @OneToMany(mappedBy = "awayTeam")
    @EqualsAndHashCode.Exclude
    @JsonManagedReference("club-awayMatches")
    private List<Match> awayMatches = new ArrayList<>();

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
    @JsonManagedReference("club-players")
    private List<Player> clubPlayers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "country_id")
    @ToString.Exclude
    @JsonBackReference("country-clubs")
    private Country clubCountry;

    public Club(String name, String nickName, LocalDate foundationDate, String coachName, String location, String imgPath, String description, Country clubCountry,
                List<Match> awayMatches, List<Match> homeMatches, List<League> leagues) {
        this.name = name;
        this.nickName = nickName;
        this.foundationDate = foundationDate;
        this.coachName = coachName;
        this.location = location;
        this.imgPath = imgPath;
        this.description = description;
        this.clubCountry = clubCountry;
        this.leagues = leagues;
        this.awayMatches = awayMatches;
        this.homeMatches = homeMatches;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Club club = (Club) o;
        return Objects.equals(id, club.id) && Objects.equals(name, club.name) && Objects.equals(nickName, club.nickName) && Objects.equals(foundationDate, club.foundationDate) && Objects.equals(coachName, club.coachName) && Objects.equals(location, club.location) && Objects.equals(imgPath, club.imgPath) && Objects.equals(description, club.description) && Objects.equals(homeMatches, club.homeMatches) && Objects.equals(awayMatches, club.awayMatches) && Objects.equals(leagues, club.leagues) && Objects.equals(clubPlayers, club.clubPlayers) && Objects.equals(clubCountry, club.clubCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nickName, foundationDate, coachName, location, imgPath, description, homeMatches, awayMatches, leagues, clubPlayers, clubCountry);
    }
}
