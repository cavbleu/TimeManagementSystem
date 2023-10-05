INSERT INTO department (id, name)
VALUES (1, 'Маркетинг'),
       (2, 'IT'),
       (3, 'Продажи');

INSERT INTO position (id, name)
VALUES (1, 'Бэкенд разработчик'),
       (2, 'Фронтенд разработчик'),
       (3, 'QA'),
       (4, 'Тимлид'),
       (5, 'Пиар менеджер'),
       (6, 'Руководитель отдела маркетинга'),
       (7, 'Менеджер по продажам'),
       (8, 'Руководитель отдела продаж');

INSERT INTO privilege (id, increased_amount, name)
VALUES (1, 2, 'Увеличенное количество опозданий на работу'),
       (2, 3, 'Увеличенное количество ранних уходов с работы'),
       (3, 1, 'Увеличенное количество отсутствий на работе'),
       (4, 1, 'Увеличенное количество пропусков работы'),
       (5, 15, 'Увеличенное суммарное время перерывов'),
       (6, 20, 'Увеличенное суммарное время обедов'),
       (7, 10, 'Увеличенное суммарное время отвлечений');

INSERT INTO settings (id, current_settings_profile, default_start_work, default_work_time, max_absence_work_count_by_month,
                      max_distraction_time_by_day, max_early_living_count_by_month, max_late_count_by_month,
                      max_lunch_time_by_day, max_rest_time_by_day, max_skip_work_count_by_month, settings_profile)
VALUES (1, 0, '09:00', 540, 1, 30, 3, 3, 60, 30, 0, 'стандартный'),
       (1, 1, '08:00', 540, 0, 15, 0, 0, 60, 15, 0, 'строгий');

INSERT INTO employee (id, age, name, position_id, department_id)
VALUES (1, 32, 'Алексей Никифоров', 5, 1),
       (2, 45, 'Борис Филимонов', 6, 1),
       (3, 41, 'Федор Добров', 7, 3),
       (4, 50, 'Александр Бирюков', 7, 3),
       (5, 34, 'Дмитрий Куплинов', 8, 3),
       (6, 31, 'Елена Зайцева', 1, 1),
       (7, 25, 'Анастасия Макарова', 2, 1),
       (8, 37, 'Кристина Алексеева', 3, 1),
       (9, 42, 'Михаил Мишустин', 4, 1);

INSERT INTO time_sheet (id, date, end_work, start_work, work_time, absence_reason)
VALUES (1, '2023-10-01',  '18:00:00', '09:00', 540, null),
       (2, '2023-10-03', '18:00:00', '13:00', 540, 'illness');

INSERT INTO  employee_time_sheet (employee_id, time_sheet_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2);

INSERT INTO distraction (id, date, distraction_time, end_distraction, start_distraction, time_sheet_id)
VALUES ();

INSERT INTO rest (id, date, end_lunch, end_rest, lunch_time, rest_time, start_lunch, start_rest, time_sheet_id)
VALUES ();