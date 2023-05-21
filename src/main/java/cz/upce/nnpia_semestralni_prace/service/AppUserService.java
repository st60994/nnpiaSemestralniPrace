package cz.upce.nnpia_semestralni_prace.service;

import cz.upce.nnpia_semestralni_prace.domain.AppUser;
import cz.upce.nnpia_semestralni_prace.dto.UserDetailsDto;
import cz.upce.nnpia_semestralni_prace.dto.input.AuthenticationRequest;
import cz.upce.nnpia_semestralni_prace.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public AppUser createAppUser(AuthenticationRequest authenticationRequest) throws UserAlreadyExistsException {
        if (appUserRepository.findAppUserByUsernameEquals(authenticationRequest.getUsername()) != null) {
            throw new UserAlreadyExistsException("User with this username already exists, please choose a different username!");
        }
        AppUser appUser = new AppUser(authenticationRequest.getUsername(), authenticationRequest.getPassword(), AppUser.Role.user);
        return appUserRepository.save(appUser);
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByUsernameEquals(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(appUser.getRole().name().toUpperCase());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(authority);
        return new UserDetailsDto(appUser.getUsername(), appUser.getPassword(), authorityList);
    }
}
