package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    @Transactional
    public Country findById(final Long id) throws ResourceNotFoundException {
        var result = countryRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Country not found");
        }
        return result.get();
    }
}
