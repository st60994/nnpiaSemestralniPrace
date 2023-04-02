package cz.upce.nnpia_semestralni_prace.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String flagPath;

    @OneToMany(mappedBy = "clubCountry")
    @EqualsAndHashCode.Exclude
    private List<Club> countryClubs = Collections.emptyList();

    @OneToMany(mappedBy = "playerCountry")
    @EqualsAndHashCode.Exclude
    private List<Player> countryPlayers = Collections.emptyList();
}
