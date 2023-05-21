package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.AppUser;
import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.dto.AppUserDto;
import cz.upce.nnpia_semestralni_prace.dto.ClubDto;
import cz.upce.nnpia_semestralni_prace.dto.input.AuthenticationRequest;
import cz.upce.nnpia_semestralni_prace.service.AppUserService;
import cz.upce.nnpia_semestralni_prace.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private AppUserService appUserService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<AppUserDto>> findAll(){
        List<AppUser> foundUsers;
        foundUsers = appUserService.findAll();
        List<AppUserDto> appUserDtos = foundUsers.stream().map(AppUser::toDto).toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(appUserDtos);
    }
}
