package com.volkan.controller;

import static com.volkan.constants.EndPoints.*;

import com.volkan.dto.request.UserLoginRequestDto;
import com.volkan.dto.request.UserRegisterRequestDto;
import com.volkan.dto.response.LoginResponseDto;
import com.volkan.repository.entity.User;
import com.volkan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
//localhost:9090/user
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService; //final olduğu için constructor otomatik oluşturuldu.

    /**
     * @RequestParam()=> default parametreyi zorunlu hale getirir ve
     * o parametreye özellikler kazandırır.
     * @param name
     * @param surname
     * @param password
     * @param email
     * @return
     */
    @GetMapping(SAVE)
    public ResponseEntity<User> save(String name, String surname, @RequestParam(defaultValue = "1234") String password, @RequestParam Optional<String> email) {
        String myemail=null;
        if(email.isPresent()) {
            myemail=email.get();
        }
        return ResponseEntity.ok(userService.save(name,surname,password,myemail));
    }

    @GetMapping("/register")
    public ResponseEntity<User> register(UserRegisterRequestDto dto) {
        return ResponseEntity.ok(userService.register2(dto));
    }
    @PostMapping("/register2")
    public ResponseEntity<User> register2(@RequestBody UserRegisterRequestDto dto) {
        return ResponseEntity.ok(userService.register2(dto));
    }
    @GetMapping("/findAllByOrderByName")
    public ResponseEntity<List<User>> findAllByOrderByName() {
       return ResponseEntity.ok(userService.findAllByOrderByName());
    }
    @GetMapping("/findByNameContaining")
    public ResponseEntity<List<User>> findByNameContainingIgnoreCase(String ifade) {
        return ResponseEntity.ok(userService.findByNameContainingIgnoreCase(ifade));
    }
    @GetMapping("/findByEmailContaining")
    public ResponseEntity<List<User>> findByEmailContainingIgnoreCase(String ifade) {
        return ResponseEntity.ok(userService.findByEmailContainingIgnoreCase(ifade));
    }
    @GetMapping("/findByEmailEndingWith")
    public ResponseEntity<List<User>> findByEmailEndingWithIgnoreCase(String ifade) {
        return ResponseEntity.ok(userService.findByEmailEndingWithIgnoreCase(ifade));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return ResponseEntity.ok(userService.doLogin(userLoginRequestDto));
    }

    @PostMapping("/login2")
    public ResponseEntity<?> login2(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        try {
            return ResponseEntity.ok(userService.doLogin(userLoginRequestDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/passwordcontrol")
    public ResponseEntity<List<User>> passwordLongerThan(int value) {
        return ResponseEntity.ok(userService.passwordLongerThan(value));
    }
    @GetMapping("/passwordcontrol2")
    public ResponseEntity<List<User>> passwordLongerThan2(int value) {
        return ResponseEntity.ok(userService.passwordLongerThan2(value));
    }
    @GetMapping("/passwordcontrol3")
    public ResponseEntity<List<User>> passwordLongerThan3(int value) {
        return ResponseEntity.ok(userService.passwordLongerThan3(value));
    }
}
