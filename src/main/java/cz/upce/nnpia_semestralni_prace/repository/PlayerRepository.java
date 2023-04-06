package cz.upce.nnpia_semestralni_prace.repository;

import cz.upce.nnpia_semestralni_prace.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
    List<Player> findAll();

    
}
