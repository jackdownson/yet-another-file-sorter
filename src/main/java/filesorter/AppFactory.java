package filesorter;


import filesorter.exif_parser.ExifOriginalDateParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppFactory {

    @Bean
    public FileLogCollector createFileLogCollector() {
        return new FileLogCollector();
    }

    @Bean
    public ExifOriginalDateParser createExifOriginalDateParser() {
        return new ExifOriginalDateParser();
    }
}
