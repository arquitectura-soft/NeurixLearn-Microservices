package org.learne.platform.learneservice.interfaces.rest.transform.TutorialsReservated;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learneservice.interfaces.rest.resources.TutorialsReservated.TutorialsReservatedResource;

public class TutorialsReservatedResourceFromEntityAssembler {
    public static TutorialsReservatedResource toResourceFromEntity(TutorialsReservated entity) {
        return new TutorialsReservatedResource(entity.getId(), entity.getStudentId(), entity.getTutorialsCourses().getId());
    }
}
