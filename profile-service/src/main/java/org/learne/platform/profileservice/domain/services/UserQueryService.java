package org.learne.platform.profileservice.domain.services;

import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.learne.platform.profileservice.domain.model.queries.GetAllUsersQuery;
import org.learne.platform.profileservice.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle(GetUserByIdQuery query);
    List<User> handle(GetAllUsersQuery query);
}
