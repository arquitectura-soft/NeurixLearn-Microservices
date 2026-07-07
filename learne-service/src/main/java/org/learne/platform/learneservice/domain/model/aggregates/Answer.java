package org.learne.platform.learneservice.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.learne.platform.learneservice.domain.model.commands.Answer.CreateAnswerCommand;
import org.learne.platform.learneservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class Answer extends AuditableAbstractAggregateRoot<Answer> {

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private boolean isCorrect;

    @Column(nullable = false)
    private String description;

    public Answer() {}

    public Answer(CreateAnswerCommand command) {
        this.question = new Question(command.questionId());
        this.isCorrect = command.isCorrect();
        this.description = command.description();
    }
}
