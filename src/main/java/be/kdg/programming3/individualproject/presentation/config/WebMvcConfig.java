package be.kdg.programming3.individualproject.presentation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final BrandConverter brandConverter;
    private final NoteConverter noteConverter;

    public WebMvcConfig(BrandConverter brandConverter, NoteConverter noteConverter) {
        this.brandConverter = brandConverter;

        this.noteConverter = noteConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(brandConverter);
        registry.addConverter(noteConverter);
    }
}
