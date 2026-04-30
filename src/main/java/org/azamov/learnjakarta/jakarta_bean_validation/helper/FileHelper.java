package org.azamov.learnjakarta.jakarta_bean_validation.helper;

import org.azamov.learnjakarta.jakarta_bean_validation.entity.Upload;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileHelper {

    public static final String UPLOAD_DIR =
            System.getProperty("user.home") + File.separator + "library_uploads" + File.separator;

    public static Upload save(Part part) throws IOException {
        String originalName = extractFileName(part);
        String extension = extractExtension(originalName);
        String generatedName = UUID.randomUUID() + (extension.isEmpty() ? "" : "." + extension);

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        part.write(UPLOAD_DIR + generatedName);

        return Upload.childMethodBuilder()
                .originalName(originalName)
                .generatedName(generatedName)
                .mimeType(part.getContentType() != null ? part.getContentType() : "application/octet-stream")
                .size(part.getSize())
                .extension(extension)
                .build();
    }

    public static File getFile(String generatedName) {
        return new File(UPLOAD_DIR + generatedName);
    }

    private static String extractExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return dot >= 0 ? fileName.substring(dot + 1) : "";
    }

    private static String extractFileName(Part part) {
        String header = part.getHeader("content-disposition");
        if (header == null) return "file";
        for (String token : header.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "file";
    }
}
