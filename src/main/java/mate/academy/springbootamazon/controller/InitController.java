package mate.academy.springbootamazon.controller;

import java.io.File;
import java.io.IOException;
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

    public InitController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        File file = new ClassPathResource("Reviews.csv").getFile();
        CvsParser cvsParser = new CvsParser();
        Long start = System.nanoTime();
        List<ReviewEntity> reviewEntities = cvsParser.parseCvsToReviews(file);
        reviewRepository.saveAll(reviewEntities);
        System.out.println("=================================");
        System.out.println((System.nanoTime() - start) / 1000000000);
        System.out.println("=================================");
    }
}
