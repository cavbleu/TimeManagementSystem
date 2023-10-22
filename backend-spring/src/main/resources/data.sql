SET SCHEMA 'time_management_system';
-- --
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

INSERT INTO settings (id, current_settings_profile, default_start_work, default_work_time,
                      max_absence_count_by_month, max_distraction_time_by_day, max_early_living_count_by_month,
                      max_late_count_by_month, max_rest_time_by_day, max_skip_count_by_month, name,
                      max_excess_distraction_time_by_month, max_excess_rest_time_by_month)
VALUES (1, true, '09:00', 540, 1, 30, 3, 3, 30, 0, 'стандартный', 2, 2),
       (2, false, '09:00', 540, 0, 0, 0, 0, 0, 0, 'строгий', 0, 0)
ON CONFLICT (id)
    DO NOTHING;

INSERT INTO department (id, name)
VALUES (1, 'Маркетинг'),
       (2, 'IT'),
       (3, 'Продажи')
ON CONFLICT (id)
    DO NOTHING;

INSERT INTO position (id, name, department_id)
VALUES (1, 'Бэкенд разработчик', 2),
       (2, 'Фронтенд разработчик', 2),
       (3, 'Пиар менеджер', 1),
       (4, 'Руководитель отдела маркетинга', 1),
       (5, 'Менеджер по продажам', 3),
       (6, 'Руководитель отдела продаж', 3)
ON CONFLICT (id)
    DO NOTHING;

INSERT INTO employee (id, age, name, position_id, privileges_number)
VALUES (1, 32, 'Никифоров Алексей Валерьевич', 1, 5),
       (2, 45, 'Волков Владислав Михайлович', 1, 3),
       (3, 41, 'Плотникова Любовь Артёмовна', 2, 1),
       (4, 50, 'Терентьев Илья Максимович', 2, 0),
       (5, 34, 'Авдеева Ксения Михайловна', 3, 0),
       (6, 31, 'Анисимова Таисия Ярославовна', 3, 10),
       (7, 25, 'Тарасова София Марковна', 4, 0),
       (8, 37, 'Щербакова Полина Сергеевна', 4, 6),
       (9, 42, 'Терентьев Егор Антонович', 5, 0),
       (10, 31, 'Егорова Маргарита Данииловна', 5, 1),
       (11, 47, 'Бородин Александр Михайлович', 6, 0),
       (12, 30, 'Зорина Виктория Ильинична', 6, 0)
ON CONFLICT (id)
    DO NOTHING;

