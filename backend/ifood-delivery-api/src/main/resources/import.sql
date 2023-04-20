insert into tb_cozinha(nome) values ('Indiana');
insert into tb_cozinha(nome) values ('Tailandesa');
insert into tb_cozinha (nome) values ('Argentina');
insert into tb_cozinha (nome) values ('Brasileira');

insert into tb_estado (nome) values ('Minas Gerais');
insert into tb_estado (nome) values ('São Paulo');
insert into tb_estado (nome) values ('Ceará');

insert into tb_cidade (nome, estado_id) values ('Uberlândia', 1);
insert into tb_cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into tb_cidade ( nome, estado_id) values ('São Paulo', 2);
insert into tb_cidade (nome, estado_id) values ('Campinas', 2);
insert into tb_cidade (nome, estado_id) values ('Fortaleza', 3);

insert into tb_restaurante (nome, taxa_frete, cozinha_id, ativo, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values ('Thai Gourmet', 10, 1, 1, NOW(), NOW(), 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into tb_restaurante (nome, taxa_frete, cozinha_id, ativo, data_cadastro, data_atualizacao) values ('Thai Delivery', 9.50, 1, 1, NOW(), NOW());
insert into tb_restaurante (nome, taxa_frete, cozinha_id, ativo, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida Indiana', 15, 2, 1, NOW(), NOW());
insert into tb_restaurante (nome, taxa_frete, cozinha_id, ativo, data_cadastro, data_atualizacao) values ('Java Steakhouse', 12, 3, 1, NOW(), NOW());
insert into tb_restaurante (nome, taxa_frete, cozinha_id, ativo, data_cadastro, data_atualizacao) values ('Lanchonete do Tio Sam', 11, 4, 1, NOW(), NOW());
insert into tb_restaurante (nome, taxa_frete, cozinha_id, ativo, data_cadastro, data_atualizacao) values ('Bar da Maria', 6, 4, 1, NOW(), NOW());

insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into tb_forma_pagamento (descricao) values ('Cartão de crédito');
insert into tb_forma_pagamento (descricao) values ('Cartão de débito');
insert into tb_forma_pagamento (descricao) values ('Dinheiro');

insert into tb_permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tb_restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
