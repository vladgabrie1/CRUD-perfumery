package be.kdg.programming3.individualproject.presentation;

import be.kdg.programming3.individualproject.exceptions.PerfumePriceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(DataAccessException ex, Model model) {
        logger.error("Database error: {}", ex.getMessage());
        model.addAttribute("errorDetails", ex.getMessage());
        return "error/error_database";
    }

    @ExceptionHandler(PerfumePriceException.class)
    public String handlePerfumePriceException(Exception e, Model model) {
        logger.error(e.getMessage());
        model.addAttribute("errorDetails", e.getMessage());
        return "error/error_price";
    }
}
