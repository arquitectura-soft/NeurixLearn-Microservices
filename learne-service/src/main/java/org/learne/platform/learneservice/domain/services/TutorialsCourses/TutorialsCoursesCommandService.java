package org.learne.platform.learneservice.domain.services.TutorialsCourses;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsCourses;
import org.learne.platform.learneservice.domain.model.commands.TutorialsCourses.CreateTutorialsCoursesCommand;
import org.learne.platform.learneservice.domain.model.commands.TutorialsCourses.UpdateTutorialsCoursesCommand;

import java.util.Optional;

public interface TutorialsCoursesCommandService {
    Optional<TutorialsCourses> handle(UpdateTutorialsCoursesCommand command);
    Long handle(CreateTutorialsCoursesCommand command);
}
