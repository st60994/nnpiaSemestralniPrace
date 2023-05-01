package cz.upce.nnpia_semestralni_prace.controller;

import cz.upce.nnpia_semestralni_prace.dto.AuthenticationRequest;
import cz.upce.nnpia_semestralni_prace.dto.AuthenticationResponse;
import cz.upce.nnpia_semestralni_prace.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class MainController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity createAuthenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final String jwt = jwtUtil.generateJwtToken(authentication);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new AuthenticationResponse(jwt));
    }
}

