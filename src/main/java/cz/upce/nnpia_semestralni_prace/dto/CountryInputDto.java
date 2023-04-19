package cz.upce.nnpia_semestralni_prace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryInputDto {
    @Size(max = 56)
    private String name;
    @Size(max = 3)
    private String abbreviation;
    private String flagPath;
}
