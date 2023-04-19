package cz.upce.nnpia_semestralni_prace.dto;

import cz.upce.nnpia_semestralni_prace.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubDto {
    private Long id;
    private String name;
    private String nickName;
    private LocalDate foundationDate;
    private String coachName;
    private String location;
    private String imgPath;
    private String description;
    private Country clubCountry;
}
