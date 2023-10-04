INSERT INTO department (id, name)
VALUES (1, 'Marketing'),
       (2, 'IT'),
       (3, 'Sales');

INSERT INTO position (id, name)
VALUES (1, 'Programmer'),
       (2, 'QA'),
       (3, 'TeamLead'),
       (4, 'PiarAgent'),
       (5, 'Avitolog'),
       (6, 'Marketolog'),
       (7, 'Salesman');

INSERT INTO employee (id, age, name, position_id, department_id)
VALUES (1, 32, 'Aleksei Zhukov', 1, 1),
       (2, 45, 'Boris Filimonov', 2, 2),
       (3, 41, 'Fedor Dobrov', 3, 2),
       (4, 50, 'Aleksandr Biryukov', 4, 1),
       (5, 34, 'Dmitriy Kuplinov', 5, 1),
       (6, 31, 'Elena Zayceva', 6, 1),
       (7, 25, 'Anastasiya Makarova', 7, 3),
       (8, 37, 'Kristina Alekseeva', 7, 3),
       (9, 42, 'Mihail Mishustin', 1, 3);

INSERT INTO time_sheet (id, date, end_work, productive_time, skip_reason, start_work, work_time)
VALUES (1, '2023-10-01',  '18:00:00', 360, null, '09:00', 540),
       (2, '2023-10-03', '18:00:00', 130, null, '13:00', 540);

INSERT INTO  employee_time_sheet (employee_id, time_sheet_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2);
