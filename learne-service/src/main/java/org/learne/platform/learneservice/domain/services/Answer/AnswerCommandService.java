package org.learne.platform.learneservice.domain.services.Answer;

import org.learne.platform.learneservice.domain.model.commands.Answer.CreateAnswerCommand;

public interface AnswerCommandService {

    Long handle(CreateAnswerCommand command);

}
