package be.kdg.programming3.individualproject.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
@Component
public class View {
    private Scanner scanner;
    private Presenter presenter;

    public View() {
    }

    @Autowired
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void show(){
        while(true){
            try {
                System.out.println("What would you like to do?");
                System.out.println("==========================");
                System.out.println("0) Quit");
                System.out.println("1) Show all perfumes");
                System.out.println("2) Show perfumes by brand or/and release year");
                System.out.println("3) Show all notes");
                System.out.println("4) Show notes by name");
                System.out.print("Choice (0-4): ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        System.out.println("Goodbye...");
                        System.exit(0);
                    case 1:
                        presenter.showAllPerfumes();
                        break;
                    case 2:
                        presenter.showPerfumesByBrandOrYear();
                        break;
                    case 3:
                        presenter.showAllNotes();
                        break;
                    case 4:
                        presenter.showNotesByName();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                scanner.nextLine();
            }
        }
    }


}
