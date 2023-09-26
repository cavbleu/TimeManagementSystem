INSERT IGNORE INTO departments (id, name)
VALUES (1, 'Marketing'),
       (2, 'IT'),
       (3, 'Sales');

INSERT IGNORE INTO employees (id, age, first_name, last_name, work_position, department_id)
VALUES (1, 32, 'Aleksei', 'Zhukov', 'Programmer', 2),
       (2, 45, 'Boris', 'Filimonov', 'QA', 2),
       (3, 41, 'Fedor', 'Dobrov', 'TeamLead', 2),
       (4, 50, 'Aleksandr', 'Biryukov', 'PiarAgent', 1),
       (5, 34, 'Dmitriy', 'Kuplinov', 'Avitolog', 1),
       (6, 31, 'Elena', 'Zayceva', 'Marketolog', 1),
       (7, 25, 'Anastasiya', 'Makarova', 'Salesman', 3),
       (8, 37, 'Kristina', 'Alekseeva', 'Salesman', 3),
       (9, 42, 'Mihail', 'Mishustin', 'Manager', 3);

INSERT IGNORE INTO time_management (date, end_lunch, end_work,
                                    lunch_time, start_lunch, start_work, work_time)
VALUES ('2023-09-27',  '14:00:00', '18:00:00', 60, '13:00', '08:00', 480),
       ('2023-09-27', '14:00:00', '18:00:00', 60, '13:00', '09:00', 420);

INSERt IGNORE INTO employees_time_management (employee_id, time_management_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2);

# INSERT INTO schedule (absence)
# VALUES ('USA');