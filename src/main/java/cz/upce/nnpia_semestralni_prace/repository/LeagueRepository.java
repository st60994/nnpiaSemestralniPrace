package cz.upce.nnpia_semestralni_prace.repository;

import cz.upce.nnpia_semestralni_prace.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    List<League> findAll();
}
