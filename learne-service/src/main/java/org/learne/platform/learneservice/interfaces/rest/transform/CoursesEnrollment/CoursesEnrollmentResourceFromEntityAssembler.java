package org.learne.platform.learneservice.interfaces.rest.transform.CoursesEnrollment;

import org.learne.platform.learneservice.domain.model.aggregates.CoursesEnrollment;
import org.learne.platform.learneservice.interfaces.rest.resources.CoursesEnrollment.CoursesEnrollmentResource;

public class CoursesEnrollmentResourceFromEntityAssembler {
    public static CoursesEnrollmentResource toResourceFromEntity(CoursesEnrollment entity) {
        return new CoursesEnrollmentResource(entity.getId(), entity.getStudentId(), entity.getCourse().getId());
    }
}
