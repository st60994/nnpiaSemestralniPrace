package cz.upce.nnpia_semestralni_prace.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.upce.nnpia_semestralni_prace.dto.PlayerDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Player {

    public static enum Position {goalkeeper, defender, midfielder, attacker}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer shirtNumber;

    @Column
    private String name;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private Integer height;

    @Column
    private Double weight;

    @Column
    private String photoPath;

    @Enumerated(EnumType.STRING)
    Position position;

    @ManyToOne
    @JoinColumn(name = "club_id")
    @ToString.Exclude
    @JsonIgnore
    private Club club;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @ToString.Exclude
    @JsonIgnore
    private Country playerCountry;

    public Player(String name, Integer shirtNumber, LocalDate dateOfBirth, Integer height, Double weight, String photoPath, Position position, Club club, Country country) {
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.photoPath = photoPath;
        this.club = club;
        this.position = position;
        this.playerCountry = country;
    }

    public PlayerDto toDto() {
        return new PlayerDto(
                getId(),
                getName(),
                getShirtNumber(),
                getDateOfBirth(),
                getHeight(),
                getWeight(),
                getPosition(),
                getPhotoPath(),
                getClub(),
                getPlayerCountry()
        );
    }

}
