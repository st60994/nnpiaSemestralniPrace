package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.League;
import cz.upce.nnpia_semestralni_prace.domain.Match;
import cz.upce.nnpia_semestralni_prace.dto.input.MatchInputDto;
import cz.upce.nnpia_semestralni_prace.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final ClubService clubService;
    private final LeagueService leagueService;

    public Page<Match> findAll(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }

    public Page<Match> findAllWithLeagueId(Long leagueId, Pageable pageable) throws ResourceNotFoundException {
        League league = leagueService.findById(leagueId);
        return matchRepository.findAllByLeagueEquals(league, pageable);
    }

    public Match findById(Long id) throws ResourceNotFoundException {
        var result = matchRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Match not found!");
        }
        return result.get();
    }

    public Match create(Match entity) {
        return matchRepository.save(entity);
    }

    public Match update(Long id, MatchInputDto matchInputDto) throws ResourceNotFoundException {
        Match baseMatch = findById(id);
        Club homeTeam = clubService.findById(matchInputDto.getHomeTeamId());
        Club awayTeam = clubService.findById(matchInputDto.getAwayTeamId());
        League league = leagueService.findById(matchInputDto.getLeagueId());

        baseMatch.setDate(matchInputDto.getDate());
        baseMatch.setAwayTeam(awayTeam);
        baseMatch.setHomeTeam(homeTeam);
        baseMatch.setLeague(league);
        baseMatch.setAwayTeamScore(matchInputDto.getAwayTeamScore());
        baseMatch.setHomeTeamScore(matchInputDto.getHomeTeamScore());
        return baseMatch;
    }

    public void deleteMatch(Long id) throws ResourceNotFoundException {
        Match match = findById(id);
        this.matchRepository.delete(match);
    }
}
