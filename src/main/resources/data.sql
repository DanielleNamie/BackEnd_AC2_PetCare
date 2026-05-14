INSERT INTO setor (nome, descricao) VALUES ('Tecnologia', 'Área de desenvolvimento de software');
INSERT INTO setor (nome, descricao) VALUES ('Marketing', 'Área de comunicação e campanhas');
INSERT INTO setor (nome, descricao) VALUES ('Recursos Humanos', 'Área de gestão de pessoas');

INSERT INTO funcionario (nome, email, cargo, setor_id) VALUES ('Gabriel Capelini', 'g.capelini@devjoy.com', 'Desenvolvedor Sênior', 1);
INSERT INTO funcionario (nome, email, cargo, setor_id) VALUES ('Júlia Moraes', 'j.moraes@devjoy.com', 'Designer UX', 2);
INSERT INTO funcionario (nome, email, cargo, setor_id) VALUES ('Danielle Namie', 'd.namie@devjoy.com', 'Gerente de RH', 3);
INSERT INTO funcionario (nome, email, cargo, setor_id) VALUES ('Melissa Yukari', 'm.yukari@devjoy.com', 'Desenvolvedora Pleno', 1);

INSERT INTO projeto (nome, descricao, data_inicio, data_fim, status) VALUES ('Portal Corporativo', 'Novo portal web da empresa', '2025-08-15', '2025-11-28', 'CONCLUIDO');
INSERT INTO projeto (nome, descricao, data_inicio, data_fim, status) VALUES ('App Mobile', 'Aplicativo para clientes', '2026-02-16', '2026-06-15', 'EM_ANDAMENTO');
INSERT INTO projeto (nome, descricao, data_inicio, data_fim, status) VALUES ('Campanha de divulgação do App', 'Marketing para lançamento do App', '2026-07-06', '2026-08-30', 'PLANEJADO');

INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (1, 1);
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (1, 4);
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (2, 1);
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (3, 2);

INSERT INTO TUTOR (NOME, TELEFONE) VALUES ('Gabriel', '159882222');

INSERT INTO ANIMAL (NOME, ESPECIE, TUTOR_ID) VALUES ('Snoopy', 'Cachorro', 1);