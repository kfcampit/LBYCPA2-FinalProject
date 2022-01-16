package com.dlsu.lbycpa2finalproject.backend;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImageController {
    private Cloudinary cloudinary;

    public ImageController() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "de-la-salle-university-manila",
                "api_key", "598739196393413",
                "api_secret", "FDuM60sKgLsOUDqD2yr1ckE9Snc"));
    }

    public void saveImage(String filename, String quizID) throws IOException {
        File file = new File("src/main/assets/images/" + filename);
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "folder", quizID,
                "use_filename", true,
                "unique_filename", false));
    }

    public String loadImageURL(String filename, String quizID) {
        String src = cloudinary.url().imageTag(quizID + "/" + filename);
        char[] temp = src.toCharArray();
        List<Character> tempList = new ArrayList<>();

        for(int i = 10; i < temp.length - 3; i++) {
            tempList.add(temp[i]);
        }

        return tempList.toString().substring(1, 3 * tempList.size() - 1).replaceAll(", ", "");
    }
}
