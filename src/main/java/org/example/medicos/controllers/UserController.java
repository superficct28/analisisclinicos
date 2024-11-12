package org.example.medicos.controllers;

import jakarta.validation.Valid;
import org.example.medicos.models.User;
import org.example.medicos.models.roles.Rol;
import org.example.medicos.models.user.UserDTO;
import org.example.medicos.repository.RolRepository;
import org.example.medicos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepository rolRepository;

    @GetMapping
    public List<User> getUseres() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User>  createUser(@Valid @RequestBody UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.notFound().build();
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
        if( userDTO.getRolId() != null) {
            Optional<Rol> rol = rolRepository.findById(userDTO.getRolId());
            user.setRol(rol.orElse(null));
        }
        User createdUser = userRepository.save(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> especialidad = userRepository.findById(id);
        return especialidad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetalles) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setNombre(userDetalles.getNombre());
            user.setApellido(userDetalles.getApellido());
            user.setEmail(userDetalles.getEmail());
            user.setTelefono(userDetalles.getTelefono());
            user.setDireccion(userDetalles.getDireccion());
            user.setCi(userDetalles.getCi());
            user.setGenero(userDetalles.getGenero());
            user.setPassword(passwordEncoder.encode(userDetalles.getPassword()));

            final User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Usuario eliminado correctamente");
            response.put("statusCode", 200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
