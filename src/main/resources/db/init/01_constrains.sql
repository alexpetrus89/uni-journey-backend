-- USERS
ALTER TABLE auth.users DROP CONSTRAINT IF EXISTS uq_user_username;
ALTER TABLE auth.users ADD CONSTRAINT uq_user_username UNIQUE (username);

-- ADMINS
ALTER TABLE people.admins DROP CONSTRAINT IF EXISTS uq_admin_code;
ALTER TABLE people.admins ADD CONSTRAINT uq_admin_code UNIQUE (admin_code);

-- PROFESSORS
ALTER TABLE people.professors DROP CONSTRAINT IF EXISTS uq_professor_code;
ALTER TABLE people.professors ADD CONSTRAINT uq_professor_code UNIQUE (professor_code);

-- STUDENTS
ALTER TABLE people.students DROP CONSTRAINT IF EXISTS uq_student_register;
ALTER TABLE people.students ADD CONSTRAINT uq_student_register UNIQUE (register);

-- COURSES
ALTER TABLE academic.courses DROP CONSTRAINT IF EXISTS uq_course_name_degree;
ALTER TABLE academic.courses ADD CONSTRAINT uq_course_name_degree UNIQUE (name, degree_course_id);