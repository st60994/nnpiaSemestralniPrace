package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.repository.ClubRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    @Transactional
    public Club findById(final Long id) throws ResourceNotFoundException {
        var result = clubRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Club not found");
        }
        return result.get();
    }
}
