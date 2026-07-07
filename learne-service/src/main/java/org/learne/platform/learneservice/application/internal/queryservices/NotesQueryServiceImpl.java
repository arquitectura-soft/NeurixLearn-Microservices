package org.learne.platform.learneservice.application.internal.queryservices;

import org.learne.platform.learneservice.domain.model.aggregates.Notes;
import org.learne.platform.learneservice.domain.model.queries.Notes.GetAllNotesQuery;
import org.learne.platform.learneservice.domain.model.queries.Notes.GetNotesByStudentIdQuery;
import org.learne.platform.learneservice.domain.services.notes.NotesQueryServices;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class NotesQueryServiceImpl implements NotesQueryServices {

    private final NotesRepository notesRepository;

    public NotesQueryServiceImpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public Optional<Notes> handle(GetNotesByStudentIdQuery query) {
        return notesRepository.findById(query.studentId());
    }

    @Override
    public List<Notes> handle(GetAllNotesQuery query) {
        return notesRepository.findAll();
    }
}
