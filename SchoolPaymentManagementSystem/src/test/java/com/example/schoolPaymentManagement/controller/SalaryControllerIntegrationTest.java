package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.SalaryDto;
import com.example.schoolPaymentManagement.dto.TeacherDto;
import com.example.schoolPaymentManagement.model.Salary;
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
 * Handle all {@link Salary} tests.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SalaryControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved teacher of a fee.")
    void testRetrieveTeacherOfSalary_whenSalaryExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<TeacherDto> feeResponse = testRestTemplate.exchange(
                "/salaries/1/teacher",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TeacherDto>() {
                }
        );

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                feeResponse.getStatusCode(),
                "There isn't any teacher for this Salary."
        );
    }

    @Test
    @DisplayName("Can't delete a Salary which has dependency(EX: Teacher).")
    void testDeleteSalary_whenSalaryHasDependency_returnsServerError() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/salaries/1",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class);

        // Assert
        Assertions.assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                resp.getStatusCode()
        );
    }

    @Test
    @DisplayName("Salary can be created")
    void testCreateSalary_whenValidDetailsProvided_returnsSalaryDetails() throws JSONException {
        // Arrange
        JSONObject feeDetailsRequestJson = new JSONObject();
        feeDetailsRequestJson.put("cost", 1000);
        feeDetailsRequestJson.put("deadLine", "2022-11-28");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(feeDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<SalaryDto> createdSalaryDetailsEntity = testRestTemplate.postForEntity(
                "/salaries/teacher/3/grade/1",
                request,
                SalaryDto.class
        );

        // Assert
        Assertions.assertEquals(
                HttpStatus.CREATED,
                createdSalaryDetailsEntity.getStatusCode()
        );
    }
}