package org.learne.platform.learneservice.interfaces.rest.transform.Unit;

import org.learne.platform.learneservice.domain.model.commands.Unit.CreateUnitCommand;
import org.learne.platform.learneservice.interfaces.rest.resources.Unit.CreateUnitResource;

public class CreateUnitCommandFromResourceAssembler {
    public static CreateUnitCommand ToCommandFromResource(CreateUnitResource resource) {
        return new CreateUnitCommand(resource.course_id(), resource.title());
    }
}
