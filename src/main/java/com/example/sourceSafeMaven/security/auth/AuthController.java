package com.example.sourceSafeMaven.security.auth;

import com.example.sourceSafeMaven.models.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }

//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @PostMapping("/login")
//    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
//    }
//
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
//        // checking for username exists in a database
//        if(userRepository.existsByUserName(signUpDto.getUsername())){
//            return new ResponseEntity<>("Username is already exist!", HttpStatus.BAD_REQUEST);
//        }
//        // checking for email exists in a database
//        if(userRepository.existsByEmail(signUpDto.getEmail())){
//            return new ResponseEntity<>("Email is already exist!", HttpStatus.BAD_REQUEST);
//        }
//        // creating user object
//        User user = new User();
//        user.setUserName(signUpDto.getUsername());
//        user.setEmail(signUpDto.getEmail());
//        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
////        Role roles = roleRepository.findByUserName("ROLE_ADMIN").get();
////        user.setRole(Collections.singleton(roles));
//        userRepository.save(user);
//        return new ResponseEntity<>("User is registered successfully!", HttpStatus.OK);
//    }


}