INSERT INTO time_sheet (id, date, end_work, start_work, work_time, absence_reason, employee_id)
VALUES (1, '2023-10-02','17:00:00', '9:00', 480, null, 1),
       (2, '2023-10-03', '17:30:00', '9:00', 510, null, 1),
       (3, '2023-10-04', '18:00:00', '9:20', 520, null, 1),
       (4, '2023-10-05', null, null, null, null, 1),
       (5, '2023-10-06', null, null, null, 'проспал', 1),
       (6, '2023-10-02', '18:00:00', '9:00', 540, null, 2),
       (7, '2023-10-03', '17:50:00', '9:00', 530, null, 2),
       (8, '2023-10-04', '18:10:00', '9:10', 540, null, 2),
       (9, '2023-10-05', null, null, null, 'устал', 2),
       (10, '2023-10-06', '17:50:00', '9:00', 530, null, 2),
       (11, '2023-10-02', '17:50:00', '9:00', 530, null, 3),
       (12, '2023-10-03', '17:50:00', '9:00', 530, null, 3),
       (13, '2023-10-04', '19:00:00', '9:00', 600, null, 3),
       (14, '2023-10-05', '19:00:00', '9:00', 600, null, 3),
       (15, '2023-10-06', '19:00:00', '9:00', 600, null, 3),
       (16, '2023-10-02', '17:00:00', '9:00', 480, null, 4),
       (17, '2023-10-03', null, null, null, 'болезнь', 4),
       (18, '2023-10-04', null, null, null, 'болезнь', 4),
       (19, '2023-10-05', '17:00:00', '9:00', 480, null, 4),
       (20, '2023-10-06', '17:00:00', '9:00', 480, null, 4),
       (21, '2023-10-02', '19:00:00', '10:00', 540, null, 5),
       (22, '2023-10-03', '19:00:00', '10:00', 540, null, 5),
       (23, '2023-10-04', '19:00:00', '10:00', 540, null, 5),
       (24, '2023-10-05', '19:00:00', '10:00', 540, null, 5),
       (25, '2023-10-06', '19:00:00', '10:00', 540, null, 5),
       (26, '2023-10-02', '18:00:00', '9:00', 540, null, 6),
       (27, '2023-10-03', null, null, null, 'болезнь', 6),
       (28, '2023-10-04', '18:00:00', '8:50', 550, null, 6),
       (29, '2023-10-05', null, null, null, null, 6),
       (30, '2023-10-06', '18:00:00', '8:00', 600, null, 6),
       (31, '2023-10-02', '18:00:00', '9:00', 540, null, 7),
       (32, '2023-10-03', null, null, null, 'болезнь', 7),
       (33, '2023-10-04', '18:00:00', '8:50', 550, null, 7),
       (34, '2023-10-05', null, null, null, null, 7),
       (35, '2023-10-06', '18:00:00', '8:00', 600, null, 7),
       (36, '2023-10-02', '18:00:00', '9:00', 540, null, 8),
       (37, '2023-10-03', null, null, null, 'болезнь', 8),
       (38, '2023-10-04', '18:00:00', '8:50', 550, null, 8),
       (39, '2023-10-05', null, null, null, null, 8),
       (40, '2023-10-06', '18:00:00', '8:00', 600, null, 8),
       (41, '2023-10-02', '18:00:00', '9:00', 540, null, 9),
       (42, '2023-10-03', null, null, null, 'болезнь', 9),
       (43, '2023-10-04', '18:00:00', '8:50', 550, null, 9),
       (44, '2023-10-05', null, null, null, null, 9),
       (45, '2023-10-06', '18:00:00', '8:00', 600, null, 9),
       (46, '2023-10-02', '18:00:00', '9:00', 540, null, 10),
       (47, '2023-10-03', null, null, null, 'болезнь', 10),
       (48, '2023-10-04', '18:00:00', '8:50', 550, null, 10),
       (49, '2023-10-05', null, null, null, null, 10),
       (50, '2023-10-06', '18:00:00', '8:00', 600, null, 10),
       (51, '2023-10-02', '18:00:00', '9:00', 540, null, 11),
       (52, '2023-10-03', null, null, null, 'болезнь', 11),
       (53, '2023-10-04', '18:00:00', '8:50', 550, null, 11),
       (54, '2023-10-05', null, null, null, null, 11),
       (55, '2023-10-06', '18:00:00', '8:00', 600, null, 11),
       (56, '2023-10-02', '18:00:00', '9:00', 540, null, 12),
       (57, '2023-10-03', null, null, null, 'болезнь', 12),
       (58, '2023-10-04', '18:00:00', '8:50', 550, null, 12),
       (59, '2023-10-05', null, null, null, null, 12),
       (60, '2023-10-06', '18:00:00', '8:00', 600, null, 12)
ON CONFLICT (id)
    DO NOTHING;

