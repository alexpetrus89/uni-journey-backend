-- ================================================
-- STUDY PLAN: INGEGNERIA GESTIONALE MAGISTRALE
-- Ordinamento: ORD270 | CFU totali: 120
-- ================================================

WITH new_plan AS (
    INSERT INTO study.study_plans (study_plan_id, student_id, ordering)
    SELECT gen_random_uuid(), '44444444-4444-4444-4444-444444444444', 'ORD270'
    WHERE NOT EXISTS (
        SELECT 1 FROM study.study_plans
        WHERE student_id = '44444444-4444-4444-4444-444444444444'
    )
    RETURNING study_plan_id
)
INSERT INTO academic.study_plan_courses (study_plan_id, course_id)
SELECT np.study_plan_id, c.id
FROM new_plan np
JOIN academic.courses c ON true
JOIN academic.degree_courses dc ON c.degree_course_id = dc.id
WHERE dc.name = 'INGEGNERIA GESTIONALE MAGISTRALE'
AND c.name IN (
    'analisi dei sistemi dinamici',
    'sistemi informativi aziendali',
    'supply chain management avanzato',
    'strategia e innovazione',
    'big data analytics',
    'industria 4.0 e fabbrica digitale',
    'internet of things',
    'fondamenti di cybersecurity',
    'basi di dati avanzate',
    'economia circolare e sostenibilità',
    'project management avanzato',
    'finanza aziendale',
    'logistica industriale',
    'gestione della produzione industriale',
    'tirocinio',
    'tesi magistrale'
);