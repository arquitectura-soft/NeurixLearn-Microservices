package org.learne.platform.learneservice.interfaces.rest.transform.TutorialsCourses;

import org.learne.platform.learneservice.domain.model.commands.TutorialsCourses.CreateTutorialsCoursesCommand;
import org.learne.platform.learneservice.interfaces.rest.resources.TutorialsCourses.CreateTutorialsCoursesResource;

public class CreateTutorialsCoursesCommandFromResourceAssembler {
    public static CreateTutorialsCoursesCommand toCommand(CreateTutorialsCoursesResource resource) {
        return new CreateTutorialsCoursesCommand(resource.courses_id(), resource.teacher_id(),
                resource.date(), resource.hour(), resource.is_reservated(), resource.link());
    }
}
