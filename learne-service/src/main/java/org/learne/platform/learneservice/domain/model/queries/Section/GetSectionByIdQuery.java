package org.learne.platform.learneservice.domain.model.queries.Section;

public record GetSectionByIdQuery(Long id) {
    public GetSectionByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
