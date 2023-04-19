package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.dto.ClubDto;
import cz.upce.nnpia_semestralni_prace.service.ClubService;
import cz.upce.nnpia_semestralni_prace.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;

    @GetMapping("")
    public List<Club> findAll() {
        return clubService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClubDto> getClubJson(@PathVariable final Long id) throws ResourceNotFoundException {
        Club result = clubService.findById(id);
        return ResponseEntity.ok(result.toDto());
    }

}
