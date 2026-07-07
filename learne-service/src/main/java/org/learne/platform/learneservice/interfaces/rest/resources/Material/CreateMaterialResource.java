package org.learne.platform.learneservice.interfaces.rest.resources.Material;

public record CreateMaterialResource( Long course_id,
                                     String title,String format,String link) {
}
