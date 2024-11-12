package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.User;
import org.example.medicos.models.user.LoginRequestDTO;
import org.example.medicos.models.user.UserDTO;
import org.example.medicos.services.UserService;
import org.example.medicos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserDTO userDTO) {
        // TODO: Verifica si el email existe
        if (userService.existByEmail(userDTO.getEmail())) {
            Map<String, Object> response = new HashMap<>();
            response.put("statusCode", HttpStatus.CONFLICT);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setNombre(userDTO.getNombre());
        user.setApellido(userDTO.getApellido());
        user.setCi(userDTO.getCi());
        user.setTelefono(userDTO.getTelefono());
        user.setDireccion(userDTO.getDireccion());
        user.setEmail(userDTO.getEmail());
        user.setGenero(userDTO.getGenero());
        // TODO: ENCRIPTAMOS LA CONTRASEÑA
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User userCreated = userService.create(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user", userCreated);
        response.put("statusCode", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            // TODO: Autenticar usuario
            if(!userService.existByEmail(loginRequest.getEmail())){
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            User user = userService.findByEmail(loginRequest.getEmail());

            // TODO: Generar token JWT
            //String jwt = jwtUtil.generateToken(user.getEmail());

            // TODO: Devolver token y detalles del usuario
            //response.put("token", jwt);
            response.put("user", user);
            response.put("statusCode", HttpStatus.OK);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            response.put("message", "Credenciales inválidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            response.put("message", "Error en la autenticación");
            response.put("error", e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
