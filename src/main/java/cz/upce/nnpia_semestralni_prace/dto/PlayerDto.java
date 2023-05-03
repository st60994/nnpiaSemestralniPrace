package cz.upce.nnpia_semestralni_prace.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    private String name;
    private Integer shirtNumber;
    private LocalDate dateOfBirth;
    private Integer height;
    private Double weight;
    private Player.Position position;
    private String photoPath;
    private Club club;
    private Country country;
}
