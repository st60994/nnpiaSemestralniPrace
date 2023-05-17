package cz.upce.nnpia_semestralni_prace.repository;

import cz.upce.nnpia_semestralni_prace.domain.League;
import cz.upce.nnpia_semestralni_prace.domain.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

// TODO JPARepository
@Repository
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
    Page<Match> findAll(Pageable pageable);

    Page<Match> findAllByLeagueEquals(League league, Pageable pageable);
}
