package cz.upce.nnpia_semestralni_prace.repository;

import cz.upce.nnpia_semestralni_prace.domain.League;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeagueRepository extends PagingAndSortingRepository<League, Long> {

}
