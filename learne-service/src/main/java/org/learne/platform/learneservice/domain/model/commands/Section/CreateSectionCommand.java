package org.learne.platform.learneservice.domain.model.commands.Section;

public record CreateSectionCommand(Long unitId, String title, String description, String urlVideo) {
}
