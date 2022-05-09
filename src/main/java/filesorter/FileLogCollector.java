package filesorter;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class FileLogCollector {

    private final List<File> renamed = new ArrayList<>();
    private final List<File> notRenamed = new ArrayList<>();
    private final List<File> noMetadata = new ArrayList<>();
    private final List<File> notJpegMetadata = new ArrayList<>();
    private final List<File> errorWhileRenaming = new ArrayList<>();



}
