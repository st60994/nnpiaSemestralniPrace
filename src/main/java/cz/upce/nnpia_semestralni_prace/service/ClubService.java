package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.League;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import cz.upce.nnpia_semestralni_prace.dto.input.ClubInputDto;
import cz.upce.nnpia_semestralni_prace.repository.ClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final CountryService countryService;
    private final LeagueService leagueService;

    @Transactional
    public Club findById(final Long id) throws ResourceNotFoundException {
        var result = clubRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Club not found");
        }
        return result.get();
    }

    @Transactional
    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    @Transactional
    public List<Club> findAllClubsInACountry(Country country) {
        return null;
    }

    @Transactional
    public Club create(Club club) {
        return clubRepository.save(club);
    }

    @Transactional
    public void deleteClub(Long id) throws ResourceNotFoundException {
        // TODO odstraní to kluby všem hráčům, kteří byli součástí tohohle klubu?
        Club club = findById(id);
        clubRepository.delete(club);
    }

    public Club updateClub(Long id, ClubInputDto clubInputDto) throws ResourceNotFoundException {
        List<League> leagues = new ArrayList<>();
        for (Long leagueId : clubInputDto.getLeagueIds()) {
            leagues.add(leagueService.findById(leagueId));
        }
        Country country = countryService.findById(clubInputDto.getClubCountryId());
        Club updatedClub = findById(id);
        updatedClub.setClubCountry(country);
        updatedClub.setLeagues(leagues);
        updatedClub.setDescription(clubInputDto.getDescription());
        updatedClub.setName(clubInputDto.getName());
        updatedClub.setLocation(clubInputDto.getLocation());
        updatedClub.setCoachName(clubInputDto.getCoachName());
        updatedClub.setImgPath(clubInputDto.getImgPath());
        updatedClub.setNickName(clubInputDto.getNickName());
        updatedClub.setFoundationDate(clubInputDto.getFoundationDate());
        return updatedClub;
    }

    public List<Club> findAllWithLeagueId(Long leagueId) throws ResourceNotFoundException {
        League league = leagueService.findById(leagueId);
        return clubRepository.findAllByLeagues(league);
    }
}
