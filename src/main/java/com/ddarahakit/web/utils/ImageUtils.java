package com.ddarahakit.web.utils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ImageUtils {
    public static String makeImagePath(String originalName) {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        // 클라우드에 업로드할 것이기 떄문에 처리 안함
//        String folderPath = str.replace("/", File.separator);
        String folderPath = str;
        String uuid = UUID.randomUUID().toString();

//        return folderPath + File.separator + uuid + "_" + originalName;
        return folderPath + "/" + uuid + "_" + originalName;

    }
}
