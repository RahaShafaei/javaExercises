INSERT INTO GRADE (GRADE_ID, NAME)
VALUES (1, 'ONE'),
       (2, 'TWO'),
       (3, 'THREE'),
       (4, 'FOUR'),
       (5, 'FIVE');
-- ==============================================
INSERT INTO STUDENT (STUDENT_ID, FIRST_NAME, LAST_NAME)
VALUES (1, 'Heaven', 'Gillan'),
       (2, 'Aline', 'Jones'),
       (3, 'Alex', 'Silva'),
       (4, 'John ', 'Darnell'),
       (5, 'Kevin', 'Robins'),
       (6, 'Jordan', 'Freeman'),
       (7, 'Denzel', 'Jones'),
       (8, 'Morgan', 'Ford'),
       (9, 'Tom', 'Robins');
-- ==============================================
INSERT INTO TEACHER (TEACHER_ID, FIRST_NAME, LAST_NAME)
VALUES (1, 'Jordan', 'Johnson'),
       (2, 'Dwayne', 'White'),
       (3, 'Morgan', 'Silva'),
       (4, 'Tom', 'Freeman'),
       (5, 'Denzel', 'Ford'),
       (6, 'Alex', 'Heaven'),
       (7, 'John', 'Gillan'),
       (8, 'Kevin', 'Darnell'),
       (9, 'Sara', 'Robins');
-- ==============================================
INSERT INTO FEE (FEE_ID, COST, DEAD_LINE, GRADE_GRADE_ID, STUDENT_STUDENT_ID)
VALUES (1, 1000, '2022-11-28', 1, 1),
       (2, 1000, '2022-11-28', 1, 2),
       (3, 1500, '2022-12-31', 2, 3),
       (4, 1500, '2022-12-31', 2, 4),
       (5, 1500, '2022-12-31', 2, 5),
       (6, 1700, '2022-12-04', 3, 6),
       (7, 1700, '2022-12-04', 3, 7),
       (8, 1700, '2022-12-04', 3, 8),
       (9, 1700, '2022-12-04', 3, 9);
-- ==============================================
INSERT INTO FEE_PAYMENT (FEE_PAYMENT_ID, FEE_FEE_ID, PAYMENT_DATE)
VALUES (1, 1, '2022-11-26'),
       (2, 2, '2022-11-28'),
       (3, 3, '2022-12-30'),
       (6, 6, '2022-12-01'),
       (7, 9, '2022-12-03');
-- ==============================================
INSERT INTO SALARY (SALARY_ID, COST, DEAD_LINE, GRADE_GRADE_ID, TEACHER_TEACHER_ID)
VALUES (1, 1000, '2022-11-28', 1, 1),
       (2, 1000, '2022-11-28', 1, 2),
       (3, 1500, '2022-12-31', 2, 3),
       (4, 1500, '2022-12-31', 2, 4),
       (5, 1500, '2022-12-31', 2, 5),
       (6, 1700, '2022-12-04', 3, 6),
       (7, 1700, '2022-12-04', 3, 7),
       (8, 1700, '2022-12-04', 3, 8),
       (9, 1700, '2022-12-04', 3, 9);
-- ==============================================
INSERT INTO SALARY_PAYMENT (SALARY_PAYMENT_ID, SALARY_SALARY_ID, PAYMENT_DATE)
VALUES (1, 1, '2022-11-26'),
       (2, 2, '2022-11-28'),
       (3, 3, '2022-12-30'),
       (6, 6, '2022-12-01'),
       (7, 9, '2022-12-03');