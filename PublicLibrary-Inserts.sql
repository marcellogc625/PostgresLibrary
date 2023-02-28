INSERT INTO authors (name, fname) VALUES 
('George Orwell', 'Eric Arthur Blair'), 
('Aldous Huxley', 'Aldous Leonard Huxley'),
('Gabriel García Márquez', 'Gabriel José de la Concordia García Márquez'),
('Ernest Hemingway', 'Ernest Miller Hemingway'),
('Franz Kafka', 'Franz Kafka'),
('Friedrich Nietzsche', 'Friedrich Wilhelm Nietzsche'),
('Hermann Hesse', 'Hermann Karl Hesse'),
('Jorge Luis Borges', 'Jorge Francisco Isidoro Luis Borges Acevedo'),
('Leo Tolstoy', 'Lev Nikolayevich Tolstoy'),
('Marcel Proust', 'Valentin Louis Georges Eugène Marcel Proust'),
('Mark Twain', 'Samuel Langhorne Clemens'),
('Milan Kundera', 'Milan Kundera'),
('Paulo Coelho', 'Paulo Coelho de Souza'),
('Philip Roth', 'Philip Milton Roth'),
('Ray Bradbury', 'Ray Douglas Bradbury'),
('Stephen King', 'Stephen Edwin King'),
('Sylvia Plath', 'Sylvia Plath'),
('Virginia Woolf', 'Adeline Virginia Woolf'),
('William Faulkner', 'William Cuthbert Faulkner'),
('Yukio Mishima', 'Kimitake Hiraoka');

INSERT INTO publishers (name, url)
VALUES 
('Editora Abril', 'https://www.abril.com.br/'),
('Editora Aleph', 'https://www.editoraaleph.com.br/'),
('Editora Arqueiro', 'https://www.editoraarqueiro.com.br/'),
('Editora Atlas', 'https://www.grupogen.com.br/atlas'),
('Editora Autêntica', 'https://www.autenticaeditora.com.br/'),
('Editora Companhia das Letras', 'https://www.companhiadasletras.com.br/'),
('Editora Contexto', 'https://www.editoracontexto.com.br/'),
('Editora Darkside', 'https://www.darksidebooks.com.br/'),
('Editora Intrínseca', 'https://www.intrinseca.com.br/'),
('Editora Melhoramentos', 'https://www.editoramelhoramentos.com.br/'),
('Editora Nova Fronteira', 'https://www.novafronteira.com.br/'),
('Editora Planeta', 'https://www.planetadelivros.com.br/'),
('Editora Record', 'https://www.record.com.br/'),
('Editora Rocco', 'https://www.rocco.com.br/'),
('Editora Sextante', 'https://www.sextante.com.br/'),
('Editora Suma', 'https://www.suma.com.br/'),
('Editora Todavia', 'https://todavialivros.com.br/'),
('Editora Unesp', 'https://editoraunesp.com.br/home'),
('Editora Vozes', 'https://www.vozes.com.br/'),
('Editora Zahar', 'https://www.zahar.com.br/');

INSERT INTO books (name, isbn, publisher_id, price) VALUES
('1984', '9780451524935', 1, 20.99),
('Admirável Mundo Novo', '9788535906310', 2, 25.00),
('Cem Anos de Solidão', '9788501046614', 3, 35.00),
('O Velho e o Mar', '9780684801223', 4, 18.00),
('A Metamorfose', '9788582850293', 5, 15.00),
('Assim Falou Zaratustra', '9788532604297', 6, 19.90),
('O Lobo da Estepe', '9788535911987', 7, 22.90),
('O Aleph', '9788535913493', 8, 16.99),
('Guerra e Paz', '9788577991015', 9, 42.00),
('Em Busca do Tempo Perdido - Volume 1: No Caminho de Swann', '9788535909748', 10, 30.00),
('As Aventuras de Tom Sawyer', '9788539427375', 11, 10.00),
('A Insustentável Leveza do Ser', '9788532501256', 12, 28.90),
('O Complexo de Portnoy', '9788535905979', 13, 25.00),
('Fahrenheit 451', '9788525414645', 14, 23.90),
('O Iluminado', '9788581051043', 15, 35.00),
('A Redoma de Vidro', '9788532500754', 16, 21.90),
('Mrs. Dalloway', '9788580440456', 17, 19.99),
('O Som e a Fúria', '9788577992333', 18, 26.00),
('Confissões do Ego', '9788532510289', 19, 32.90),
('O Samurai', '9788577992241', 20, 24.00);

INSERT INTO bookauthors (isbn, author_id, seq_no) VALUES
('9780451524935', 1, 1),
('9788535906310', 2, 1),
('9788501046614', 3, 1),
('9780684801223', 4, 1),
('9788582850293', 5, 1),
('9788532604297', 6, 1),
('9788535911987', 7, 1),
('9788535913493', 8, 1),
('9788577991015', 9, 1),
('9788535909748', 10, 1),
('9788539427375', 11, 1),
('9788532501256', 12, 1),
('9788535905979', 13, 1),
('9788525414645', 14, 1),
('9788581051043', 15, 1),
('9788532500754', 16, 1),
('9788580440456', 17, 1),
('9788577992333', 18, 1),
('9788532510289', 19, 1),
('9788577992241', 20, 1);