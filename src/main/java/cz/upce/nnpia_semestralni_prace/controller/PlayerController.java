package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.Player;
import cz.upce.nnpia_semestralni_prace.service.PlayerService;
import cz.upce.nnpia_semestralni_prace.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getAppUserJson(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = playerService.findById(id);
        return ResponseEntity.ok(result.toDto());
    }
}
