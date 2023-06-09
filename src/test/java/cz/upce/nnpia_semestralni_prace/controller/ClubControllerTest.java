package cz.upce.nnpia_semestralni_prace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.upce.nnpia_semestralni_prace.Example;
import cz.upce.nnpia_semestralni_prace.domain.AppUser;
import cz.upce.nnpia_semestralni_prace.domain.Club;
import cz.upce.nnpia_semestralni_prace.domain.League;
import cz.upce.nnpia_semestralni_prace.repository.AppUserRepository;
import cz.upce.nnpia_semestralni_prace.repository.ClubRepository;
import cz.upce.nnpia_semestralni_prace.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.jfr.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureMockMvc
class ClubControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AppUserRepository appUserRepository;
    @LocalServerPort
    private int port;

    @Autowired
    private ClubRepository clubRepository;
    private String secret = JwtUtil.JWT_SECRET;
    private HttpHeaders headers = new HttpHeaders();
    @Mock
    private League mockedLeague;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        AppUser appUser = new AppUser("userName", "password", AppUser.Role.administrator);
        appUserRepository.save(appUser);
    }

    @AfterEach
    void tearDown() {
        clubRepository.deleteAll();
        appUserRepository.deleteAll();
    }

    @Test
    @Name(value = "Find all clubs without request param")
    void findAll() throws Exception {
        String token = Jwts.builder()
                .setSubject("userName")
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
        headers = new HttpHeaders();
        headers.setBearerAuth(token);

        Club club = clubRepository.save(Example.EXAMPLE_CLUB);
        String url = "http://localhost:" + port + "/clubs/" + club.getId();
        var resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Club receivedClub = objectMapper.readValue(responseContent, Club.class);
        boolean ret = (receivedClub.equals(club));
        assertTrue(ret);
    }

    @Test
    @Transactional
    @Name(value = "Find all clubs with league request param")
    void findAllWithSpecificLeague() throws Exception {
        String token = Jwts.builder()
                .setSubject("userName")
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
        headers = new HttpHeaders();
        headers.setBearerAuth(token);
        long leagueId = 1;
        when(mockedLeague.getId()).thenReturn(leagueId);
        Club fakeCLub = Example.createExampleClubWithLeague(mockedLeague);
        Club club = clubRepository.save(fakeCLub);
        String url = "http://localhost:" + port + "/clubs/" + club.getId() + "?leagueId=" + leagueId;
        var resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Club receivedClub = objectMapper.readValue(responseContent, Club.class);
        boolean ret = (receivedClub.toDto().equals(club.toDto()));
        assertTrue(ret);
    }
}