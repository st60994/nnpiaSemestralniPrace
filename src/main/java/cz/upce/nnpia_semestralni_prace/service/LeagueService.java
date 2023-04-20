package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.League;
import cz.upce.nnpia_semestralni_prace.dto.input.LeagueInputDto;
import cz.upce.nnpia_semestralni_prace.repository.LeagueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class LeagueService {
    private final LeagueRepository leagueRepository;

    @Transactional
    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    @Transactional
    public League findById(Long id) throws ResourceNotFoundException {
        var result = leagueRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("League not found");
        }
        return result.get();
    }

    @Transactional
    public League create(League entity) {
        return leagueRepository.save(entity);
    }

    @Transactional
    public League updateLeague(Long id, LeagueInputDto leagueInputDto) throws ResourceNotFoundException {
        League league = findById(id);
        league.setName(leagueInputDto.getName());
        league.setImgPath(league.getImgPath());
        league.setFoundationDate(league.getFoundationDate());
        return league;
    }

    @Transactional
    public void deleteLeague(Long id) throws ResourceNotFoundException {
        League league = findById(id);
        leagueRepository.delete(league);
    }
}
