package com.example.schoolPaymentManagement.controller;

import com.example.schoolPaymentManagement.model.Student;
import com.example.schoolPaymentManagement.model.Teacher;
import com.example.schoolPaymentManagement.service.NotifyingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Raha
 * @since 2023-07-10
 *
 * <p>
 * Handle all notifying interactions.
 * </p>
 */
@AllArgsConstructor
@RestController
public class NotifyingController {
    NotifyingService notifyingService;

    /**
     * Notifying {@link Student}s or {@link Teacher}s that their payment has been made.
     */
    @GetMapping("/notifying/done")
    public void notifyingDone() {
        notifyingService.notifyingDone();
    }

    /**
     * Notifying {@link Student}s or {@link Teacher}s that their payment has not been made.
     */
    @GetMapping("/notifying/notDone")
    public void notifyingNotDone() {
        notifyingService.notifyingNotDone();
    }
}
