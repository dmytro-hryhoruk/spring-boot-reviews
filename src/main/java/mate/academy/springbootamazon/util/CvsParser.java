package mate.academy.springbootamazon.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springbootamazon.entity.ReviewEntity;

public class CvsParser {

    private List<String> convertFileIntoList(File file) {
        try {
            return Files.lines(Paths.get(file.getAbsolutePath()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public List<ReviewEntity> parseCvsToReviews(File file) {
        List<String> list = convertFileIntoList(file);
        List<ReviewEntity> reviewEntities = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            String[] fields = list.get(i).split(",");
            ReviewEntity reviewEntity = new ReviewEntity();
            reviewEntity.setProductId(fields[1]);
            reviewEntity.setUserId(fields[2]);
            reviewEntity.setProfileName(fields[3]);
            reviewEntity.setText(fields[9]);
            reviewEntities.add(reviewEntity);
        }
        return reviewEntities;
    }
}
