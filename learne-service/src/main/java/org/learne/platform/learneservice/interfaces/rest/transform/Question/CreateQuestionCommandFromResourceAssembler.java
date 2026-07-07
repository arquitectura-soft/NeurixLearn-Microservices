package org.learne.platform.learneservice.interfaces.rest.transform.Question;

import org.learne.platform.learneservice.domain.model.commands.Question.CreateQuestionCommand;
import org.learne.platform.learneservice.interfaces.rest.resources.Question.CreateQuestionResource;

public class CreateQuestionCommandFromResourceAssembler {

    public static CreateQuestionCommand ToCommandFromResource(CreateQuestionResource resource) {
        return new CreateQuestionCommand(resource.exam_id(), resource.question());
    }
}
