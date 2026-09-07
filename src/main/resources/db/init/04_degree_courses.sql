-- ===============================
-- DEGREE COURSES
-- ===============================

INSERT INTO academic.degree_courses (id, name, graduation_class, duration)
VALUES
-- BACHELOR
(gen_random_uuid(), 'INGEGNERIA GESTIONALE', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA INFORMATICA', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA ELETTRICA', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA CIVILE', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA MECCANICA', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA ELETTRONICA', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA AMBIENTALE', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA EDILE', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA DELL''AUTOMAZIONE', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA DELLE TELECOMUNICAZIONI', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA DEI SISTEMI MEDICALI', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA AEROSPAZIALE', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA CHIMICA', 'BACHELOR', 3),
(gen_random_uuid(), 'INGEGNERIA BIOMEDICA', 'BACHELOR', 3),
-- MASTER
('f4996047-a62c-4aa6-96ac-d5a9e27431b2', 'INGEGNERIA GESTIONALE MAGISTRALE', 'MASTER', 2),
('f4996047-a62c-4aa6-96ac-d5a9e2743234', 'INGEGNERIA INFORMATICA MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA ELETTRICA MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA MECCANICA MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA ELETTRONICA MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA AMBIENTALE MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA EDILE MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA CIVILE MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA DELL''AUTOMAZIONE MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA DELLE TELECOMUNICAZIONI MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA DEI SISTEMI MEDICALI MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA AEROSPAZIALE MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA CHIMICA MAGISTRALE', 'MASTER', 2),
(gen_random_uuid(), 'INGEGNERIA BIOMEDICA MAGISTRALE', 'MASTER', 2)
ON CONFLICT (name) DO NOTHING;