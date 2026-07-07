package org.learne.platform.learneservice.interfaces.rest.resources.Answer;

public record AnswerResource(Long id, Long question_id, boolean is_correct,
                             String description) {
}
