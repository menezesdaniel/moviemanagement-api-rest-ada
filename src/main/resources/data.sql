-- Arquivo data.sql - Dados iniciais para o banco H2
-- Coloque este arquivo em src/main/resources/data.sql

INSERT INTO filmes (titulo, diretor, ano_lancamento, genero, sinopse, duracao_minutos, avaliacao, data_cadastro, data_atualizacao) VALUES
('A Origem', 'Christopher Nolan', 2010, 'Ficção Científica', 'Um ladrão que rouba segredos corporativos através do uso da tecnologia de compartilhamento de sonhos recebe a tarefa inversa de plantar uma ideia na mente de um CEO.', 148, 8.8, CURRENT_DATE, CURRENT_DATE),

('O Poderoso Chefão', 'Francis Ford Coppola', 1972, 'Drama/Crime', 'O patriarca de uma dinastia do crime organizado transfere o controle de seu império clandestino para seu filho relutante.', 175, 9.2, CURRENT_DATE, CURRENT_DATE),

('Pulp Fiction', 'Quentin Tarantino', 1994, 'Crime/Drama', 'As vidas de dois assassinos da máfia, um boxeador, a esposa de um gângster e dois bandidos se entrelaçam em quatro histórias de violência e redenção.', 154, 8.9, CURRENT_DATE, CURRENT_DATE),

('Matrix', 'Lana Wachowski, Lilly Wachowski', 1999, 'Ficção Científica', 'Um hacker descobre que sua realidade é uma simulação criada por máquinas e se junta a uma rebelião para libertá-la.', 136, 8.7, CURRENT_DATE, CURRENT_DATE),

('Forrest Gump', 'Robert Zemeckis', 1994, 'Drama/Romance', 'As presidências de Kennedy e Johnson, a Guerra do Vietnã, o escândalo de Watergate e outros eventos históricos se desenrolam através da perspectiva de um homem do Alabama com QI de 75.', 142, 8.8, CURRENT_DATE, CURRENT_DATE),

('Interestelar', 'Christopher Nolan', 2014, 'Ficção Científica/Drama', 'Uma equipe de exploradores viaja através de um buraco de minhoca no espaço em uma tentativa de garantir a sobrevivência da humanidade.', 169, 8.6, CURRENT_DATE, CURRENT_DATE),

('Clube da Luta', 'David Fincher', 1999, 'Drama', 'Um funcionário de escritório insone e um fabricante de sabão formam um clube de luta underground que evolui para algo muito mais.', 139, 8.8, CURRENT_DATE, CURRENT_DATE),

('Gladiador', 'Ridley Scott', 2000, 'Ação/Drama', 'Um ex-general romano busca vingança contra o corrupto imperador que assassinou sua família e o enviou à escravidão.', 155, 8.5, CURRENT_DATE, CURRENT_DATE),

('O Cavaleiro das Trevas', 'Christopher Nolan', 2008, 'Ação/Crime', 'Quando a ameaça conhecida como Coringa causa caos e destruição em Gotham, Batman deve aceitar um dos maiores testes psicológicos e físicos de sua capacidade de lutar contra a injustiça.', 152, 9.0, CURRENT_DATE, CURRENT_DATE),

('Vingadores: Ultimato', 'Anthony Russo, Joe Russo', 2019, 'Ação/Aventura', 'Após os eventos devastadores de Vingadores: Guerra Infinita, os Vingadores se reúnem mais uma vez para reverter as ações de Thanos e restaurar o equilíbrio no universo.', 181, 8.4, CURRENT_DATE, CURRENT_DATE);