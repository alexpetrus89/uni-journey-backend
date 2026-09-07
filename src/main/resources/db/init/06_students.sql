-- =========================
-- STUDENT 1
-- =========================

INSERT INTO auth.users (
    id,
    username,
    first_name,
    last_name,
    dob,
    fiscal_code,
    phone,
    role,
    street,
    city,
    country,
    zip_code,
    deleted,
    created_at,
    updated_at,
    version
) VALUES (
    '44444444-4444-4444-4444-444444444444',
    'alexpetruzzi89@gmail.com',
    'Brant',
    'Bjork',
    '1978-07-20',
    'FRRMRC00L20D612P',
    '+39 02 3334455',
    'STUDENT',
    'Via Manzoni 8',
    'Palm Desert',
    'Italy',
    '20121',
    false,
    NOW(),
    NOW(),
    0
) ON CONFLICT DO NOTHING;

INSERT INTO auth.credentials (
    user_id,
    password,
    enabled,
    locked,
    credentials_expiration_date,
    failed_login_attempts,
    last_failed_login
) VALUES (
    '44444444-4444-4444-4444-444444444444',
    '$2a$12$VRTzc/4.xoSeIcLNbw412e/BZbT7CkRGio8iZCt1wILsVeuCX7D/2',
    true,
    false,
    '2090-01-01',
    0,
    NULL
) ON CONFLICT DO NOTHING;

INSERT INTO auth.password_history (
    credentials,
    encoded_password,
    changed_at
) VALUES (
    '44444444-4444-4444-4444-444444444444',
    '$2a$12$VRTzc/4.xoSeIcLNbw412e/BZbT7CkRGio8iZCt1wILsVeuCX7D/2',
    NOW()
) ON CONFLICT DO NOTHING;

INSERT INTO people.students (
    id,
    register,
    degree_course_id
) VALUES (
    '44444444-4444-4444-4444-444444444444',
    '100001',
    'f4996047-a62c-4aa6-96ac-d5a9e27431b2' -- INGEGNERIA GESTIONALE MAGISTRALE
) ON CONFLICT DO NOTHING;


-- =========================
-- STUDENT 2
-- =========================

INSERT INTO auth.users (
    id,
    username,
    first_name,
    last_name,
    dob,
    fiscal_code,
    phone,
    role,
    street,
    city,
    country,
    zip_code,
    deleted,
    created_at,
    updated_at,
    version
) VALUES (
    '55555555-5555-5555-5555-555555555555',
    'stud.romano',
    'Sofia',
    'Romano',
    '2001-03-14',
    'RMNSFO01C54H501W',
    '+39 06 7778899',
    'STUDENT',
    'Via Nazionale 33',
    'Roma',
    'Italy',
    '00184',
    false,
    NOW(),
    NOW(),
    0
) ON CONFLICT DO NOTHING;

INSERT INTO auth.credentials (
    user_id,
    password,
    enabled,
    locked,
    credentials_expiration_date,
    failed_login_attempts,
    last_failed_login
) VALUES (
    '55555555-5555-5555-5555-555555555555',
    '$2a$12$VRTzc/4.xoSeIcLNbw412e/BZbT7CkRGio8iZCt1wILsVeuCX7D/2',
    true,
    false,
    '2090-01-01',
    0,
    NULL
) ON CONFLICT DO NOTHING;

INSERT INTO auth.password_history (
    credentials,
    encoded_password,
    changed_at
) VALUES (
    '55555555-5555-5555-5555-555555555555',
    '$2a$12$VRTzc/4.xoSeIcLNbw412e/BZbT7CkRGio8iZCt1wILsVeuCX7D/2',
    NOW()
) ON CONFLICT DO NOTHING;

INSERT INTO people.students (
    id,
    register,
    degree_course_id
) VALUES (
    '55555555-5555-5555-5555-555555555555',
    '100002',
    'f4996047-a62c-4aa6-96ac-d5a9e2743234' -- INGEGNERIA INFORMATICA MAGISTRALE
) ON CONFLICT DO NOTHING;