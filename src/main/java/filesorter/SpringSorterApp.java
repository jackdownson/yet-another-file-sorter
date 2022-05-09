package filesorter;

import filesorter.exif_parser.ExifOriginalDateParser;
import filesorter.exif_parser.ExifParser;

import java.io.File;

public class SpringSorterApp {

    public static void main(String[] args)  {


        File directory = new File("/home/administrator/testDirectory/");

        ExifParser exifParser  = new ExifOriginalDateParser(new FileLogCollector());



        FileRenamer fr = new FileRenamer(new ExifOriginalDateParser(new FileRenamer(), new FileLogCollector()));
        fr.renameFilesRecursively(directory);

    }


}
