package org.learne.platform.learneservice.interfaces.rest.transform.Course;

import org.learne.platform.learneservice.domain.model.aggregates.Course;
import org.learne.platform.learneservice.interfaces.rest.resources.Course.CourseResource;

public class CourseResourceFromEntityAssembler {
    public static CourseResource toResourceFromEntity(Course entity) {
        return new CourseResource(entity.getId(), entity.getTitle(), entity.getDescription(),
                entity.getTeacherId(), entity.getLevel(), entity.getDuration(), entity.getPrior_knowledge(),
                entity.getPrincipal_image(), entity.getUrl_video());
    }
}
