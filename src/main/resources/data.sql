INSERT INTO auth_role (role_name, role_description) VALUES ('USER', 'permit to his data only');
INSERT INTO auth_role (role_name, role_description) VALUES ('ADMIN', 'all permissions');


INSERT INTO auth_user (first_name, last_name, username, password) VALUES ('mohit','vijay','mohitvijayv', '$2y$12$u9TFTLRdprcGtUqeOAjMGeYkIqhHq2fYb.CqIefKfsqq4QaWfeM8S');
INSERT INTO auth_user (first_name, last_name, username, password) VALUES ('mohit','vijay','mohitvijayv12', '$2y$12$u9TFTLRdprcGtUqeOAjMGeYkIqhHq2fYb.CqIefKfsqq4QaWfeM8S');
INSERT INTO auth_user (first_name, last_name, username, password) VALUES ('mohit','vijay','mohitvijayv13', '$2y$12$u9TFTLRdprcGtUqeOAjMGeYkIqhHq2fYb.CqIefKfsqq4QaWfeM8S');
INSERT INTO auth_user (first_name, last_name, username, password) VALUES ('mohit','vijay','mohitvijayv15', '$2y$12$u9TFTLRdprcGtUqeOAjMGeYkIqhHq2fYb.CqIefKfsqq4QaWfeM8S');
INSERT INTO auth_user (first_name, last_name, username, password) VALUES ('mohit','vijay','mohitvijayv14', '$2y$12$u9TFTLRdprcGtUqeOAjMGeYkIqhHq2fYb.CqIefKfsqq4QaWfeM8S');

INSERT INTO auth_user_role (auth_user_id, auth_role_id) VALUES (1, 2);
INSERT INTO auth_user_role (auth_user_id, auth_role_id) VALUES (2, 1);
INSERT INTO auth_user_role (auth_user_id, auth_role_id) VALUES (3, 1);
INSERT INTO auth_user_role (auth_user_id, auth_role_id) VALUES (4, 1);
INSERT INTO auth_user_role (auth_user_id, auth_role_id) VALUES (4, 2);
INSERT INTO auth_user_role (auth_user_id, auth_role_id) VALUES (5, 1);