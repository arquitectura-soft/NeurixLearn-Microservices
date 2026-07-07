package org.learne.platform.learneservice.application.internal.queryservices;

import org.learne.platform.learneservice.domain.model.aggregates.Exam;
import org.learne.platform.learneservice.domain.model.queries.Exam.GetAllExamsQuery;
import org.learne.platform.learneservice.domain.model.queries.Exam.GetExamByIdQuery;
import org.learne.platform.learneservice.domain.services.Exam.ExamQueryService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamQueryServiceImpl implements ExamQueryService {

    private final ExamRepository examRepository;

    public ExamQueryServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }


    @Override
    public Optional<Exam> handle(GetExamByIdQuery query) {
        return examRepository.findById(query.id());
    }

    @Override
    public List<Exam> handle(GetAllExamsQuery query) {
        return examRepository.findAll();
    }
}
