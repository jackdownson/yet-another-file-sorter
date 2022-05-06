package filesorter;

import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class File {

    private String filename;

    private Path filePath;

    public File() {

    }

    public File(String filename, Path filePath) {
        this.filename = filename;
        this.filePath = filePath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
}
