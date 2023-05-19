package cz.upce.nnpia_semestralni_prace.repository;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAll();

    List<Player> findAllByClubEquals(Club club);

    List<Player> findAllByPlayerCountryEquals(Country country);
}
