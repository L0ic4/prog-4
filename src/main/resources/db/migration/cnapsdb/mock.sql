INSERT INTO cnaps_employee_entity
(firstname, lastname, work_email, personal_email, cin, address, cnaps, children, category, sex, function, birthdate,
 hire_date, resignation_date)
VALUES ('Prénom1', 'Nom1', 'email1@entreprise.com', 'email_personnel1@example.com', 'CIN123456789', 'Adresse1',
        'CNAPS123', 2, 'M1', 'M', 'MANAGEMENT', '1990-01-15', '2022-03-10', NULL),
       ('Prénom2', 'Nom2', 'email2@entreprise.com', 'email_personnel2@example.com', 'CIN987654321', 'Adresse2',
        'CNAPS456', 0, 'M2', 'F', 'HR', '1988-05-20', '2021-11-22', NULL),
       ('Prénom3', 'Nom3', 'email3@entreprise.com', 'email_personnel3@example.com', 'CIN456123789', 'Adresse3',
        'CNAPS789', 1, 'OS1', 'M', 'IT', '1995-08-10', '2023-01-07', NULL),
       ('Prénom4', 'Nom4', 'email4@entreprise.com', 'email_personnel4@example.com', 'CIN789123456', 'Adresse4',
        'CNAPS1234', 3, 'OS2', 'F', 'ACCOUNTING', '1987-11-03', '2020-06-15', '2022-09-30'),
       ('Prénom5', 'Nom5', 'email5@entreprise.com', 'email_personnel5@example.com', 'CIN321654987', 'Adresse5',
        'CNAPS5678', 2, 'OS3', 'M', 'MARKETING', '1992-04-25', '2019-02-12', NULL);