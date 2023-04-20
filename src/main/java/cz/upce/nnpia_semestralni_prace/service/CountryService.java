package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import cz.upce.nnpia_semestralni_prace.dto.input.CountryInputDto;
import cz.upce.nnpia_semestralni_prace.repository.ClubRepository;
import cz.upce.nnpia_semestralni_prace.repository.CountryRepository;
import cz.upce.nnpia_semestralni_prace.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Transactional
    public Country findById(final Long id) throws ResourceNotFoundException {
        var result = countryRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Country not found");
        }
        return result.get();
    }

    @Transactional
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(Long id) throws ResourceNotFoundException {
        Country country = findById(id);
        List<Player> countryPlayers = playerRepository.findAllByPlayerCountryEquals(country);
        List<Club> countryClubs = clubRepository.findAllByClubCountryEquals(country);
        countryPlayers.forEach(player -> player.setPlayerCountry(null));
        countryClubs.forEach(club -> club.setClubCountry(null));
        countryRepository.delete(country);
    }

    @Transactional
    public Country updateCountry(Long id, CountryInputDto countryInputDto) throws ResourceNotFoundException {
        Country baseCountry = findById(id);
        baseCountry.setName(countryInputDto.getName());
        baseCountry.setAbbreviation(countryInputDto.getAbbreviation());
        baseCountry.setFlagPath(countryInputDto.getFlagPath());
        return baseCountry;
    }
}
