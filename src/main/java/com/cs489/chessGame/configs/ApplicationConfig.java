package com.cs489.chessGame.configs;

import com.cs489.chessGame.error.CustomException;
import com.cs489.chessGame.error.Errors;
import com.cs489.chessGame.models.UserDetail;
import com.cs489.chessGame.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    private UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService (){
        return (username -> {
            var user = userRepository.findUserByUsername(username).orElseThrow(()->{throw new CustomException(Errors.NOT_FOUND);});
            return modelMapper().map(user, UserDetail.class);
        });
    }
}