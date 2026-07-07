package org.learne.platform.learneservice.interfaces.rest.resources.Material;

public record MaterialResource(Long id,Long course_id,
                               String title,String format,String link) {
}
