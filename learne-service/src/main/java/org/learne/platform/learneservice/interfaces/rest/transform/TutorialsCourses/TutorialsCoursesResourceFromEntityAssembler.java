package org.learne.platform.learneservice.interfaces.rest.transform.TutorialsCourses;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsCourses;
import org.learne.platform.learneservice.interfaces.rest.resources.TutorialsCourses.TutorialsCoursesResource;

public class TutorialsCoursesResourceFromEntityAssembler {
    public static TutorialsCoursesResource toResourceFromEntity(TutorialsCourses entity) {
        return new TutorialsCoursesResource(entity.getId(), entity.getCourse().getId(),
                entity.getTeacherId(), entity.getDate(), entity.getHour(), entity.getIsReservated(),
                entity.getLink());
    }
}
