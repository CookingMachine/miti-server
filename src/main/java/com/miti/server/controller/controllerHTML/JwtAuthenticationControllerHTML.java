package com.miti.server.controller.controllerHTML;

import com.miti.server.config.jwt.JwtRequestFilter;
import com.miti.server.config.jwt.JwtUserDetailsService;
import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.model.dto.CategoryDTO;
import com.miti.server.model.jwt.JwtRequest;
import com.miti.server.model.jwt.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationControllerHTML {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final JwtUserDetailsService userDetailsService;

    @RequestMapping(value = {"/authentication"}, method = RequestMethod.GET)
    public String authentication(Model model, HttpServletRequest req) {
        String token = null;
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token"))
                    token = "Bearer " + cookie.getValue().toString();
            }
        }

        JwtRequest auth = new JwtRequest();
        model.addAttribute("auth", auth);
        model.addAttribute("token", token);
        model.addAttribute("yourToken", token);
        return "authentication";
    }

    @RequestMapping(value = {"/authentication"}, method = RequestMethod.POST)
    public String addCategory(Model model, @ModelAttribute("auth") JwtRequest jwtRequest, HttpServletResponse res) throws Exception{
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();

        authenticate(username, password);
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String token = jwtUtil.generateToken(userDetails);

        return "main";
    }

    private void authenticate (String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException de) {
            throw new Exception("USER_DISABLED", de);
        } catch (BadCredentialsException bce) {
            throw new Exception("INVALID_CREDENTIALS", bce);
        }
    }

}
