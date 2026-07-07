package org.learne.platform.learneservice.domain.services.TutorialsReservated;

import org.learne.platform.learneservice.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learneservice.domain.model.queries.TutorialsReservated.GetAllTutorialsReservatedQuery;

import java.util.List;

public interface TutorialsReservatedQueryService {
    List<TutorialsReservated> handle(GetAllTutorialsReservatedQuery query);
}
