package org.learne.platform.learneservice.domain.services.Unit;

import org.learne.platform.learneservice.domain.model.commands.Unit.CreateUnitCommand;

public interface UnitCommandService {
    Long handle(CreateUnitCommand command);
}
