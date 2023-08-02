INSERT INTO employee_entity (cin, firstname, lastname, position, children)
VALUES ('987654321', 'Marie', 'Martin', 'Designer graphique',0);

INSERT INTO employee_entity (cin, firstname, lastname, position, resignation_date, children)
VALUES ('567890123', 'Pierre', 'Durand', 'Chef de projet', '2023-12-31 00:00:00',5);

INSERT INTO employee_entity (cin, firstname, lastname, position, children)
VALUES ('765432109', 'Sophie', 'Lefebvre', 'Analyste financier', 2);

INSERT INTO employee_entity (cin, firstname, lastname, position, children)
VALUES ('908172635', 'Thomas', 'Leroy', 'Assistant administratif',3);

INSERT INTO phone_number_entity (country_code, phone_number, employee_id)
VALUES ('+33', '612345678', 1);

INSERT INTO employee_entity (cin, firstname, lastname, position, image_base64, children)
VALUES ('246813579', 'Isabelle', 'Girard', 'Marketing Manager', 'base64_encoded_image_2',3);

INSERT INTO employee_entity (cin, firstname, lastname, position, hire_date, birthdate, children)
VALUES ('135792468', 'Alexandre', 'Rousseau', 'Responsable des ventes', '2022-05-10 00:00:00', '1988-09-20 00:00:00',0);

INSERT INTO employee_entity (cin, firstname, lastname, position, personal_email, work_email, children)
VALUES ('012345678', 'Camille', 'Lacroix', 'Développeur Web', 'camille.lacroix@email.com', 'camille@company.com',4);

INSERT INTO employee_entity (cin, firstname, lastname, position, children)
VALUES ('975318642', 'Luc', 'Mercier', 'Analyste de données', 0);

INSERT INTO employee_entity (cin, cnaps, firstname, lastname, position, children)
VALUES ('864209753', 'CNAPS123456', 'Caroline', 'Dubois', 'Consultante RH',6);
