package org.learne.platform.learneservice.domain.services.Answer;

import org.learne.platform.learneservice.domain.model.aggregates.Answer;
import org.learne.platform.learneservice.domain.model.queries.Answer.GetAllAnswersQuery;
import org.learne.platform.learneservice.domain.model.queries.Answer.GetAnswerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AnswerQueryService {

    Optional<Answer> handle(GetAnswerByIdQuery query);

    List<Answer> handle(GetAllAnswersQuery query);
}
