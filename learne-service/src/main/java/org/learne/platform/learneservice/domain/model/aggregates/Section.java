package org.learne.platform.learneservice.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.learne.platform.learneservice.domain.model.commands.Section.CreateSectionCommand;
import org.learne.platform.learneservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
@Setter
public class Section extends AuditableAbstractAggregateRoot<Section> {
    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String url_video;

    public Section() {}

    public Section(CreateSectionCommand command) {
        this.unit = new Unit(command.unitId());
        this.title = command.title();
        this.description = command.description();
        this.url_video = command.urlVideo();
    }
}