INSERT INTO distraction (id, date, distraction_time, end_distraction, start_distraction, employee_id)
VALUES (1, '2023-10-02', 31, '12:31', '12:00', 1),
       (2, '2023-10-02', 15, '13:10', '12:55', 1),
       (3, '2023-10-02', 35, '11:55', '11:20', 2),
       (4, '2023-10-02', 45, '11:45', '11:00', 2),
       (5, '2023-10-02', 35, '11:55', '11:20', 3),
       (6, '2023-10-02', 35, '11:55', '11:20', 3),
       (7, '2023-10-02', 35, '09:55', '09:20', 4),
       (8, '2023-10-02', 5, '09:25', '09:20', 4),
       (9, '2023-10-02', 10, '15:30', '15:20', 5),
       (10, '2023-10-02', 10, '16:30', '16:20', 5),
       (11, '2023-10-02', 5, '11:25', '14:20', 6),
       (12, '2023-10-02', 5, '11:25', '14:20', 6),
       (13, '2023-10-02', 30, '12:30', '12:00', 7),
       (14, '2023-10-02', 15, '13:10', '12:55', 7),
       (15, '2023-10-02', 35, '11:55', '11:20', 8),
       (16, '2023-10-02', 45, '11:45', '11:00', 8),
       (17, '2023-10-02', 15, '11:35', '11:20', 9),
       (18, '2023-10-02', 25, '11:45', '11:20', 9),
       (19, '2023-10-02', 5, '09:25', '09:20', 10),
       (20, '2023-10-02', 5, '09:25', '09:20', 10),
       (21, '2023-10-02', 10, '15:30', '15:20', 11),
       (22, '2023-10-02', 10, '16:30', '16:20', 11),
       (23, '2023-10-02', 5, '11:25', '14:20', 12),
       (24, '2023-10-03', 5, '11:25', '14:20', 12),
       (25, '2023-10-03', 31, '12:31', '12:00', 1),
       (26, '2023-10-03', 15, '13:10', '12:55', 1),
       (27, '2023-10-03', 35, '11:55', '11:20', 2),
       (28, '2023-10-03', 45, '11:45', '11:00', 2),
       (29, '2023-10-03', 15, '11:35', '11:20', 3),
       (30, '2023-10-03', 25, '11:45', '11:20', 3),
       (31, '2023-10-03', 5, '09:25', '09:20', 4),
       (32, '2023-10-03', 5, '09:25', '09:20', 4),
       (33, '2023-10-03', 10, '15:30', '15:20', 5),
       (34, '2023-10-03', 10, '16:30', '16:20', 5),
       (35, '2023-10-03', 5, '11:25', '14:20', 6),
       (36, '2023-10-03', 5, '11:25', '14:20', 6),
       (37, '2023-10-03', 30, '12:30', '12:00', 7),
       (38, '2023-10-03', 15, '13:10', '12:55', 7),
       (39, '2023-10-03', 35, '11:55', '11:20', 8),
       (40, '2023-10-03', 45, '11:45', '11:00', 8),
       (41, '2023-10-03', 15, '11:35', '11:20', 9),
       (42, '2023-10-03', 25, '11:45', '11:20', 9),
       (43, '2023-10-03', 5, '09:25', '09:20', 10),
       (44, '2023-10-03', 5, '09:25', '09:20', 10),
       (45, '2023-10-03', 10, '15:30', '15:20', 11),
       (46, '2023-10-03', 10, '16:30', '16:20', 11),
       (47, '2023-10-03', 5, '11:25', '14:20', 12),
       (48, '2023-10-03', 5, '11:25', '14:20', 12),
       (49, '2023-10-04', 30, '12:30', '12:00', 1),
       (50, '2023-10-04', 15, '13:10', '12:55', 1),
       (51, '2023-10-04', 35, '11:55', '11:20', 2),
       (52, '2023-10-04', 45, '11:45', '11:00', 2),
       (53, '2023-10-04', 15, '11:35', '11:20', 3),
       (54, '2023-10-04', 25, '11:45', '11:20', 3),
       (55, '2023-10-04', 5, '09:25', '09:20', 4),
       (56, '2023-10-04', 5, '09:25', '09:20', 4),
       (57, '2023-10-04', 10, '15:30', '15:20', 5),
       (58, '2023-10-04', 10, '16:30', '16:20', 5),
       (59, '2023-10-04', 5, '11:25', '14:20', 6),
       (60, '2023-10-04', 5, '11:25', '14:20', 6),
       (61, '2023-10-04', 30, '12:30', '12:00', 7),
       (62, '2023-10-04', 15, '13:10', '12:55', 7),
       (63, '2023-10-04', 35, '11:55', '11:20', 8),
       (64, '2023-10-04', 45, '11:45', '11:00', 8),
       (65, '2023-10-04', 15, '11:35', '11:20', 9),
       (66, '2023-10-04', 25, '11:45', '11:20', 9),
       (67, '2023-10-04', 5, '09:25', '09:20', 10),
       (68, '2023-10-04', 5, '09:25', '09:20', 10),
       (69, '2023-10-04', 10, '15:30', '15:20', 11),
       (70, '2023-10-04', 10, '16:30', '16:20', 11),
       (71, '2023-10-04', 5, '11:25', '14:20', 12),
       (72, '2023-10-04', 5, '11:25', '14:20', 12),
       (73, '2023-10-05', 30, '12:30', '12:00', 1),
       (74, '2023-10-05', 15, '13:10', '12:55', 1),
       (75, '2023-10-05', 35, '11:55', '11:20', 2),
       (76, '2023-10-05', 45, '11:45', '11:00', 2),
       (77, '2023-10-05', 15, '11:35', '11:20', 3),
       (78, '2023-10-05', 25, '11:45', '11:20', 3),
       (79, '2023-10-05', 5, '09:25', '09:20', 4),
       (80, '2023-10-05', 5, '09:25', '09:20', 4),
       (81, '2023-10-05', 10, '15:30', '15:20', 5),
       (82, '2023-10-05', 10, '16:30', '16:20', 5),
       (83, '2023-10-05', 5, '11:25', '14:20', 6),
       (84, '2023-10-05', 5, '11:25', '14:20', 6),
       (85, '2023-10-05', 30, '12:30', '12:00', 7),
       (86, '2023-10-05', 15, '13:10', '12:55', 7),
       (87, '2023-10-05', 35, '11:55', '11:20', 8),
       (88, '2023-10-05', 45, '11:45', '11:00', 8),
       (89, '2023-10-05', 15, '11:35', '11:20', 9),
       (90, '2023-10-05', 25, '11:45', '11:20', 9),
       (91, '2023-10-05', 5, '09:25', '09:20', 10),
       (92, '2023-10-05', 5, '09:25', '09:20', 10),
       (93, '2023-10-05', 10, '15:30', '15:20', 11),
       (94, '2023-10-05', 10, '16:30', '16:20', 11),
       (95, '2023-10-05', 5, '11:25', '14:20', 12),
       (96, '2023-10-05', 5, '11:25', '14:20', 12),
       (97, '2023-10-06', 30, '12:30', '12:00', 1),
       (98, '2023-10-06', 15, '13:10', '12:55', 1),
       (99, '2023-10-06', 35, '11:55', '11:20', 2),
       (100, '2023-10-06', 45, '11:45', '11:00', 2),
       (101, '2023-10-06', 15, '11:35', '11:20', 3),
       (102, '2023-10-06', 25, '11:45', '11:20', 3),
       (103, '2023-10-06', 5, '09:25', '09:20', 4),
       (104, '2023-10-06', 5, '09:25', '09:20', 4),
       (105, '2023-10-06', 10, '15:30', '15:20', 5),
       (106, '2023-10-06', 10, '16:30', '16:20', 5),
       (107, '2023-10-06', 5, '11:25', '14:20', 6),
       (108, '2023-10-06', 5, '11:25', '14:20', 6),
       (109, '2023-10-06', 30, '12:30', '12:00', 7),
       (110, '2023-10-06', 15, '13:10', '12:55', 7),
       (111, '2023-10-06', 35, '11:55', '11:20', 8),
       (112, '2023-10-06', 45, '11:45', '11:00', 8),
       (113, '2023-10-06', 15, '11:35', '11:20', 9),
       (114, '2023-10-06', 25, '11:45', '11:20', 9),
       (115, '2023-10-06', 5, '09:25', '09:20', 10),
       (116, '2023-10-06', 5, '09:25', '09:20', 10),
       (117, '2023-10-06', 10, '15:30', '15:20', 11),
       (118, '2023-10-06', 10, '16:30', '16:20', 11),
       (119, '2023-10-06', 5, '11:25', '14:20', 12),
       (120, '2023-10-06', 5, '11:25', '14:20', 12)
