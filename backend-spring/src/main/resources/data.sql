CREATE SCHEMA IF NOT EXISTS time_management_system;
--
SET SCHEMA 'time_management_system';

INSERT INTO department (id, name)
VALUES (1, 'Маркетинг'),
       (2, 'IT'),
       (3, 'Продажи')
ON CONFLICT (id)
DO NOTHING;

INSERT INTO position (id, name, department_id)
VALUES (1, 'Бэкенд разработчик', 2),
       (2, 'Фронтенд разработчик', 2),
       (3, 'QA', 2),
       (4, 'Тимлид', 2),
       (5, 'Пиар менеджер', 1),
       (6, 'Руководитель отдела маркетинга', 1),
       (7, 'Менеджер по продажам', 3),
       (8, 'Руководитель отдела продаж', 3)
ON CONFLICT (id)
DO NOTHING;

INSERT INTO privilege (id, increased_amount, name)
VALUES (1, 2, 'Увеличенное количество опозданий на работу'),
       (2, 3, 'Увеличенное количество ранних уходов с работы'),
       (3, 1, 'Увеличенное количество отсутствий на работе'),
       (4, 1, 'Увеличенное количество прогулов работы'),
       (5, 15, 'Увеличенное суммарное время перерывов'),
       (6, 20, 'Увеличенное суммарное время обедов'),
       (7, 10, 'Увеличенное суммарное время отвлечений')
ON CONFLICT (id)
DO NOTHING;

INSERT INTO employee (id, age, name, position_id, privileges_number)
VALUES (1, 32, 'Алексей Никифоров', 5, 5),
       (2, 45, 'Борис Филимонов', 6, 3),
       (3, 41, 'Федор Добров', 7, 1),
       (4, 50, 'Александр Бирюков', 3, 0),
       (5, 34, 'Дмитрий Куплинов', 8, 0),
       (6, 31, 'Елена Зайцева', 1, 0),
       (7, 25, 'Анастасия Макарова', 2, 0),
       (8, 37, 'Кристина Алексеева', 3, 0),
       (9, 42, 'Михаил Мишустин', 4, 0)
ON CONFLICT (id)
DO NOTHING;


INSERT INTO settings (id, current_settings_profile, default_start_work, default_work_time,
                      max_absence_count_by_month, max_distraction_time_by_day, max_early_living_count_by_month,
                      max_late_count_by_month, max_rest_time_by_day, max_skip_count_by_month, name)
VALUES (1, false, '09:00', 540, 1, 30, 3, 3, 30, 0, 'стандартный'),
       (2, true, '08:00', 540, 0, 15, 0, 0, 15, 0, 'строгий')
ON CONFLICT (id)
DO NOTHING;

INSERT INTO time_sheet (id, date, end_work, start_work, work_time, absence_reason, employee_id)
VALUES (1, '2023-10-02', '18:00:00', '9:00', 540, null, 1),
       (2, '2023-10-03', null, null, null, 'illness', 1),
       (3, '2023-10-04', '18:00:00', '8:50', 550, null, 1),
       (4, '2023-10-05', null, null, null, null, 1),
       (5, '2023-10-06', '18:00:00', '8:00', 600, null, 1)
ON CONFLICT (id)
DO NOTHING;

INSERT INTO distraction (id, date, distraction_time, end_distraction, start_distraction, employee_id)
VALUES (1, '2023-10-02', 10, '12:10', '12:00', 1),
       (2, '2023-10-02', 15, '13:10', '12:55', 1),
       (3, '2023-10-02', 5, '11:25', '11:20', 1)
ON CONFLICT (id)
DO NOTHING;

INSERT INTO rest (id, date, start_rest, end_rest, rest_time, employee_id)
VALUES (1, '2023-10-02', '9:45', '11:00', 75, 1)
ON CONFLICT (id)
DO NOTHING;
