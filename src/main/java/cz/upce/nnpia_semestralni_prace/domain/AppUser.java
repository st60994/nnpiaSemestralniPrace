package cz.upce.nnpia_semestralni_prace.domain;

import cz.upce.nnpia_semestralni_prace.dto.AppUserDto;
import cz.upce.nnpia_semestralni_prace.dto.ClubDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    public AppUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static enum Role {administrator, user}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public AppUserDto toDto() {
        return new AppUserDto(
                getUsername(),
                getRole()
        );
    }

}
