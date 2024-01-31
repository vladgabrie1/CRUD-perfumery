package be.kdg.programming3.individualproject.service;

import be.kdg.programming3.individualproject.util.PageVisit;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface SessionHistoryService {
    void updateSessionHistory(HttpSession session, String page);
    List<PageVisit> getSessionHistory(HttpSession session);

}
