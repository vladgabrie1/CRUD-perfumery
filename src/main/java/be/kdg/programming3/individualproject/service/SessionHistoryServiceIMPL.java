package be.kdg.programming3.individualproject.service;

import be.kdg.programming3.individualproject.util.PageVisit;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionHistoryServiceIMPL implements SessionHistoryService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void updateSessionHistory(HttpSession session, String page) {
        List<PageVisit> pageHistory = getSessionHistory(session);

        if (pageHistory == null) {
            logger.info("Creating session history...");
            pageHistory = new ArrayList<>();
        }

        pageHistory.add(new PageVisit(page, LocalDateTime.now()));
        session.setAttribute("pageHistory", pageHistory);
    }

    @Override
    public List<PageVisit> getSessionHistory(HttpSession session) {

        @SuppressWarnings("unchecked")
        List<PageVisit> history = (List<PageVisit>) session.getAttribute("pageHistory");
        return history;

    }
}
