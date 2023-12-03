package com.example.sourceSafeMaven.security.auth;

import com.example.sourceSafeMaven.models.AuthResponseModel;
import com.example.sourceSafeMaven.models.ErrorResponse;
import com.example.sourceSafeMaven.models.authExceptions.RegistrationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
//s
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ){
        try {
            AuthResponseModel response = authService.register(request);
            return ResponseEntity.ok(response);

        } catch (RegistrationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        try {
            AuthResponseModel response = authService.authenticate(request);
            System.out.println("in try controller");
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            System.out.println("in catch controller");

            // Handle user not found exception
            //another way
           /* Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("msg", "User not found");   then put errorResponse in .body(errorResponse) */

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User Not Found"));
        }
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
