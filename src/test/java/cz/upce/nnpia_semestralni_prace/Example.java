package cz.upce.nnpia_semestralni_prace;

import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.League;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example {
    public static final Club EXAMPLE_CLUB = new Club(100L, "club1", "nick",
            LocalDate.of(2000, 5, 21), "coach1", "location",
            "/example/pic.png", "desc",
            Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(),
            null);

    private Example() {

    }

    public static Club createExampleClubWithLeague(League league) {
        List<League> leagueList = new ArrayList<>();
        leagueList.add(league);
        return new Club(100L, "club1", "nick",
                LocalDate.of(2000, 5, 21), "coach1", "location",
                "/example/pic.png", "desc",
                new ArrayList<>(),new ArrayList<>(), leagueList, new ArrayList<>(),
                null);

    }
}
