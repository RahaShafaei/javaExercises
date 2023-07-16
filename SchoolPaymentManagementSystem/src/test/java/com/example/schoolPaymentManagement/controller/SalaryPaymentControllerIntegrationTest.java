package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.SalaryDto;
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
 * Handle all {@link SalaryPayment} tests.
 * </p>
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SalaryPaymentControllerIntegrationTest {
    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved Salary of a SalaryPayment.")
    void testRetrieveSalaryOfSalaryPayment_whenSalaryPaymentExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<SalaryDto> salaryPaymentResponse =
                testRestTemplate.exchange(
                        "/salaryPayments/1/salary",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<SalaryDto>() {
                        }
                );

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                salaryPaymentResponse.getStatusCode(),
                "There isn't any Salary for this SalaryPayment."
        );
    }

    @Test
    @DisplayName("Can delete a SalaryPayment.")
    void testDeleteSalaryPayment_whenSalaryPaymentHasSalary_returnsServerError() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/salaryPayments/1",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                resp.getStatusCode()
        );
    }

    @Test
    @DisplayName("SalaryPayment can be created")
    void testCreateSalaryPaymentOfSalary_whenValidDetailsProvided_returnsSalaryPaymentDetails() throws JSONException {
        // Arrange
        JSONObject salaryPaymentDetailsRequestJson = new JSONObject();
        salaryPaymentDetailsRequestJson.put("paymentDate", "2022-11-28");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(salaryPaymentDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<SalaryPaymentDto> createdSalaryPaymentDetailsEntity = testRestTemplate.postForEntity(
                "/salaryPayments/salary/1",
                request,
                SalaryPaymentDto.class
        );

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, createdSalaryPaymentDetailsEntity.getStatusCode());
    }
}