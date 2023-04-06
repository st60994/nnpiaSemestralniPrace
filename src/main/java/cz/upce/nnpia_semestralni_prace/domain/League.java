package cz.upce.nnpia_semestralni_prace.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private LocalDateTime foundationDate;

    @Column
    private String imgPath;

    @OneToMany(mappedBy = "league")
    @EqualsAndHashCode.Exclude
    private List<Match> matches = Collections.emptyList();

    @ManyToMany(mappedBy = "leagues")
    @EqualsAndHashCode.Exclude
    private List<Club> clubs = Collections.emptyList();

}
