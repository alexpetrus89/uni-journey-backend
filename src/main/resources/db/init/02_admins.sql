-- =========================
-- ADMIN
-- =========================

INSERT INTO auth.users (
    id, username,
    first_name, last_name, dob,
    fiscal_code, phone, role,
    street, city, country, zip_code,
    deleted, created_at, updated_at, version
) VALUES (
    '11111111-1111-1111-1111-111111111111',
    'anacleto@gmail.com',
    'Mario', 'Rossi', '1980-05-15',
    'RSSMRA80E15H501Z', '+39 02 1234567', 'ADMIN',
    'Via Roma 1', 'Milano', 'Italy', '20121',
    false, NOW(), NOW(), 0
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
    '11111111-1111-1111-1111-111111111111',
    '$2a$12$VRTzc/4.xoSeIcLNbw412e/BZbT7CkRGio8iZCt1wILsVeuCX7D/2', --- password
    true, false, '2090-01-01', 0, NULL
) ON CONFLICT DO NOTHING;

INSERT INTO auth.password_history (
    credentials, encoded_password, changed_at
) VALUES (
    '11111111-1111-1111-1111-111111111111',
    '$2a$12$VRTzc/4.xoSeIcLNbw412e/BZbT7CkRGio8iZCt1wILsVeuCX7D/2',
    NOW()
) ON CONFLICT DO NOTHING;

INSERT INTO people.admins (id, admin_code)
VALUES ('11111111-1111-1111-1111-111111111111', 'ADMAB12CD34')
ON CONFLICT DO NOTHING;