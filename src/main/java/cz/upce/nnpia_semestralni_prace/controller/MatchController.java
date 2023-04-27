package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.League;
import cz.upce.nnpia_semestralni_prace.domain.Match;
import cz.upce.nnpia_semestralni_prace.dto.MatchDto;
import cz.upce.nnpia_semestralni_prace.dto.input.MatchInputDto;
import cz.upce.nnpia_semestralni_prace.service.ClubService;
import cz.upce.nnpia_semestralni_prace.service.LeagueService;
import cz.upce.nnpia_semestralni_prace.service.MatchService;
import cz.upce.nnpia_semestralni_prace.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final ClubService clubService;
    private final LeagueService leagueService;

    @GetMapping("")
    public ResponseEntity<List<Match>> findAll() {
        // TODO při get endpointech posílat jiný object, kde mám jen zkratku a fotku?
        List<Match> matches = matchService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Match result = matchService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result.toDto());
    }

    @PostMapping("")
    public ResponseEntity<MatchDto> create(@RequestBody @Validated MatchInputDto matchInputDto) throws ResourceNotFoundException {
        Club homeTeam = clubService.findById(matchInputDto.getHomeTeamId());
        Club awayTeam = clubService.findById(matchInputDto.getAwayTeamId());
        League league = leagueService.findById(matchInputDto.getLeagueId());

        Match result = matchService.create(toEntity(matchInputDto, homeTeam, awayTeam, league));
        return ResponseEntity.status(HttpStatus.CREATED).body(result.toDto());
    }

    @PutMapping("{id}")
    public ResponseEntity<MatchDto> updateMatch(@PathVariable Long id, @RequestBody @Validated MatchInputDto matchInputDto) throws ResourceNotFoundException {
        Match updatedMatch = matchService.update(id, matchInputDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(updatedMatch.toDto());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        try {
            matchService.deleteMatch(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    private static Match toEntity(MatchInputDto matchInputDto, Club homeTeam, Club awayTeam, League league) {
        return new Match(
                matchInputDto.getDate(),
                matchInputDto.getHomeTeamScore(),
                matchInputDto.getAwayTeamScore(),
                homeTeam,
                awayTeam,
                league
        );
    }
}
