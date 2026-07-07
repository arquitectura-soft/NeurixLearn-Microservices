package org.learne.platform.learneservice.interfaces.rest.transform.Notes;

import org.learne.platform.learneservice.domain.model.commands.Note.CreateNotesCommand;
import org.learne.platform.learneservice.interfaces.rest.resources.Notes.CreateNotesResource;

public class CreateNotesCommandFromResourceAssembler {
    public static CreateNotesCommand toCommandFromResource(CreateNotesResource resource) {
        return  new CreateNotesCommand(resource.student_id(),resource.exam_id(),resource.note());
    }

}