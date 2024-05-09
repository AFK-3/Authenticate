package id.ac.ui.cs.advprog.afk3.controller;

import id.ac.ui.cs.advprog.afk3.model.Builder.UserBuilder;
import id.ac.ui.cs.advprog.afk3.model.UserEntity;
import id.ac.ui.cs.advprog.afk3.model.dto.AuthResponseDto;
import id.ac.ui.cs.advprog.afk3.model.dto.LoginDto;
import id.ac.ui.cs.advprog.afk3.model.dto.RegisterDto;
import id.ac.ui.cs.advprog.afk3.repository.UserRepository;
import id.ac.ui.cs.advprog.afk3.security.JwtGenerator;
import id.ac.ui.cs.advprog.afk3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }
    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Autowired
    private UserBuilder userEntityBuilder;

    private JwtGenerator jwtGenerator;

    private  PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView login(Model model){
        LoginDto loginDto = new LoginDto();
        model.addAttribute("logindto", loginDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        System.out.println("starting login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@ModelAttribute LoginDto loginDto, Model model){
        System.out.println("after login "+loginDto.getUsername());
        UsernamePasswordAuthenticationToken aa = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword());
        System.out.println(aa.getPrincipal()+" "+aa.getCredentials()+" "+aa.getDetails());
        Authentication authentication = authenticationManager.authenticate(aa);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<String>(token,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userService.findByUsername(registerDto.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userEntityBuilder.reset()
                .addUsername(registerDto.getUsername())
                .addPassword(passwordEncoder.encode(registerDto.getPassword()))
                .addType(registerDto.getType())
                .addPhoneNumber(registerDto.getPhonenumber())
                .addAddress(registerDto.getAddress())
                .addName(registerDto.getName())
                .build();

        userService.create(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

}
