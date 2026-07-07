package org.learne.platform.learneservice.interfaces.rest.transform.TutorialsReservated;

import org.learne.platform.learneservice.domain.model.commands.CreateTutorialsReservatedCommand;
import org.learne.platform.learneservice.interfaces.rest.resources.TutorialsReservated.CreateTutorialsReservatedResource;

public class CreateTutorialsReservatedCommandFromResourceAssembler {
    public static CreateTutorialsReservatedCommand toCommand(CreateTutorialsReservatedResource resource) {
        return new CreateTutorialsReservatedCommand(resource.student_id(), resource.tutorial_id());
    }
}
