package filesorter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class SpringSorterApp {

    public static void main(String[] args)  {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppFactory.class);
        File directory = new File("/home/administrator/testDirectory/");
        RenameImagesUseCase renameImages = context.getBean(RenameImagesUseCase.class);
        renameImages.renameFiles(directory);

    }


}
