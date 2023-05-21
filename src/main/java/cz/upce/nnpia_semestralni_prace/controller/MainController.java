package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.domain.AppUser;
import cz.upce.nnpia_semestralni_prace.dto.input.AuthenticationRequest;
import cz.upce.nnpia_semestralni_prace.dto.AuthenticationResponse;
import cz.upce.nnpia_semestralni_prace.service.AppUserService;
import cz.upce.nnpia_semestralni_prace.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class MainController {

    private AuthenticationManager authenticationManager;
    private AppUserService appUserService;
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity createAuthenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final String jwt = jwtUtil.generateJwtToken(authentication);
        UserDetails userDetails = appUserService.loadUserByUsername(authenticationRequest.getUsername());
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            String role = authority.getAuthority();
            roles.add(role);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new AuthenticationResponse(jwt, roles.get(0)));
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> createNewUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        AppUser appUser = appUserService.createAppUser(authenticationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(appUser);
    }
}

