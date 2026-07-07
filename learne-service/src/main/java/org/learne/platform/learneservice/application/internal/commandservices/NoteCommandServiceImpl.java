package org.learne.platform.learneservice.application.internal.commandservices;

import org.learne.platform.learneservice.domain.model.aggregates.Notes;
import org.learne.platform.learneservice.domain.model.commands.Note.CreateNotesCommand;
import org.learne.platform.learneservice.domain.services.notes.NotesCommandService;
import org.learne.platform.learneservice.infrastructure.persistence.jpa.NotesRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteCommandServiceImpl implements NotesCommandService {

    private final NotesRepository notesRepository;

    public NoteCommandServiceImpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public Long handle(CreateNotesCommand command) {

        var newNote = new Notes(command);
        try {
            notesRepository.save(newNote);
        }catch (RuntimeException e) {
            throw new IllegalArgumentException("An error occurred while saving note" + e.getMessage());
        }
        return newNote.getId();
    }
}
