package org.learne.platform.learneservice.interfaces.rest.transform.Exam;

import org.learne.platform.learneservice.domain.model.aggregates.Exam;
import org.learne.platform.learneservice.interfaces.rest.resources.Exam.ExamResource;

public class ExamResourceFromEntityAssembler {

    public static ExamResource ToResourceFromEntity(Exam entity) {
        return new ExamResource(entity.getId(), entity.getUnit().getId(),
                entity.getCourse().getId(), entity.getTitle());
    }
}
