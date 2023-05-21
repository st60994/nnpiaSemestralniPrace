package cz.upce.nnpia_semestralni_prace.dto;

import cz.upce.nnpia_semestralni_prace.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private String username;
    @Enumerated(EnumType.STRING)
    private AppUser.Role role;
}
