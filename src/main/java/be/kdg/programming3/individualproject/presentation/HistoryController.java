package be.kdg.programming3.individualproject.presentation;

import be.kdg.programming3.individualproject.service.SessionHistoryService;
import be.kdg.programming3.individualproject.util.PageVisit;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SessionHistoryService sessionHistoryService;

    public HistoryController(SessionHistoryService sessionHistoryService) {
        this.sessionHistoryService = sessionHistoryService;
    }

    @GetMapping("/history")
    public String history(HttpSession session, Model model){
        logger.info("GET request received for History Page");

        // Fetch session history from SessionHistoryService
        List<PageVisit> pageHistory = sessionHistoryService.getSessionHistory(session);

        // Add pageHistory to the model for rendering in the view
        model.addAttribute("pageHistory", pageHistory);

        // Log the number of page visits for debugging
        logger.debug("Number of page visits in the session: {}", pageHistory.size());

        return "history/history";
    }
}
