package org.learne.platform.learneservice.interfaces.rest.transform.Answer;

import org.learne.platform.learneservice.domain.model.aggregates.Answer;
import org.learne.platform.learneservice.interfaces.rest.resources.Answer.AnswerResource;

public class AnswerResourceFromEntityAssembler {

    public static AnswerResource ToResourceFromEntity(Answer entity) {
        return new AnswerResource(entity.getId(), entity.getQuestion().getId(),
                entity.isCorrect(), entity.getDescription());
    }
}
