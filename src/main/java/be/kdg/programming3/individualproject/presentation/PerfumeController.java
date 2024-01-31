package be.kdg.programming3.individualproject.presentation;

import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.presentation.viewmodels.PerfumeViewModel;
import be.kdg.programming3.individualproject.service.BrandService;
import be.kdg.programming3.individualproject.service.NoteService;
import be.kdg.programming3.individualproject.service.PerfumeService;
import be.kdg.programming3.individualproject.service.SessionHistoryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping({"/perfume", "", "/"})
public class PerfumeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PerfumeService perfumeService;
    private final BrandService brandService;
    private final SessionHistoryService historyService;
    private final NoteService noteService;

    public PerfumeController(PerfumeService perfumeService, BrandService brandService, SessionHistoryService historyService, NoteService noteService) {
        this.perfumeService = perfumeService;
        this.brandService = brandService;
        this.historyService = historyService;
        this.noteService = noteService;
    }

    @GetMapping({"", "/list", "/"})
    public String showPerfumeList(Model model, HttpSession session,
                                  @RequestParam(required = false) String filterReleaseDate,
                                  @RequestParam(required = false) String priceRange) {
        logger.info("Request for perfume view");
        historyService.updateSessionHistory(session, "/perfume/list");

        List<Perfume> perfumeList;

        // Filtering perfumes based on parameters
        if (filterReleaseDate != null && !filterReleaseDate.isEmpty()) {
            LocalDate releaseDate = LocalDate.parse(filterReleaseDate);
            perfumeList = perfumeService.getPerfumesByPriceRangeAndReleaseDateAfter(0.0, 10000.1, releaseDate);
        } else if (priceRange != null && !priceRange.isEmpty()) {
            String[] priceRangeArray = priceRange.split(";");
            if (priceRangeArray.length == 2) {
                double minPrice = Double.parseDouble(priceRangeArray[0]);
                double maxPrice = Double.parseDouble(priceRangeArray[1]);
                perfumeList = perfumeService.getPerfumesByPriceRange(minPrice, maxPrice);
            } else {
                perfumeList = perfumeService.getAllPerfumes();
            }
        } else {
            perfumeList = perfumeService.getAllPerfumes();
        }

        model.addAttribute("perfumes", perfumeList);
        logger.debug("Perfume list retrieved successfully. Number of perfumes: {}", perfumeList.size());
        return "perfume/list";
    }

    @GetMapping("/add")
    public String addPerfume(Model model, HttpSession session){
        logger.info("Request for adding a perfume");
        historyService.updateSessionHistory(session, "/perfume/add");

        // Preparing ViewModel for adding a new perfume
        PerfumeViewModel perfumeViewModel = new PerfumeViewModel();
        perfumeViewModel.setBrands(brandService.getAllBrands());
        perfumeViewModel.setNotes(noteService.getAllNotes());

        model.addAttribute("perfumeViewModel", perfumeViewModel);
        return "perfume/add";
    }

    @PostMapping("/add")
    public String processPerfume(@Valid @ModelAttribute("perfumeViewModel") PerfumeViewModel perfumeViewModel,
                                 BindingResult bindingResult,
                                 @RequestParam("photo") MultipartFile photoFile) {
        logger.debug("Processing perfume: {}", perfumeViewModel);

        // Handling validation errors
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.error(error.toString()));
            perfumeViewModel.setBrands(brandService.getAllBrands());
            perfumeViewModel.setNotes(noteService.getAllNotes());
            return "perfume/add";
        } else {
            // Creating and saving a new perfume
            Perfume newPerfume = new Perfume(
                    perfumeViewModel.getName(),
                    perfumeViewModel.getBrand(),
                    perfumeViewModel.getReleaseDate(),
                    perfumeViewModel.getType(),
                    perfumeViewModel.getPrice());
            newPerfume.setNotes(perfumeViewModel.getSelectedNotes());
            perfumeService.savePerfume(newPerfume);

            //Saving the photo
            if (!photoFile.isEmpty()) {
                try {
                    String fileName = perfumeViewModel.getName().toLowerCase() + ".jpg";
                    byte[] bytes = photoFile.getBytes();
                    Path path = Paths.get("src/main/resources/static/img/perfumes-img/" + fileName);
                    Files.write(path, bytes);
                } catch (IOException e) {
                    // Handle file processing exception
                    logger.error(Arrays.toString(e.getStackTrace()));
                }
            }


            logger.info("Perfume added successfully");
            return "redirect:/perfume/list";
        }
    }


    @GetMapping("/details")
    public String itemDetails(@RequestParam("perfumeId")int id, Model model, HttpSession session){
        logger.info("Request for Perfume Details");
        historyService.updateSessionHistory(session, "/perfume/details?perfumeId=" + id);

        // Fetching perfume details
        Perfume perfume = perfumeService.getPerfumeById(id);
        model.addAttribute("perfume", perfume);

        logger.debug("Perfume details retrieved successfully for Perfume ID: {}", id);
        return "/perfume/details";
    }

    @PostMapping("/delete")
    public String deletePerfume(@RequestParam("entityId") int id){
        logger.warn("Request to delete Perfume ID: {}", id);
        perfumeService.deletePerfume(id);
        return "redirect:/perfume/list";
    }
}
