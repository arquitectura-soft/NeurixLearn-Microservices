package org.learne.platform.learneservice.domain.model.queries.Answer;

public record GetAnswerByIdQuery(Long id) {

    public GetAnswerByIdQuery {
        if(id == null) {
            throw new NullPointerException("Id is required");
        }
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be a positive number");
        }
    }
}
