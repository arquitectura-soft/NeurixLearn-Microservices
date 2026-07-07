package org.learne.platform.learneservice.domain.services.notes;

import org.learne.platform.learneservice.domain.model.commands.Note.CreateNotesCommand;


public interface NotesCommandService {

    Long handle(CreateNotesCommand command);
}
