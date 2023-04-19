package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import cz.upce.nnpia_semestralni_prace.dto.*;
import cz.upce.nnpia_semestralni_prace.service.CountryService;
import cz.upce.nnpia_semestralni_prace.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("")
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CountryDto> getCountryJson(@PathVariable final Long id) throws ResourceNotFoundException {
        Country result = countryService.findById(id);
        return ResponseEntity.ok(result.toDto());
    }

    @PostMapping("")
    public ResponseEntity<CountryDto> create(@RequestBody @Validated final CountryInputDto countryInputDto) {

        Country result = countryService.create(toEntity(countryInputDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result.toDto());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable final Long id, @RequestBody @Validated CountryInputDto countryInputDto) throws ResourceNotFoundException {
        Country updatedCountry = countryService.updateCountry(id, countryInputDto);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(updatedCountry.toDto());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        try {
            countryService.deleteCountry(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    private static Country toEntity(final CountryInputDto countryInputDto) {
        return new Country(
                countryInputDto.getName(),
                countryInputDto.getAbbreviation(),
                countryInputDto.getFlagPath()
        );
    }

}
