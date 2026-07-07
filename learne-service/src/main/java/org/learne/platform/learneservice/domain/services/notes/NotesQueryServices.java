package org.learne.platform.learneservice.domain.services.notes;

import org.learne.platform.learneservice.domain.model.aggregates.Notes;
import org.learne.platform.learneservice.domain.model.queries.Notes.GetAllNotesQuery;
import org.learne.platform.learneservice.domain.model.queries.Notes.GetNotesByStudentIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotesQueryServices {
    Optional<Notes> handle(GetNotesByStudentIdQuery query);
    List<Notes> handle(GetAllNotesQuery query);
}
