package cz.upce.nnpia_semestralni_prace.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.upce.nnpia_semestralni_prace.dto.CountryDto;
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
    private String abbreviation;

    @Column
    private String flagPath;

    @OneToMany(mappedBy = "clubCountry")
    @EqualsAndHashCode.Exclude
    @JsonManagedReference("country-clubs")
    private List<Club> countryClubs = Collections.emptyList();

    @OneToMany(mappedBy = "playerCountry")
    @EqualsAndHashCode.Exclude
    @JsonManagedReference("country-players")
    private List<Player> countryPlayers = Collections.emptyList();

    public Country(String name, String abbreviation, String flagPath) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.flagPath = flagPath;
    }

    public CountryDto toDto() {
        return new CountryDto(
                getId(),
                getName(),
                getAbbreviation(),
                getFlagPath()
        );
    }
}
