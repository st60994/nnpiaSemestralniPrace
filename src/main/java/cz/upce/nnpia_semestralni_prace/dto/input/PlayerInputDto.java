package cz.upce.nnpia_semestralni_prace.dto.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInputDto {
    @NotNull
    private String name;
    @Positive
    private Integer shirtNumber;
    @Past
    private LocalDate dateOfBirth;
    @Positive
    private Integer height;
    @Positive
    private Double weight;
    private Player.Position position;
    private String photoPath;
    private Long clubId;
    private Long countryId;
}
