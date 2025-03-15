package com.akshatsonic.globetotter.service;

import com.akshatsonic.globetotter.models.Session;
import com.akshatsonic.globetotter.models.User;
import com.akshatsonic.globetotter.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService {

    @Value("${session.expiry.time}")
    private Long sessionExpiryTime;


    private final SessionRepository sessionRepository;

    public Session getValidSession(Long userId){
        Optional<Session> session = sessionRepository.findFirstByUserIdOrderByIdDesc(userId);
        if(session.isEmpty() || !session.get().getIsActive()){
            return null;
        }
        else if (session.get().getExpiryTime().isBefore(LocalDateTime.now())){
            session.get().setIsActive(false);
            sessionRepository.save(session.get());
            return null;
        }
        return session.get();
    }

    public boolean isValid(String sessionToken, Long userId){
        Session activeSession = getValidSession(userId);
        return activeSession != null && activeSession.getSessionToken().equals(sessionToken);
    }

    public Session createSession(User user){
        Session session = getValidSession(user.getId());
        if(session==null){
            Session newSession =  Session.builder()
                    .sessionToken(UUID.randomUUID().toString())
                    .user(user)
                    .expiryTime(LocalDateTime.now().plusSeconds(sessionExpiryTime/1000))
                    .isActive(true)
                    .build();
            sessionRepository.save(newSession);
            return newSession;
        }
        else{
            session.setExpiryTime(LocalDateTime.now().plusSeconds(sessionExpiryTime/1000));
            sessionRepository.save(session);
            return session;
        }
    }

    public void invalidateSession(String sessionToken, Long userId){
        Session session = getValidSession(userId);
        if(session!=null && session.getSessionToken().equals(sessionToken)){
            session.setIsActive(false);
            sessionRepository.save(session);
        }
    }
}
