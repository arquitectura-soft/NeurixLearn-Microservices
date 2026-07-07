package org.learne.platform.learneservice.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.learne.platform.learneservice.domain.model.commands.Note.CreateNotesCommand;
import org.learne.platform.learneservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@Setter
public class Notes extends AuditableAbstractAggregateRoot<Notes> {

    @JoinColumn(name = "student_id", nullable = false)
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @Column(nullable = false)
    private Float note;


    public Notes() {}

    public Notes(CreateNotesCommand command) {
      this.studentId= command.studentId();
      this.exam=new Exam(command.examId());
      this.note=command.note();
    }

    public Notes( Long id) {
        this.setId(id);
    }
}
