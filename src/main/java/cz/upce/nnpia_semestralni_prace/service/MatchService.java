package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.Match;
import cz.upce.nnpia_semestralni_prace.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public Match findById(Long id) throws ResourceNotFoundException {
        var result = matchRepository.findById(id);
        if (!result.isPresent()) {
            throw new ResourceNotFoundException("Match not found!");
        }
        return result.get();
    }

    public Match create(Match entity) {
        return matchRepository.save(entity);
    }
}
