package filesorter.exif_parser;

import filesorter.FileLogCollector;
import filesorter.util.StringChanger;
import lombok.RequiredArgsConstructor;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.springframework.stereotype.Component;

import java.io.File;

@Component("exifDate")
@RequiredArgsConstructor
public class ExifOriginalDateParser implements ExifParser{

    private final FileLogCollector fileLogCollector;

    public String getTagName(File file)  {
        final ImageMetadata metadata;
        String result = "";

        try {

            metadata = Imaging.getMetadata(file);

            if (metadata == null) {
                fileLogCollector.getNoMetadata().add(file);
                return result;
            }

            if (!(metadata instanceof JpegImageMetadata)) {
                fileLogCollector.getNotJpegMetadata().add(file);
                return result;
//                throw new RuntimeException("Can not parse Metadata from file: " + file);
            }

            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;


//        printTagValue(jpegMetadata,
//                ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
            result = StringChanger.cleanStringFrom(getTagValue(jpegMetadata,
                    ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL), "'");
            System.out.println(result);
            return result;
        } catch (Exception ignored) {

        }


        return result;
    }

    private String getTagValue(final JpegImageMetadata jpegMetadata,
                                      final TagInfo tagInfo) {
        final TiffField field = jpegMetadata.findEXIFValueWithExactMatch(tagInfo);
        try {

            if (field == null) {
                System.out.println(tagInfo.name + ": " + "Not Found.");
                throw new RuntimeException("tag info == null");
            }
            return field.getValueDescription();
        } catch (RuntimeException exc) {
            System.out.println(exc.getMessage());
        }
            return "";
    }

    private void printTagValue(final JpegImageMetadata jpegMetadata,
                               final TagInfo tagInfo) {
        final TiffField field = jpegMetadata.findEXIFValueWithExactMatch(tagInfo);
        if (field == null) {
            System.out.println(tagInfo.name + ": " + "Not Found.");
            throw new RuntimeException("tag info == null");
        }
        System.out.println(field.getValueDescription());

    }
}
