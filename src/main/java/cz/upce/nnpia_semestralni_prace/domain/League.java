package cz.upce.nnpia_semestralni_prace.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.upce.nnpia_semestralni_prace.dto.LeagueDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate foundationDate;

    @Column
    private String imgPath;

    @OneToMany(mappedBy = "league")
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private List<Match> matches = Collections.emptyList();

    @ManyToMany(mappedBy = "leagues")
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private List<Club> clubs = Collections.emptyList();

    public League(String name, LocalDate foundationDate, String imgPath) {
        this.name = name;
        this.foundationDate = foundationDate;
        this.imgPath = imgPath;
    }

    public LeagueDto toDto() {
        return new LeagueDto(
                getId(),
                getName(),
                getFoundationDate(),
                getImgPath()
        );
    }
}
