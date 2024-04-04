package com.example.ppproject3.service;

import com.example.ppproject3.commonTypes.UserRole;
import com.example.ppproject3.errors.UserNotFoundError;
import com.example.ppproject3.infrastructure.entities.AuthEntity;
import com.example.ppproject3.infrastructure.repository.AuthRepository;
import jakarta.persistence.Tuple;
import org.springframework.security.core.Authentication;

public abstract class OwnershipService {
    private final AuthRepository authRepository;

    public OwnershipService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean isOwner(String username, Long userId){
        if(userId == null || username == null){
            return false;
        }
        AuthEntity authEntity = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundError.createWithUsername(username));
        return userId.equals(authEntity.getUser().getUserId());
    }

    public boolean isOwnerOrAdmin(AuthInfo authInfo, Long userId) {
        return isOwner(authInfo.getUsername(), userId) || (authInfo.getUserRole() != null && authInfo.getUserRole().equals(UserRole.ROLE_ADMIN));
    }

    public AuthInfo getCurrentAuth(Authentication authentication) {
        var username = authentication.getName();
        var authorities = authentication.getAuthorities().stream().toList();
        UserRole role = null;

        if(!authorities.isEmpty()) {
            role = UserRole.valueOf(authorities.getFirst().getAuthority());
        }

        return new AuthInfo(username, role);
    }

    public static class AuthInfo {
        private String username;
        private UserRole userRole;

        public AuthInfo(String username, UserRole userRole) {
            this.username = username;
            this.userRole = userRole;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public UserRole getUserRole() {
            return userRole;
        }

        public void setUserRole(UserRole userRole) {
            this.userRole = userRole;
        }
    }
}
