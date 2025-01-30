package edu.cesi.negosud.security;

import edu.cesi.negosud.model.Client;
import edu.cesi.negosud.model.Employe;
import edu.cesi.negosud.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    private Client client;
    private Employe employe;

    private String email;
    private String password;

    public AppUserDetails(Client client) {
        this.client = client;
        this.email = client.getEmail();
        this.password = client.getPassword();
    }

    public AppUserDetails(Employe employe) {
        this.employe = employe;
        this.email = employe.getEmail();
        this.password = employe.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

         return client != null
                 ? List.of(new SimpleGrantedAuthority("ROLE_CLIENT"))
                 : List.of(new SimpleGrantedAuthority("ROLE_VENDEUR"));

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return actif;
    }
}
