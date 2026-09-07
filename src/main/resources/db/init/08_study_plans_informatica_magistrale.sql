-- ================================================
-- STUDY PLAN: INGEGNERIA INFORMATICA MAGISTRALE
-- Ordinamento: ORD270 | CFU totali: 120
-- ================================================

WITH new_plan AS (
    INSERT INTO study.study_plans (study_plan_id, student_id, ordering)
    SELECT gen_random_uuid(), '55555555-5555-5555-5555-555555555555', 'ORD270'
    WHERE NOT EXISTS (
        SELECT 1 FROM study.study_plans
        WHERE student_id = '55555555-5555-5555-5555-555555555555'
    )
    RETURNING study_plan_id
)
INSERT INTO academic.study_plan_courses (study_plan_id, course_id)
SELECT np.study_plan_id, c.id
FROM new_plan np
JOIN academic.courses c ON true
JOIN academic.degree_courses dc ON c.degree_course_id = dc.id
WHERE dc.name = 'INGEGNERIA INFORMATICA MAGISTRALE'
AND c.name IN (
    'analisi dei sistemi dinamici',
    'sistemi informativi distribuiti',
    'machine learning e deep learning',
    'cloud e edge computing',
    'cybersecurity avanzata',
    'internet of things avanzato',
    'basi di dati avanzate',
    'compilatori e linguaggi',
    'big data e data engineering',
    'blockchain e sistemi distribuiti',
    'vision e robotica',
    'tirocinio',
    'tesi magistrale'
);