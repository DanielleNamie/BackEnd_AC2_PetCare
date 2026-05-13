-- ============================================================
--  DADOS INICIAIS — inseridos automaticamente ao subir o app
--  Útil para já ter dados prontos na apresentação
-- ============================================================

-- Setores
INSERT INTO setor (nome, descricao) VALUES ('Tecnologia', 'Área de desenvolvimento de software');
INSERT INTO setor (nome, descricao) VALUES ('Marketing', 'Área de comunicação e campanhas');
INSERT INTO setor (nome, descricao) VALUES ('Recursos Humanos', 'Área de gestão de pessoas');

-- Funcionários
INSERT INTO funcionario (nome, email, cargo, setor_id) VALUES ('Ana Silva', 'ana@empresa.com', 'Desenvolvedora Sênior', 1);
INSERT INTO funcionario (nome, email, cargo, setor_id) VALUES ('Carlos Souza', 'carlos@empresa.com', 'Designer UX', 2);
INSERT INTO funcionario (nome, email, cargo, setor_id) VALUES ('Maria Oliveira', 'maria@empresa.com', 'Gerente de RH', 3);
INSERT INTO funcionario (nome, email, cargo, setor_id) VALUES ('João Lima', 'joao@empresa.com', 'Desenvolvedor Pleno', 1);

-- Projetos
INSERT INTO projeto (nome, descricao, data_inicio, data_fim, status) VALUES ('Portal Corporativo', 'Novo portal web da empresa', '2024-01-15', '2024-06-30', 'EM_ANDAMENTO');
INSERT INTO projeto (nome, descricao, data_inicio, data_fim, status) VALUES ('App Mobile', 'Aplicativo para clientes', '2024-03-01', '2024-12-31', 'PLANEJADO');
INSERT INTO projeto (nome, descricao, data_inicio, data_fim, status) VALUES ('Campanha Verão', 'Marketing para temporada', '2024-11-01', '2025-02-28', 'EM_ANDAMENTO');

-- Vínculos Funcionário <-> Projeto
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (1, 1);
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (1, 4);
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (2, 1);
INSERT INTO projeto_funcionario (projeto_id, funcionario_id) VALUES (3, 2);