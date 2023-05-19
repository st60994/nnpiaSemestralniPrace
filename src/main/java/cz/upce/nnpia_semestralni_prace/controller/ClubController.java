package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.League;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import cz.upce.nnpia_semestralni_prace.dto.ClubDto;
import cz.upce.nnpia_semestralni_prace.dto.PlayerDto;
import cz.upce.nnpia_semestralni_prace.dto.input.ClubInputDto;
import cz.upce.nnpia_semestralni_prace.service.ClubService;
import cz.upce.nnpia_semestralni_prace.service.CountryService;
import cz.upce.nnpia_semestralni_prace.service.LeagueService;
import cz.upce.nnpia_semestralni_prace.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/clubs")
public class ClubController {
    private final ClubService clubService;
    private final CountryService countryService;
    private final LeagueService leagueService;

    @GetMapping("")
    public ResponseEntity<List<ClubDto>> findAll(@RequestParam(value = "leagueId") Long leagueId) throws ResourceNotFoundException {
        List<Club> foundClubs;
        if (leagueId == null) {
            foundClubs = clubService.findAll();
        } else {
            foundClubs = clubService.findAllWithLeagueId(leagueId);
        }
        List<ClubDto> clubsDtos = foundClubs.stream().map(Club::toDto).toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(clubsDtos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClubDto> getClubJson(@PathVariable final Long id) throws ResourceNotFoundException {
        Club result = clubService.findById(id);
        return ResponseEntity.ok(result.toDto());
    }

    @PostMapping("")
    public ResponseEntity<ClubDto> create(@RequestBody @Validated ClubInputDto clubInputDto) throws ResourceNotFoundException {
        Country country = countryService.findById(clubInputDto.getClubCountryId());
        List<League> leagues = new ArrayList<>();
        for (Long leagueId : clubInputDto.getLeagueIds()) {
            leagues.add(leagueService.findById(leagueId));
        }

        Club club = clubService.create(toEntity(clubInputDto, country, leagues));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(club.toDto());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClubDto> updateClub(@PathVariable final Long id, @RequestBody @Validated ClubInputDto clubInputDto) throws ResourceNotFoundException {
        Club updatedClub = clubService.updateClub(id, clubInputDto);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(updatedClub.toDto());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        try {
            clubService.deleteClub(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }


    private static Club toEntity(final ClubInputDto clubInputDto, Country country, List<League> leagues) {
        return new Club(
                clubInputDto.getName(),
                clubInputDto.getNickName(),
                clubInputDto.getFoundationDate(),
                clubInputDto.getCoachName(),
                clubInputDto.getLocation(),
                clubInputDto.getImgPath(),
                clubInputDto.getDescription(),
                country,
                Collections.emptyList(),
                Collections.emptyList(),
                leagues
        );
    }

}
