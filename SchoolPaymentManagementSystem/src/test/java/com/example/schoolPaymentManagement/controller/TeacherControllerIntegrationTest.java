package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.TeacherDto;
import com.example.schoolPaymentManagement.model.Teacher;
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
 * Handle all {@link Teacher} tests.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeacherControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved salaries of teachers.")
    void testRetrieveMusicsOfTeacher_whenTeacherExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<SalaryDto>> musicResponse =
                testRestTemplate.exchange(
                        "/teachers/1/salaries",
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
                1,
                "Returned teacher's salary numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Can,t delete a Teacher which has dependency.")
    void testDeleteTeacher_whenTeacherExist_returnsStatus() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/teachers/1",
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
    @DisplayName("Teacher can be created")
    void testCreateTeacher_whenValidDetailsProvided_returnsTeacherDetails() throws JSONException {
        // Arrange
        JSONObject teacherDetailsRequestJson = new JSONObject();
        teacherDetailsRequestJson.put("firstName", "Test Teacher firstName");
        teacherDetailsRequestJson.put("lastName", "Test Teacher lastName");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(teacherDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<TeacherDto> createdMusicDetailsEntity = testRestTemplate.postForEntity(
                "/teachers/grade/1",
                request,
                TeacherDto.class
        );

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, createdMusicDetailsEntity.getStatusCode());
    }
}