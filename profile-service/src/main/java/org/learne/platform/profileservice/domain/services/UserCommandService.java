package org.learne.platform.profileservice.domain.services;

import org.learne.platform.profileservice.domain.model.aggregates.User;
import org.learne.platform.profileservice.domain.model.commands.CreateUserCommand;
import org.learne.platform.profileservice.domain.model.commands.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(UpdateUserCommand command);
    Long handle(CreateUserCommand command);
}
