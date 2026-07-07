package org.learne.platform.learneservice.interfaces.rest.transform.Section;

import org.learne.platform.learneservice.domain.model.aggregates.Section;
import org.learne.platform.learneservice.interfaces.rest.resources.Section.SectionResource;

public class SectionResourceFromEntityAssembler {
    public static SectionResource ToResourceFromEntity(Section entity) {
        return new SectionResource(entity.getId(), entity.getUnit().getId(), entity.getTitle(),
                entity.getDescription(), entity.getUrl_video());
    }
}
