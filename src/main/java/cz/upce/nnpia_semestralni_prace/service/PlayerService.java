package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.Player;
import cz.upce.nnpia_semestralni_prace.dto.PlayerInputDto;
import cz.upce.nnpia_semestralni_prace.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Transactional
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Transactional
    public Player findById(final Long id) throws ResourceNotFoundException {
        var result = playerRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Player not found");
        }
        return result.get();
    }

    public Player create(final Player entity) {
        return playerRepository.save(entity);
    }
}
