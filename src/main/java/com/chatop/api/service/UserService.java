package com.chatop.api.service;

import com.chatop.api.dto.UserResponseDTO;
import com.chatop.api.exception.UserNotFoundException;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponseDTO getUser(Long id){
        return userRepository.findById(id)
                .map(this::toUserResponseDTO)
                .orElseThrow(UserNotFoundException::new);
    }

    private UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setCreatedAt(user.getCreatedAt());
        userResponseDTO.setUpdatedAt(user.getUpdatedAt());

        return userResponseDTO;
    }

    public Optional<User> findUserEntityById(Long id){
        return userRepository.findById(id);
    }
    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
