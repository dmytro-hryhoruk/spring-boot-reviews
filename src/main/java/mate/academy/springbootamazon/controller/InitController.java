package mate.academy.springbootamazon.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.PostConstruct;
import mate.academy.springbootamazon.entity.ReviewEntity;
import mate.academy.springbootamazon.repository.ReviewRepository;
import mate.academy.springbootamazon.util.CvsParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;


@Component
public class InitController {
    private ReviewRepository reviewRepository;
    private CvsParser cvsParser;

    public InitController(ReviewRepository reviewRepository, CvsParser cvsParser) {
        this.reviewRepository = reviewRepository;
        this.cvsParser = cvsParser;
    }

    @PostConstruct
    public void init() throws IOException {
        File file = new ClassPathResource("Reviews.csv").getFile();
        Long start = System.nanoTime();
        List<ReviewEntity> reviewEntities = cvsParser.parseCsvToReviews(file);
        reviewRepository.saveAll(reviewEntities);
        System.out.println(reviewEntities.get(reviewEntities.size()-1).getId());
        System.out.println("=================================");
        System.out.println((System.nanoTime() - start) / 1000000000);
        System.out.println("=================================");
    }
}
