package temp.spring.loader;

import org.springframework.boot.loader.JarLauncher;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.JarFileArchive;
import org.springframework.boot.loader.jar.JarFile;

import java.io.File;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author LW
 * @since 2021/4/8  11:08
 */
public class TestLoad extends JarLauncher {


    public TestLoad(Archive archive) {
        super(archive);
    }

    public static void main(String[] args) throws Exception {
        JarLauncher.main(args);
    }

}
