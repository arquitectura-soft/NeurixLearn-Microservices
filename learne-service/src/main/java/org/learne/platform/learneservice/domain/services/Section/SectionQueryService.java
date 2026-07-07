package org.learne.platform.learneservice.domain.services.Section;

import org.learne.platform.learneservice.domain.model.aggregates.Section;
import org.learne.platform.learneservice.domain.model.queries.Section.GetAllSectionsQuery;
import org.learne.platform.learneservice.domain.model.queries.Section.GetSectionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SectionQueryService {
    Optional<Section> handle(GetSectionByIdQuery query);
    List<Section> handle(GetAllSectionsQuery query);
}
