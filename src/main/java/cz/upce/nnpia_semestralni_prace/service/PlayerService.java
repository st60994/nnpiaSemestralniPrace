package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.Country;
import cz.upce.nnpia_semestralni_prace.domain.Player;
import cz.upce.nnpia_semestralni_prace.dto.PlayerInputDto;
import cz.upce.nnpia_semestralni_prace.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubService clubService;
    private final CountryService countryService;

    @Transactional
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Transactional
    public Player findById(final Long id) throws ResourceNotFoundException {
        Optional<Player> result = playerRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Player not found");
        }
        return result.get();
    }

    @Transactional
    public Player create(final Player entity) {
        return playerRepository.save(entity);
    }

    @Transactional
    public Player updatePlayer(Long id, PlayerInputDto playerInputDto) throws ResourceNotFoundException {
        Player basePlayer = findById(id);
        Club club = clubService.findById(playerInputDto.getClubId());
        Country country = countryService.findById(playerInputDto.getCountryId());
        basePlayer.setName(playerInputDto.getName());
        basePlayer.setHeight(playerInputDto.getHeight());
        basePlayer.setPosition(playerInputDto.getPosition());
        basePlayer.setWeight(playerInputDto.getWeight());
        basePlayer.setPhotoPath(playerInputDto.getPhotoPath());
        basePlayer.setDateOfBirth(playerInputDto.getDateOfBirth());
        basePlayer.setPlayerCountry(country);
        basePlayer.setClub(club);
        return basePlayer;
    }

    @Transactional
    public void deletePlayer(Long id) throws ResourceNotFoundException {
        Player player = findById(id);
        playerRepository.delete(player);
    }

    public List<Player> findAllPlayersWithACountry(Country country) {
        return playerRepository.findAllByPlayerCountryEquals(country);
    }
}
