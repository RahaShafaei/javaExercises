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
INSERT INTO  STUDENT_GRADE (STUDENT_ID , GRADE_ID )
VALUES
    (1,1),
    (2,1),
    (3,1),
    (3,2),
    (4,2),
    (5,2),
    (6,3),
    (7,3),
    (8,3),
    (9,3);
-- ==============================================
INSERT INTO  TEACHER_GRADE (TEACHER_ID , GRADE_ID )
VALUES
    (1,1),
    (2,1),
    (3,1),
    (3,2),
    (4,2),
    (5,2),
    (6,3),
    (7,3),
    (8,3),
    (9,3);
-- ==============================================
INSERT INTO FEE (FEE_ID, COST, DEAD_LINE, GRADE_GRADE_ID, STUDENT_STUDENT_ID, STATUS)
VALUES (1, 1000, '2022-11-28', 1, 1, null),
       (2, 1000, '2022-11-28', 1, 2, null),
       (3, 1500, '2022-12-31', 2, 3, null),
       (4, 1500, '2022-12-31', 2, 4, null),
       (5, 1500, '2022-12-31', 2, 5, null),
       (6, 1700, '2022-12-04', 3, 6, null),
       (7, 1700, '2022-12-04', 3, 7, null),
       (8, 1700, '2022-12-04', 3, 8, null),
       (9, 1700, '2022-12-04', 3, 9, null);
-- ==============================================
INSERT INTO SALARY (SALARY_ID, COST, DEAD_LINE, GRADE_GRADE_ID, TEACHER_TEACHER_ID, STATUS)
VALUES (1, 1000, '2022-11-28', 1, 1, null),
       (2, 1000, '2022-11-28', 1, 2, null),
       (3, 1500, '2022-12-31', 2, 3, null),
       (4, 1500, '2022-12-31', 2, 4, null),
       (5, 1500, '2022-12-31', 2, 5, null),
       (6, 1700, '2022-12-04', 3, 6, null),
       (7, 1700, '2022-12-04', 3, 7, null),
       (8, 1700, '2022-12-04', 3, 8, null),
       (9, 1700, '2022-12-04', 3, 9, null);
-- ==============================================
INSERT INTO PAYMENT (PAYMENT_ID, FEE_FEE_ID, SALARY_SALARY_ID,PAYMENT_DATE)
VALUES (1, 1, null, '2022-11-26'),
       (2, 2, null, '2022-11-28'),
       (3, 3, null, '2022-12-30'),
       (4, 6, null, '2022-12-01'),
       (5, 9, null, '2022-12-03'),
       (6, null, 1, '2022-11-26'),
       (7, null, 2, '2022-11-28'),
       (8, null, 3, '2022-12-30'),
       (9, null, 6, '2022-12-01'),
       (10, null, 9, '2022-12-04');
