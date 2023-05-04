package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import cz.upce.nnpia_semestralni_prace.dto.PlayerDto;
import cz.upce.nnpia_semestralni_prace.dto.input.PlayerInputDto;
import cz.upce.nnpia_semestralni_prace.service.ClubService;
import cz.upce.nnpia_semestralni_prace.service.CountryService;
import cz.upce.nnpia_semestralni_prace.service.PlayerService;
import cz.upce.nnpia_semestralni_prace.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final ClubService clubService;
    private final CountryService countryService;

    @GetMapping("")
    public ResponseEntity<List<Player>> findAll(@RequestParam(value = "clubId") Long clubId) throws ResourceNotFoundException {
        List<Player> foundPlayers = playerService.findAll(clubId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(foundPlayers);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlayerDto> getPlayerJson(@PathVariable final Long id) throws ResourceNotFoundException {
        Player result = playerService.findById(id);
        return ResponseEntity.ok(result.toDto());
    }

    @PostMapping("")
    public ResponseEntity<PlayerDto> create(@RequestBody @Validated PlayerInputDto playerInputDto) throws ResourceNotFoundException {
        Club club = clubService.findById(playerInputDto.getClubId());
        Country country = countryService.findById(playerInputDto.getCountryId());

        Player result = playerService.create(toEntity(playerInputDto, club, country));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result.toDto());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PlayerDto> updatePlayer(@PathVariable final Long id, @RequestBody @Validated PlayerInputDto playerInputDto) throws ResourceNotFoundException {
        Player updatedPlayer = playerService.updatePlayer(id, playerInputDto);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(updatedPlayer.toDto());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        try {

            playerService.deletePlayer(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    private static Player toEntity(final PlayerInputDto playerInputDto, Club club, Country country) {
        return new Player(
                playerInputDto.getName(),
                playerInputDto.getShirtNumber(),
                playerInputDto.getDateOfBirth(),
                playerInputDto.getHeight(),
                playerInputDto.getWeight(),
                playerInputDto.getPhotoPath(),
                playerInputDto.getPosition(),
                club,
                country
        );
    }
}
