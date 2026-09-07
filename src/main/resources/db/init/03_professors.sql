-- =========================
-- PROFESSOR 1
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
    '22222222-2222-2222-2222-222222222222',
    'prof.bianchi',
    'Luigi',
    'Bianchi',
    '1975-03-22',
    'BNCLGU75C22F205X',
    '+39 06 9876543',
    'PROFESSOR',
    'Via Garibaldi 5',
    'Roma',
    'Italy',
    '00185',
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
    '22222222-2222-2222-2222-222222222222',
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
    '22222222-2222-2222-2222-222222222222',
    '$2a$12$VRTzc/4.xoSeIcLNbw412e/BZbT7CkRGio8iZCt1wILsVeuCX7D/2',
    NOW()
) ON CONFLICT DO NOTHING;

INSERT INTO people.professors (id, professor_code)
VALUES ('22222222-2222-2222-2222-222222222222', 'AB12CD34')
ON CONFLICT DO NOTHING;

-- =========================
-- PROFESSOR 2
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
    '33333333-3333-3333-3333-333333333333',
    'prof.verdi',
    'Anna',
    'Verdi',
    '1978-11-10',
    'VRDNNA78S50G273K',
    '+39 011 5556677',
    'PROFESSOR',
    'Corso Vittorio 12',
    'Torino',
    'Italy',
    '10121',
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
    '33333333-3333-3333-3333-333333333333',
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
    '33333333-3333-3333-3333-333333333333',
    '$2a$12$VRTzc/4.xoSeIcLNbw412e/BZbT7CkRGio8iZCt1wILsVeuCX7D/2',
    NOW()
) ON CONFLICT DO NOTHING;

INSERT INTO people.professors (id, professor_code)
VALUES ('33333333-3333-3333-3333-333333333333', 'EF56GH78')
ON CONFLICT DO NOTHING;