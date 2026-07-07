package org.learne.platform.learneservice.interfaces.rest.transform.Notes;

import org.learne.platform.learneservice.domain.model.aggregates.Notes;
import org.learne.platform.learneservice.interfaces.rest.resources.Notes.NoteResource;

public class NotesResourceFromEntityAssembler {
    public static NoteResource ToResourceFromEntity(Notes entity) {
        return new NoteResource(entity.getId(),entity.getStudentId(),
                entity.getExam().getId(),entity.getNote());
    }
}
