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
public class Player {

    public static enum Position {goalkeeper, defender, midfielder, attacker}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime dateOfBirth;

    @Column
    private Integer height;

    @Column
    private Double weight;

    @Column
    private String photoPath;

    @Enumerated(EnumType.STRING)
    Position position;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Club club;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Country playerCountry;

}
