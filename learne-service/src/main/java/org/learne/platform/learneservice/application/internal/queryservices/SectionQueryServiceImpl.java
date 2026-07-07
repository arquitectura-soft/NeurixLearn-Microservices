package org.learne.platform.learneservice.application.internal.queryservices;

import org.learne.platform.learneservice.domain.model.aggregates.Section;
import org.learne.platform.learneservice.domain.model.queries.Section.GetAllSectionsQuery;
import org.learne.platform.learneservice.domain.model.queries.Section.GetSectionByIdQuery;
import org.learne.platform.learneservice.domain.services.Section.SectionQueryService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionQueryServiceImpl implements SectionQueryService {
    private final SectionRepository sectionRepository;
    public SectionQueryServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }
    @Override
    public Optional<Section> handle(GetSectionByIdQuery query) {
        return sectionRepository.findById(query.id());
    }
    @Override
    public List<Section> handle(GetAllSectionsQuery query) {
        return sectionRepository.findAll();
    }
}
