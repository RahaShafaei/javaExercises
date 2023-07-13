package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.dto.TeacherDto;
import com.example.schoolPaymentManagement.model.Grade;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all {@link Grade} tests.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GradeControllerIntegrationTest {
    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved Students of a Grade.")
    void testRetrieveStudentsOfGrade_whenGradeExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<StudentDto>> musicResponse =
                testRestTemplate.exchange(
                        "/grades/1/students",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<StudentDto>>() {
                        }
                );
        List<StudentDto> studentDtos = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                studentDtos.size(),
                3,
                "Returned Students of a Grade numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Test retrieved Teachers of a Grade.")
    void testRetrieveTeachersOfGrade_whenGradeExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<TeacherDto>> musicResponse =
                testRestTemplate.exchange(
                        "/grades/1/teachers",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<TeacherDto>>() {
                        }
                );
        List<TeacherDto> teacherDtos = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                teacherDtos.size(),
                3,
                "Returned Teachers of a Grade numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Test retrieved Fees of a Grade.")
    void testRetrieveFeesOfGrade_whenGradeExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<FeeDto>> musicResponse =
                testRestTemplate.exchange(
                        "/grades/1/fees",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<FeeDto>>() {
                        }
                );
        List<FeeDto> feeDtos = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                feeDtos.size(),
                2,
                "Returned Fees of a Grade numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Test retrieved Salary of a Grade.")
    void testRetrieveSalaryOfGrade_whenGradeExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<SalaryDto>> musicResponse =
                testRestTemplate.exchange(
                        "/grades/1/salaries",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<SalaryDto>>() {
                        }
                );
        List<SalaryDto> salaryDtos = musicResponse.getBody();

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                musicResponse.getStatusCode()
        );
        Assertions.assertEquals(
                salaryDtos.size(),
                2,
                "Returned Salary of a Grade numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Can't delete a Grade.")
    void testDeleteGrade_whenGradeExist_returnsStatus() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/grades/1",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );

        // Assert
        Assertions.assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                resp.getStatusCode()
        );
    }

    @Test
    @DisplayName("Grade can be created")
    void testCreateGrade_whenValidDetailsProvided_returnsGradeDetails() throws JSONException {
        // Arrange
        JSONObject gradeDetailsRequestJson = new JSONObject();
        gradeDetailsRequestJson.put("name", "Test Grade");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(gradeDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<Grade> createdMusicDetailsEntity = testRestTemplate.postForEntity(
                "/grades",
                request,
                Grade.class
        );

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, createdMusicDetailsEntity.getStatusCode());
    }

}