ON CONFLICT (id)
    DO NOTHING;

INSERT INTO rest (id, date, rest_time, end_rest, start_rest, employee_id)
VALUES (1, '2023-10-02', 35, '12:35', '12:00', 1),
       (2, '2023-10-02', 15, '13:10', '12:55', 1),
       (3, '2023-10-02', 35, '11:55', '11:20', 2),
       (4, '2023-10-02', 45, '11:45', '11:00', 2),
       (5, '2023-10-02', 15, '11:35', '11:20', 3),
       (6, '2023-10-02', 35, '11:55', '11:20', 3),
       (7, '2023-10-02', 35, '09:55', '09:20', 4),
       (8, '2023-10-02', 35, '09:55', '09:20', 4),
       (9, '2023-10-02', 10, '15:30', '15:20', 5),
       (10, '2023-10-02', 10, '16:30', '16:20', 5),
       (11, '2023-10-02', 5, '11:25', '14:20', 6),
       (12, '2023-10-02', 5, '11:25', '14:20', 6),
       (13, '2023-10-02', 30, '12:30', '12:00', 7),
       (14, '2023-10-02', 15, '13:10', '12:55', 7),
       (15, '2023-10-02', 35, '11:55', '11:20', 8),
       (16, '2023-10-02', 45, '11:45', '11:00', 8),
       (17, '2023-10-02', 15, '11:35', '11:20', 9),
       (18, '2023-10-02', 25, '11:45', '11:20', 9),
       (19, '2023-10-02', 5, '09:25', '09:20', 10),
       (20, '2023-10-02', 5, '09:25', '09:20', 10),
       (21, '2023-10-02', 10, '15:30', '15:20', 11),
       (22, '2023-10-02', 10, '16:30', '16:20', 11),
       (23, '2023-10-02', 5, '11:25', '14:20', 12),
       (24, '2023-10-03', 5, '11:25', '14:20', 12),
       (25, '2023-10-03', 30, '12:30', '12:00', 1),
       (26, '2023-10-03', 15, '13:10', '12:55', 1),
       (27, '2023-10-03', 35, '11:55', '11:20', 2),
       (28, '2023-10-03', 45, '11:45', '11:00', 2),
       (29, '2023-10-03', 15, '11:35', '11:20', 3),
       (30, '2023-10-03', 25, '11:45', '11:20', 3),
       (31, '2023-10-03', 5, '09:25', '09:20', 4),
       (32, '2023-10-03', 5, '09:25', '09:20', 4),
       (33, '2023-10-03', 10, '15:30', '15:20', 5),
       (34, '2023-10-03', 10, '16:30', '16:20', 5),
       (35, '2023-10-03', 5, '11:25', '14:20', 6),
       (36, '2023-10-03', 5, '11:25', '14:20', 6),
       (37, '2023-10-03', 30, '12:30', '12:00', 7),
       (38, '2023-10-03', 15, '13:10', '12:55', 7),
       (39, '2023-10-03', 35, '11:55', '11:20', 8),
       (40, '2023-10-03', 45, '11:45', '11:00', 8),
       (41, '2023-10-03', 15, '11:35', '11:20', 9),
       (42, '2023-10-03', 25, '11:45', '11:20', 9),
       (43, '2023-10-03', 5, '09:25', '09:20', 10),
       (44, '2023-10-03', 5, '09:25', '09:20', 10),
       (45, '2023-10-03', 10, '15:30', '15:20', 11),
       (46, '2023-10-03', 10, '16:30', '16:20', 11),
       (47, '2023-10-03', 5, '11:25', '14:20', 12),
       (48, '2023-10-03', 5, '11:25', '14:20', 12),
       (49, '2023-10-04', 30, '12:30', '12:00', 1),
       (50, '2023-10-04', 15, '13:10', '12:55', 1),
       (51, '2023-10-04', 35, '11:55', '11:20', 2),
       (52, '2023-10-04', 45, '11:45', '11:00', 2),
       (53, '2023-10-04', 15, '11:35', '11:20', 3),
       (54, '2023-10-04', 25, '11:45', '11:20', 3),
       (55, '2023-10-04', 5, '09:25', '09:20', 4),
       (56, '2023-10-04', 5, '09:25', '09:20', 4),
       (57, '2023-10-04', 10, '15:30', '15:20', 5),
       (58, '2023-10-04', 10, '16:30', '16:20', 5),
       (59, '2023-10-04', 5, '11:25', '14:20', 6),
       (60, '2023-10-04', 5, '11:25', '14:20', 6),
       (61, '2023-10-04', 30, '12:30', '12:00', 7),
       (62, '2023-10-04', 15, '13:10', '12:55', 7),
       (63, '2023-10-04', 35, '11:55', '11:20', 8),
       (64, '2023-10-04', 45, '11:45', '11:00', 8),
       (65, '2023-10-04', 15, '11:35', '11:20', 9),
       (66, '2023-10-04', 25, '11:45', '11:20', 9),
       (67, '2023-10-04', 5, '09:25', '09:20', 10),
       (68, '2023-10-04', 5, '09:25', '09:20', 10),
       (69, '2023-10-04', 10, '15:30', '15:20', 11),
       (70, '2023-10-04', 10, '16:30', '16:20', 11),
       (71, '2023-10-04', 5, '11:25', '14:20', 12),
       (72, '2023-10-04', 5, '11:25', '14:20', 12),
       (73, '2023-10-05', 30, '12:30', '12:00', 1),
       (74, '2023-10-05', 15, '13:10', '12:55', 1),
       (75, '2023-10-05', 35, '11:55', '11:20', 2),
       (76, '2023-10-05', 45, '11:45', '11:00', 2),
       (77, '2023-10-05', 15, '11:35', '11:20', 3),
       (78, '2023-10-05', 25, '11:45', '11:20', 3),
       (79, '2023-10-05', 5, '09:25', '09:20', 4),
       (80, '2023-10-05', 5, '09:25', '09:20', 4),
       (81, '2023-10-05', 10, '15:30', '15:20', 5),
       (82, '2023-10-05', 10, '16:30', '16:20', 5),
       (83, '2023-10-05', 5, '11:25', '14:20', 6),
       (84, '2023-10-05', 5, '11:25', '14:20', 6),
       (85, '2023-10-05', 30, '12:30', '12:00', 7),
       (86, '2023-10-05', 15, '13:10', '12:55', 7),
       (87, '2023-10-05', 35, '11:55', '11:20', 8),
       (88, '2023-10-05', 45, '11:45', '11:00', 8),
       (89, '2023-10-05', 15, '11:35', '11:20', 9),
       (90, '2023-10-05', 25, '11:45', '11:20', 9),
       (91, '2023-10-05', 5, '09:25', '09:20', 10),
       (92, '2023-10-05', 5, '09:25', '09:20', 10),
       (93, '2023-10-05', 10, '15:30', '15:20', 11),
       (94, '2023-10-05', 10, '16:30', '16:20', 11),
       (95, '2023-10-05', 5, '11:25', '14:20', 12),
       (96, '2023-10-05', 5, '11:25', '14:20', 12),
       (97, '2023-10-06', 30, '12:30', '12:00', 1),
       (98, '2023-10-06', 15, '13:10', '12:55', 1),
       (99, '2023-10-06', 35, '11:55', '11:20', 2),
       (100, '2023-10-06', 45, '11:45', '11:00', 2),
       (101, '2023-10-06', 15, '11:35', '11:20', 3),
       (102, '2023-10-06', 25, '11:45', '11:20', 3),
       (103, '2023-10-06', 5, '09:25', '09:20', 4),
       (104, '2023-10-06', 5, '09:25', '09:20', 4),
       (105, '2023-10-06', 10, '15:30', '15:20', 5),
       (106, '2023-10-06', 10, '16:30', '16:20', 5),
       (107, '2023-10-06', 5, '11:25', '14:20', 6),
       (108, '2023-10-06', 5, '11:25', '14:20', 6),
       (109, '2023-10-06', 30, '12:30', '12:00', 7),
       (110, '2023-10-06', 15, '13:10', '12:55', 7),
       (111, '2023-10-06', 35, '11:55', '11:20', 8),
       (112, '2023-10-06', 45, '11:45', '11:00', 8),
       (113, '2023-10-06', 15, '11:35', '11:20', 9),
       (114, '2023-10-06', 25, '11:45', '11:20', 9),
       (115, '2023-10-06', 5, '09:25', '09:20', 10),
       (116, '2023-10-06', 5, '09:25', '09:20', 10),
       (117, '2023-10-06', 10, '15:30', '15:20', 11),
       (118, '2023-10-06', 10, '16:30', '16:20', 11),
       (119, '2023-10-06', 5, '11:25', '14:20', 12),
       (120, '2023-10-06', 5, '11:25', '14:20', 12)
ON CONFLICT (id)
    DO NOTHING;
