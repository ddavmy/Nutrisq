INSERT INTO User (email, username, password)
SELECT 'string@string.str', 'string', '$2a$10$aCfS5UM8eWn1fEKf0nYTIOqXZrbWMjDGITo8TPeccNpQEBKA1LbxG'
WHERE NOT EXISTS (
    SELECT 1 FROM User WHERE username = 'string'
);
INSERT INTO UserSpecifics (username, firstname, lastname, born, weight, height, sex)
SELECT 'string', 'Str', 'Ing', '2012-03-16', '57.5', '172', 'Male'
WHERE NOT EXISTS (
    SELECT 1 FROM UserSpecifics WHERE username = 'string'
);
INSERT INTO User (email, username, password)
SELECT 'test@test.pl', 'test', '$2a$10$KwWNYBC0gNw67i0HYg/QieGgRj44vVPQmYrUPzRcQ4KJ5iYoPQfRu'
WHERE NOT EXISTS (
    SELECT 1 FROM User WHERE username = 'test'
);
INSERT INTO UserSpecifics (username)
SELECT 'test'
WHERE NOT EXISTS (
    SELECT 1 FROM UserSpecifics WHERE username = 'test'
);