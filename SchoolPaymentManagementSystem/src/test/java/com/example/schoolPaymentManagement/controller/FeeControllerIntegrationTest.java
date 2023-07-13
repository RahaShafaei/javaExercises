package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.StudentDto;
import com.example.schoolPaymentManagement.model.Fee;
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
 * Handle all {@link Fee} tests.
 * </p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeeControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved student of a fee.")
    void testRetrieveStudentOfFee_whenFeeExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<StudentDto> feeResponse = testRestTemplate.exchange(
                "/fees/1/student",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StudentDto>() {
                }
        );

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                feeResponse.getStatusCode(),
                "There isn't any student for this Fee."
        );
    }

    @Test
    @DisplayName("Can't delete a Fee which has dependency(EX: Student).")
    void testDeleteFee_whenFeeHasDependency_returnsServerError() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/fees/1",
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
    @DisplayName("Fee can be created")
    void testCreateFee_whenValidDetailsProvided_returnsFeeDetails() throws JSONException {
        // Arrange
        JSONObject feeDetailsRequestJson = new JSONObject();
        feeDetailsRequestJson.put("cost", 10000);
        feeDetailsRequestJson.put("deadLine", "2022-11-28");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(feeDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<FeeDto> createdFeeDetailsEntity = testRestTemplate.postForEntity(
                "/fees/student/3/grade/1",
                request,
                FeeDto.class
        );

        // Assert
        Assertions.assertEquals(
                HttpStatus.CREATED,
                createdFeeDetailsEntity.getStatusCode()
        );
    }
}