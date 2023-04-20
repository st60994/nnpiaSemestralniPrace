package cz.upce.nnpia_semestralni_prace.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubInputDto {
    @NotNull
    private String name;
    @NotNull
    private String nickName;
    @Past
    private LocalDate foundationDate;
    private String coachName;
    private String location;
    private String imgPath;
    @Size(max = 1024)
    private String description;
    private Long clubCountryId;
    private List<Long> leagueIds;
}
