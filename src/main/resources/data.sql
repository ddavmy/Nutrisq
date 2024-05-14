INSERT INTO UserInfo (email, username, password, roles)
SELECT 'string@string.str', 'string', '$2a$10$aCfS5UM8eWn1fEKf0nYTIOqXZrbWMjDGITo8TPeccNpQEBKA1LbxG', 'user'
WHERE NOT EXISTS (
    SELECT 1 FROM UserInfo WHERE username = 'string'
);
INSERT INTO UserDetails (userId, firstname, lastname, born, weight, height, sex)
SELECT '1', 'Str', 'Ing', '2012-03-16', '57.5', '172', 'Male'
WHERE NOT EXISTS (
    SELECT 1 FROM UserDetails WHERE userId = 1
);