package com.project.newyearthon.service;

import com.project.newyearthon.domain.Role;
import com.project.newyearthon.domain.User;
import com.project.newyearthon.domain.role.Guest;
import com.project.newyearthon.domain.role.Supplier;
import com.project.newyearthon.repository.GuestRepository;
import com.project.newyearthon.repository.SupplierRepository;
import com.project.newyearthon.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SupplierRepository supplierRepository;
    private final GuestRepository guestRepository;

    public void registerUser(String email, String password, String phoneNumber, boolean part) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .phoneNumber(phoneNumber)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        if(part){
            Supplier supplier = Supplier.builder()
                    .user(user).build();
            supplierRepository.save(supplier);
        } else{
            Guest guest = Guest.builder()
                    .user(user).build();
            guestRepository.save(guest);
        }
    }

    public User authenticateUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다!"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다!");
        }
        return user;
    }

    public User getCurrentUser() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if( authentication==null||!authentication.isAuthenticated()){
            throw new RuntimeException("no authicated user found");
        }
        Optional<User> user=userRepository.findByEmail(authentication.getName());
        if(user.isEmpty()) {
            throw new IllegalStateException("회원이 없습니다.");
        }
        return user.get();

    }
}
