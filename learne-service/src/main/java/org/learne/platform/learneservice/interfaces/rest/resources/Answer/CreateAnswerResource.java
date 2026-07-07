package org.learne.platform.learneservice.interfaces.rest.resources.Answer;

public record CreateAnswerResource(Long question_id, boolean is_correct,
                                   String description) {
}
