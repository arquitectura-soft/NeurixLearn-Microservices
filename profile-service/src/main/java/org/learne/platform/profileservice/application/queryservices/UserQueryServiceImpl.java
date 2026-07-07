package org.learne.platform.profileservice.application.queryservices;

import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.learne.platform.profileservice.domain.model.queries.GetAllUsersQuery;
import org.learne.platform.profileservice.domain.model.queries.GetUserByIdQuery;
import org.learne.platform.profileservice.domain.services.UserQueryService;
import org.learne.platform.profileservice.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }
}
