package org.learne.platform.learneservice.domain.services.Section;

import org.learne.platform.learneservice.domain.model.commands.Section.CreateSectionCommand;

public interface SectionCommandService {
    Long handle(CreateSectionCommand command);
}
