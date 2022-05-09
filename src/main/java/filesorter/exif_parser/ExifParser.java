package filesorter.exif_parser;

import org.apache.commons.imaging.ImageReadException;

import java.io.File;
import java.io.IOException;

public interface ExifParser {

    String getTagName(File file);
}
