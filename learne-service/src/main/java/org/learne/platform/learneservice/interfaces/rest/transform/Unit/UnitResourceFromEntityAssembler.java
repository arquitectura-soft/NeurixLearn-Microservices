package org.learne.platform.learneservice.interfaces.rest.transform.Unit;

import org.learne.platform.learneservice.domain.model.aggregates.Unit;
import org.learne.platform.learneservice.interfaces.rest.resources.Unit.UnitResource;

public class UnitResourceFromEntityAssembler {
    public static UnitResource ToResourceFromEntity(Unit entity) {
        return new UnitResource(entity.getId(), entity.getCourse().getId(), entity.getTitle());
    }
}
