package com.hibejix.cursomc.resources;

import com.hibejix.cursomc.dto.EmailDTO;
import com.hibejix.cursomc.security.JWTUtil;
import com.hibejix.cursomc.security.UserSpringSecurity;
import com.hibejix.cursomc.services.AuthService;
import com.hibejix.cursomc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author msalvador
 *
 */

@RestController
@RequestMapping(value="/hibejix-comercio/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {

        UserSpringSecurity userSpringSecurity = UserService.authenticated();
        String token = jwtUtil.generateToken(userSpringSecurity.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        authService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }
}
