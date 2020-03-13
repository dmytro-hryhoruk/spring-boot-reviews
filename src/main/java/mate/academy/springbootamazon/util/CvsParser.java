package mate.academy.springbootamazon.util;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import mate.academy.springbootamazon.entity.ReviewEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CvsParser {


    public List<ReviewEntity> parseCsvToReviews(File file) throws IOException {
        List<ReviewEntity> reviewEntities = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader())
        ) {
            for (CSVRecord csvRecord : csvParser) {
                ReviewEntity entity = new ReviewEntity();
                entity.setId(Long.valueOf(csvRecord.get("Id")));
                entity.setProductId(csvRecord.get("ProductId"));
                entity.setUserId(csvRecord.get("UserId"));
                entity.setText(csvRecord.get("Text"));
                entity.setProfileName(csvRecord.get("ProfileName"));
                reviewEntities.add(entity);
            }
        }
        return reviewEntities;
    }
}
