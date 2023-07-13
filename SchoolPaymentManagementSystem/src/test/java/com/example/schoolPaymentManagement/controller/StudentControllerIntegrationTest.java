package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.model.Student;
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
 * Handle all {@link Student} tests.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentControllerIntegrationTest {
    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved fees of students.")
    void testRetrieveFeesOfStudent_whenStudentExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<List<FeeDto>> musicResponse =
                testRestTemplate.exchange(
                        "/students/1/fees",
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
                1,
                "Returned student's fee numbers seems to be incorrect"
        );
    }

    @Test
    @DisplayName("Can't delete a Student which has dependency.")
    void testDeleteStudent_whenStudentExist_returnsStatus() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/students/1",
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
    @DisplayName("Student can be created")
    void testCreateStudent_whenValidDetailsProvided_returnsStudentDetails() throws JSONException {
        // Arrange
        JSONObject studentDetailsRequestJson = new JSONObject();
        studentDetailsRequestJson.put("firstName", "Test Student firstName");
        studentDetailsRequestJson.put("lastName", "Test Student lastName");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(studentDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<StudentDto> createdMusicDetailsEntity = testRestTemplate.postForEntity(
                "/students/grade/1",
                request,
                StudentDto.class
        );

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, createdMusicDetailsEntity.getStatusCode());
    }

}