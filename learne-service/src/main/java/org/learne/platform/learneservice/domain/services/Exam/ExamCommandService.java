package org.learne.platform.learneservice.domain.services.Exam;

import org.learne.platform.learneservice.domain.model.commands.Exam.CreateExamCommand;

public interface ExamCommandService {

    Long handle(CreateExamCommand command);
}
