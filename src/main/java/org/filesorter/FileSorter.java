package org.filesorter;

import java.io.IOException;
import java.nio.file.*;

public abstract class FileSorter {
    protected abstract Path getSourceDir();
    protected abstract String[] getFileExtensions();
    protected abstract Path getTargetDir();

    public void sort() {
        Path sourceDir = getSourceDir();

        for (String extension : getFileExtensions()) {
            String pattern = "*." + extension;

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir, pattern)) {
                for (Path entry : stream) {
                    moveFile(entry);
                }
            } catch (IOException e) {
                System.err.println("Error accessing the directory: " + e.getMessage());
            }
        }
    }

    private void moveFile(Path entry) {
        Path targetPath = getTargetDir();

        try {
            Files.move(entry, targetPath.resolve(entry.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error moving file: " + entry.getFileName() + " - " + e.getMessage());
        }
    }
}
