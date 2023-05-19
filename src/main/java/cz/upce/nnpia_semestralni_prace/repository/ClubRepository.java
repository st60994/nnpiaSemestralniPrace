package cz.upce.nnpia_semestralni_prace.repository;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findAll();

    List<Club> findAllByLeagues(League league);

    List<Club> findAllByClubCountryEquals(Country country);
}
