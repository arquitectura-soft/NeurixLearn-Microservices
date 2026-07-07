package org.learne.platform.learneservice.interfaces.rest.resources.Notes;

public record CreateNotesResource(Long student_id, Long exam_id, Float note) {
}
