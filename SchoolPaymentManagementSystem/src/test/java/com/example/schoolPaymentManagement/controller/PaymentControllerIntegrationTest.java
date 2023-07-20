package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.dto.FeeDto;
import com.example.schoolPaymentManagement.dto.PaymentDto;
import com.example.schoolPaymentManagement.model.Payment;
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
 * Handle all {@link Payment} tests.
 * </p>
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentControllerIntegrationTest {
    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Test retrieved Fee of a Payment.")
    void testRetrievePartOfPayment_whenPaymentExist_thenReturnStatus() {
        // Arrange Act
        ResponseEntity<PaymentDto> feePaymentResponse =
                testRestTemplate.exchange(
                        "/payments/FEE/1",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<PaymentDto>() {
                        }
                );

        // Assert
        Assertions.assertEquals(
                HttpStatus.OK,
                feePaymentResponse.getStatusCode(),
                "There isn't any Fee for this Payment."
        );
    }

    @Test
    @DisplayName("Can delete a Payment.")
    void testDeletePayment_whenPaymentHasPlaylist_returnsServerError() {
        // Arrange
        ResponseEntity<Void> resp = testRestTemplate.exchange(
                "/payments/1",
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
    @DisplayName("Payment can be created")
    void testCreatePaymentOfSpecificType_whenValidDetailsProvided_returnsPaymentDetails() throws JSONException {
        // Arrange
        JSONObject feePaymentDetailsRequestJson = new JSONObject();
        feePaymentDetailsRequestJson.put("paymentDate", "2022-12-08");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(feePaymentDetailsRequestJson.toString(), headers);

        // Act
        ResponseEntity<PaymentDto> createdFeePaymentDetailsEntity = testRestTemplate.postForEntity(
                "/payments/fee/8",
                request,
                PaymentDto.class
        );

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED, createdFeePaymentDetailsEntity.getStatusCode());
    }
}