package org.learne.platform.learneservice.domain.model.queries.Exam;

public record GetExamByIdQuery(Long id) {

    public GetExamByIdQuery {
        if(id == null) {
            throw new NullPointerException("Id is required");
        }
        if(id <= 0) {
            throw new IllegalArgumentException("Id must be a positive number");
        }
    }
}
