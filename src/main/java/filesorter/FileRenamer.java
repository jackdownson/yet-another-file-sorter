package filesorter;

import filesorter.exif_parser.ExifOriginalDateParser;
import filesorter.exif_parser.ExifParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
@Component
public class FileRenamer {

    private final ExifParser exifParser;
    private final FileLogCollector fileLogCollector;


    @Autowired
    public FileRenamer(@Qualifier("exifDate") ExifParser exifParser,
                       FileLogCollector fileLogCollector) {
        this.exifParser = exifParser;
        this.fileLogCollector = fileLogCollector;
    }



    public  void renameFilesRecursively(File directory)  {
        List<File> files = List.of(Objects.requireNonNull(directory.listFiles()));
        for (File file : files) {
            if (file.isDirectory()) {
                renameFilesRecursively(file);
            } else {
                renameFile(file);


            }
        }
    }

    private String renameFile(File file) {
        String tagName = exifParser.getTagName(file);
        if (tagName.isEmpty()){
            System.out.println("Name to apply: " + file.toString());
            fileLogCollector.getNotRenamed().add(file);
            return file.toString();
        }

        String newName = buildNewName(file, tagName);

        try {
            Path moved = Files.move(file.toPath(), Path.of(newName));
            fileLogCollector.getRenamed().add(moved.toFile());
        } catch (IOException e) {
            System.out.println("renaming failed");
            fileLogCollector.getErrorWhileRenaming().add(file);
        }
        System.out.println("Name to apply: " + newName);
        return newName;
    }


    private String changeDatePattern(String tagDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss")
                .withLocale(Locale.ENGLISH);
        return LocalDateTime.parse(tagDate, formatter)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }

    private String buildNewName(File file, String tagName) {
        String modifiedTagName = changeDatePattern(tagName);
        StringBuilder sb = new StringBuilder();
        StringBuilder newName = sb.append(file.getParent()).append("/").append(modifiedTagName).append("_").append(file.getName());
        return newName.toString();

    }



    public void measureTime(File file, String tagName){
        StopWatch watch = new StopWatch();
        watch.start();
        buildNewName(file, tagName);
        watch.stop();
        System.out.println("buildNewName " + watch.getLastTaskTimeNanos()/1000);

    }

}
