package org.example.itech_heaven.Service.Authentication;

import lombok.RequiredArgsConstructor;
import org.example.itech_heaven.Entity.Role;
import org.example.itech_heaven.Entity.User;
import org.example.itech_heaven.Repository.RoleRepository;
import org.example.itech_heaven.Repository.UserRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class FacebookLoginStrategy implements Oauth2LoginStrategy{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public User addOauth2User(OAuth2User oAuth2User) {
        Role role = roleRepository.findByName("ROLE_CUSTOMER");
        String fullname = oAuth2User.getAttributes().get("name").toString();
        String[] names = fullname.split(" ");
        String last = names[names.length - 1];


        String first = String.join(" ", Arrays.copyOf(names, names.length - 1));
        User user = new User();
        user.setUsername(oAuth2User.getAttribute("email"));
        user.setEmail(oAuth2User.getAttribute("email"));
        user.setProvider("facebook");
        user.setFirstname(first);
        user.setLastname(last);
        user.setRoles(Collections.singletonList(role));

        return userRepository.save(user);
    }
}
