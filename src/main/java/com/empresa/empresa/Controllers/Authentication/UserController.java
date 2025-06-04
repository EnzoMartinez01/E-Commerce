package com.empresa.empresa.Controllers.Authentication;

import com.empresa.empresa.Dto.Authentication.UserDto;
import com.empresa.empresa.Dto.Authentication.UserUpdateDto;
import com.empresa.empresa.Models.Authentication.Roles;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Services.Authentication.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Get All Users
    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<UserDto> users = userService.allUsers(page, size);
            return ResponseEntity.ok(users);
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getUserById/{idUser}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer idUser){
        try {
            UserDto user = userService.getUserById(idUser);
            return ResponseEntity.ok(user);
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        String username = principal.getName();
        UserDto user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/getUsersByFilters")
    public ResponseEntity<Page<UserDto>> getUsersByFilters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String searchTerms,
            @RequestParam(required = false) Integer roleId,
            @RequestParam(required = false) Boolean isActive) {
        try {
            Page<UserDto> users = userService.getUsersByFilters(searchTerms, roleId, isActive, page, size);
            return ResponseEntity.ok(users);
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

  @PutMapping("/updateUser/{idUser}")
public ResponseEntity<Map<String, String>> updateUser(@PathVariable Integer idUser, @RequestBody UserUpdateDto updatedUser){
    try {
        userService.updateUserFromDto(idUser, updatedUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User updated successfully");
        return ResponseEntity.ok(response);
    } catch (AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    } catch (RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

    @PatchMapping("/deactivateUser/{idUser}")
    public ResponseEntity<Map<String, String>> deactivateUser(@PathVariable Integer idUser){
        try {
            userService.deactivateUser(idUser);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User deactivated successfully");
            return ResponseEntity.ok(response);
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getRolesAll")
    public ResponseEntity<List<Roles>> getRolesAll(){
        try {
            List<Roles> roles = userService.getRolesAll();
            return ResponseEntity.ok(roles);
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
