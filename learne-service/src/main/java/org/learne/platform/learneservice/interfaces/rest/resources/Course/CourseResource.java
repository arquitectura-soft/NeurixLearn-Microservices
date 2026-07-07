package org.learne.platform.learneservice.interfaces.rest.resources.Course;

public record CourseResource(Long id, String title, String description, Long teacher_id,
                             String level, String duration, String prior_knowledge, String principal_image,
                             String url_video) {
}
