package com.example.backend.service.impl;

import com.example.backend.domain.Author;
import com.example.backend.domain.Category;
import com.example.backend.domain.Practice;
import com.example.backend.domain.Team;
import com.example.backend.dto.PracticeDto;
import com.example.backend.dto.PracticeSearchCriteria;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.repository.TeamRepository;
import com.example.backend.service.PracticeService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;

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

    @Test
    void searchPractices_ShouldReturnMatchingPractices_WhenSearchCriteriaProvided() {
        // given
        Author author = getAuthor();
        Category category = getCategory();
        Team team = getTeam();
        Practice practice1 = Practice.builder()
                .name("Practice 1")
                .description("Description 1")
                .documentLink("Link 1")
                .rating(1)
                .author(author)
                .category(category)
                .team(team)
                .build();
        Practice practice2 = Practice.builder()
                .name("Practice 2")
                .description("Description 2")
                .documentLink("Link 2")
                .rating(2)
                .author(author)
                .category(category)
                .team(team)
                .build();
        Practice practice3 = Practice.builder()
                .name("Practice 3")
                .description("Description 3")
                .documentLink("Link 3")
                .rating(3)
                .author(author)
                .category(category)
                .team(team)
                .build();
        practiceRepository.saveAll(List.of(practice1, practice2, practice3));

        PracticeSearchCriteria searchCriteria = new PracticeSearchCriteria();
        searchCriteria.setName("Practice 2");
        searchCriteria.setSortByRatingDirection(Sort.Direction.ASC);

        // when
        List<Practice> result = practiceService.searchPractices(searchCriteria);

        // then
        assertEquals(1, result.size());
        assertEquals(practice2, result.get(0));
    }

    @Test
    public void testRatePractice() {
        // Создаем тестовую практику
        Practice practice = Practice.builder()
                .name("Test Practice")
                .description("Test description")
                .documentLink("http://test.com")
                .rating(0)
                .author(getAuthor())
                .category(getCategory())
                .team(getTeam())
                .build();
        practice = practiceRepository.save(practice);

        // Вызываем метод ratePractice для тестовой практики
        Practice ratedPractice = practiceService.ratePractice(practice.getId());

        // Проверяем, что рейтинг практики был увеличен на единицу
        assertEquals(practice.getRating() + 1, ratedPractice.getRating());

        // Убеждаемся, что изменения были сохранены в базе данных
        Optional<Practice> updatedPractice = practiceRepository.findById(practice.getId());
        assertNotNull(updatedPractice);
        assertEquals(practice.getRating() + 1, updatedPractice.get().getRating());
    }

    private Team getTeam() {
        return teamRepository.save(Team.builder().name("Test team").build());
    }

    private Category getCategory() {
        return categoryRepository.save(Category.builder().name(" Test category").build());
    }

    @NotNull
    private Author getAuthor() {
        return authorRepository.save(Author.builder().name("Test author").build());
    }

    private PracticeDto createPractice() {
        return PracticeDto.builder()
                .name("Test Practice")
                .description("Test Description")
                .documentLink("Test Document Link")
                .rating(5)
                .category(getCategory().getName())
                .team(getTeam().getName())
                .author(getAuthor().getName())
                .build();
    }
}