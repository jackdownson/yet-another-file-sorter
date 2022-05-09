package filesorter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
public class RenameImagesUseCase {

    private final FileRenamer fileRenamer;

  public void renameFiles(File directory) {
      fileRenamer.renameFilesRecursively(directory);
  }


}
