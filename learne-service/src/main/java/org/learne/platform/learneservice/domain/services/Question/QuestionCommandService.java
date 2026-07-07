package org.learne.platform.learneservice.domain.services.Question;

import org.learne.platform.learneservice.domain.model.commands.Question.CreateQuestionCommand;

public interface QuestionCommandService {

    Long handle(CreateQuestionCommand command);
}
