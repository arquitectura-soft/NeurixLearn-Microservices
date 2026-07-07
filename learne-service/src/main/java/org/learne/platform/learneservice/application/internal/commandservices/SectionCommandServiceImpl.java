package org.learne.platform.learneservice.application.internal.commandservices;

import org.learne.platform.learneservice.domain.model.aggregates.Section;
import org.learne.platform.learneservice.domain.model.commands.Section.CreateSectionCommand;
import org.learne.platform.learneservice.domain.services.Section.SectionCommandService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.SectionRepository;
import org.springframework.stereotype.Service;

@Service
public class SectionCommandServiceImpl implements SectionCommandService {
    private final SectionRepository sectionRepository;
    public SectionCommandServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }
    @Override
    public Long handle(CreateSectionCommand command) {
        if (sectionRepository.existsByTitle(command.title())) {
            throw new IllegalArgumentException("Title already exists");
        }
        var newSection = new Section(command);
        try {
            sectionRepository.save(newSection);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("An error occurred while saving the section " + e.getMessage());
        }
        return newSection.getId();
    }
}
