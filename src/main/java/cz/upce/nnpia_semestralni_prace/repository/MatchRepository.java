package cz.upce.nnpia_semestralni_prace.repository;

import cz.upce.nnpia_semestralni_prace.domain.Match;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
    List<Match> findAll();
}
