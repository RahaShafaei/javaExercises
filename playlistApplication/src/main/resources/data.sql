INSERT INTO PLAYLIST (PLAYLIST_ID, NAME)
VALUES (1, 'FUNNY'),
       (2, 'SAD'),
       (3, 'CHILL'),
       (4, 'CREATIVE'),
       (5, 'CUTE'),
       (6, 'SUMMER'),
       (7, 'WORKOUT'),
       (8, 'COUNTRY'),
       (9, 'PARTY');
--------------------------------
INSERT INTO GENRE (GENRE_ID, NAME)
VALUES (1, 'Hip-Hop'),
       (2, 'Rap'),
       (3, 'R&B'),
       (4, 'Rock'),
       (5, 'Blues'),
       (6, 'Dance'),
       (7, 'Jazz');
--------------------------------------
INSERT INTO MUSIC (MUSIC_ID, GENERALIST_GENRE_ID , PUB_YEAR, ARTIST, NAME)
VALUES (1, 1, 2013, 'JWP and Bez Cenzury', 'A Pamietasz Jak? (Blend)'),
       (2, 1, 1998, 'Molesta Ewenement', 'Wiedzialem, ze Tak Bedzie'),
       (3, 1, 1999, 'DJ 600 V', '93...94'),
       (4, 1, 2014, 'Chada and Pih', 'Po Tej Samej Stronie'),
       (5, 2, 2015, 'Bialas, Obywatel MC and VNM', 'Pierwszy Walkman'),
       (6, 2, 1998, 'Molesta Ewenement', 'Jeszcze Jedno'),
       (7, 2, 2001, 'Peja and Slums Attack', 'Moj Rap Moja Rzeczywistosc'),
       (8, 2, 2003, 'Ascetoholix feat. Mezo and Szad Akrobata', 'Suczki'),
       (9, 2, 2003, 'Mezo and Lajner feat. Liber', 'Aniele'),
       (10, 6, 1990, 'Robyx', 'Dedication Groove'),
       (11, 6, 1990, 'Robyx', 'The Oscar Mix'),
       (12, 6, 1990, 'Robyx', 'Dedication Dub'),
       (13, 6, 2003, 'Ascetoholix feat. Mezo and Szad Akrobata', 'Suczki'),
       (14, 6, 2003, 'Mezo and Lajner feat. Liber', 'Aniele'),
       (15, 3, 1998, 'Molesta Ewenement', 'Jeszcze Jedno'),
       (16, 3, 2001, 'Peja and Slums Attack', 'Moj Rap Moja Rzeczywistosc'),
       (17, 3, 2004, 'Jesse Dangerously', 'Tom Lehrer''s the Elements'),
       (18, 7, 1974, 'Stanley Cowell Jazz', 'Travelin Man Jazz'),
       (19, 5, 1974, 'Stanley Cowell', 'Travelin Man'),
       (20, 4, 1974, 'Matthew Nelson and Gunnar Nelson', 'Travelin Man'),
       (21, 4, 1971, 'Nacha Guevara ', 'Los Elementos'),
       (22, 4, 2012, 'Dani Alvarez and Sergi Sirvent', 'The Elements');
-----------------------------------------------
INSERT INTO  MUSIC_PLAYLIST (MUSIC_ID , PLAYLIST_ID )
VALUES
    (1,1),
    (2,1),
    (3,1),
    (4,2),
    (5,2),
    (6,2),
    (7,2),
    (8,3),
    (9,3),
    (10,4),
    (11,5),
    (12,6),
    (13,6),
    (14,7),
    (15,7),
    (16,7),
    (17,8),
    (18,8),
    (19,8),
    (20,9),
    (21,9),
    (22,9);

