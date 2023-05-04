package com.example.backend.service.impl;

import com.example.backend.domain.Author;
import com.example.backend.domain.Category;
import com.example.backend.domain.Practice;
import com.example.backend.domain.Team;
import com.example.backend.dto.PracticeDto;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.repository.TeamRepository;
import com.example.backend.service.PracticeService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PracticeServiceImplTest {
    @Autowired
    private PracticeService practiceService;

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private static PostgreSQLContainer<?> postgreSQLContainer;

    @BeforeAll
    public static void setUpContainer() {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test")
                .withExposedPorts(5432);
        postgreSQLContainer.start();
        System.setProperty("spring.datasource.url",
                format("jdbc:postgresql://%s:%d/test",
                        postgreSQLContainer.getContainerIpAddress(),
                        postgreSQLContainer.getMappedPort(5432))
        );

    }

    @BeforeEach
    void init() {
        categoryRepository.deleteAllInBatch();
        authorRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
        practiceRepository.deleteAllInBatch();
    }

    @AfterAll
    public static void stopContainer() {
        postgreSQLContainer.stop();
    }

    @Test
    public void testSavePractice() {
        PracticeDto practice = createPractice();
        PracticeDto savedPractice = practiceService.savePractice(practice);
        assertNotNull(savedPractice.getId());
    }

    @Test
    public void testGetPracticeById() {
        PracticeDto practice = createPractice();
        PracticeDto practiceDto = practiceService.savePractice(practice);
        Practice retrievedPractice = practiceService.getPracticeById(practiceDto.getId());
        assertNotNull(retrievedPractice);
        assertEquals(practice.getName(), retrievedPractice.getName());
    }

    private PracticeDto createPractice() {
        Author author = authorRepository.save(Author.builder().name("Test author").build());
        Category category = categoryRepository.save(Category.builder().name(" Test category").build());
        Team team = teamRepository.save(Team.builder().name("Test team").build());
        return PracticeDto.builder()
                .name("Test Practice")
                .description("Test Description")
                .documentLink("Test Document Link")
                .rating(5)
                .category(category.getName())
                .team(team.getName())
                .author(author.getName())
                .build();
    }
}