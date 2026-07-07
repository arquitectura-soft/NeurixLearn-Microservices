package org.learne.platform.learneservice.domain.services.Material;

import org.learne.platform.learneservice.domain.model.commands.Material.CreateMaterialCommand;
import org.learne.platform.learneservice.domain.model.commands.Material.DeleteMaterialCommand;


public interface MaterialCommandService {
    void handle(DeleteMaterialCommand command);
    Long handle(CreateMaterialCommand command);
}
