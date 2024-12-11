package com.example.logreceive;

public class CommonUtils {
    private static final String EXETENSION_SEPERATOR = ".";
    private static final String TIME_SEPERATOR = "_";

    public static String fileNameCreate(String fileName){
        int extensionIndex = fileName.lastIndexOf(EXETENSION_SEPERATOR);
        String fileExtension = fileName.substring(extensionIndex);
        String uploadName = fileName.substring(0, extensionIndex);
        String now = String.valueOf(System.currentTimeMillis());

        StringBuilder sb = new StringBuilder(uploadName)
                .append(TIME_SEPERATOR)
                .append(now)
                .append(fileExtension);
        return sb.toString();
    }
}