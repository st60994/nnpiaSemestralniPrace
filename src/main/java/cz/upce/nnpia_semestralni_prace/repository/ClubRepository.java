package cz.upce.nnpia_semestralni_prace.repository;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClubRepository extends PagingAndSortingRepository<Club, Long> {
    List<Club> findAll();

    List<Club> findAllByClubCountryEquals(Country country);
}
