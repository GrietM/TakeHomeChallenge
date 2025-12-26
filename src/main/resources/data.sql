-- =========================
-- USER DE PRUEBA
-- =========================
-- email: user@test.com
-- password (plain): test123
-- password (bcrypt hash): $2a$10$...

INSERT INTO users (name, lastname, email, password)
VALUES (
  'test',
  'user',
  'user@test.com',
  '$2a$10$yuRuqvFoX1RSFzqAwk77ZeeFiD8S7P31iStlXsCmnv5j.2jWGYs52'
);

-- =========================
-- NOTIFICACIONES DE PRUEBA
-- =========================

INSERT INTO notification (titulo, contenido, canal, user_id)
VALUES
  ('Email notification', 'This is a test EMAIL notification', 'EMAIL', 1),
  ('SMS notification',   'This is a test SMS notification',   'SMS',   1),
  ('Push notification',  'This is a test PUSH notification',  'PUSH',  1);
