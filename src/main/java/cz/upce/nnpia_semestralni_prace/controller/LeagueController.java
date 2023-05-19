package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.League;
import cz.upce.nnpia_semestralni_prace.dto.ClubDto;
import cz.upce.nnpia_semestralni_prace.dto.LeagueDto;
import cz.upce.nnpia_semestralni_prace.dto.input.LeagueInputDto;
import cz.upce.nnpia_semestralni_prace.service.LeagueService;
import cz.upce.nnpia_semestralni_prace.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/leagues")
public class LeagueController {

    private final LeagueService leagueService;

    @GetMapping
    public ResponseEntity<List<LeagueDto>> findAll() {
        List<League> leagues = leagueService.findAll();
        List<LeagueDto> leagueDtos = leagues.stream().map(League::toDto).toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(leagueDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueDto> getLeagueJson(@PathVariable Long id) throws ResourceNotFoundException {
        League result = leagueService.findById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(result.toDto());
    }

    @PostMapping()
    public ResponseEntity<LeagueDto> create(@RequestBody @Validated final LeagueInputDto leagueInputDto) {
        League result = leagueService.create(toEntity(leagueInputDto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result.toDto());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LeagueDto> updateLeague(@PathVariable final Long id, @RequestBody @Validated LeagueInputDto leagueInputDto) throws ResourceNotFoundException {
        League updatedLeague = leagueService.updateLeague(id, leagueInputDto);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(updatedLeague.toDto());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable Long id) {
        try {
            leagueService.deleteLeague(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    private static League toEntity(final LeagueInputDto leagueInput) {
        return new League(
                leagueInput.getName(),
                leagueInput.getFoundationDate(),
                leagueInput.getImgPath()
        );
    }
}
