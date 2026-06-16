--
-- Banco de dados: `hackathon`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `alunos`
--

CREATE TABLE `alunos` (
  `id` int NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ra` int NOT NULL,
  `senha` varchar(100) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `curso` varchar(100) NOT NULL,
  `periodo` int NOT NULL,
  `data_nasc` date NOT NULL,
  `apto` tinyint NOT NULL DEFAULT '0',
  `endereco_aluno` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `candidaturas`
--

CREATE TABLE `candidaturas` (
  `id` int NOT NULL,
  `aluno_id` int NOT NULL,
  `vagas_id` int NOT NULL,
  `data_candidatura` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- --------------------------------------------------------

--
-- Estrutura para tabela `contratos`
--

CREATE TABLE `contratos` (
  `id` int NOT NULL,
  `vagas_id` int NOT NULL,
  `aluno_id` int NOT NULL,
  `date_inicio` date NOT NULL,
  `data_fim` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- --------------------------------------------------------

--
-- Estrutura para tabela `empresas`
--

CREATE TABLE `empresas` (
  `id` int NOT NULL,
  `razao_social` varchar(100) NOT NULL,
  `cnpj` varchar(14) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `telefone_contato` varchar(11) NOT NULL,
  `responsavel` varchar(100) NOT NULL,
  `status` tinyint NOT NULL,
  `endereco_empresa` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `endereco_aluno`
--

CREATE TABLE `endereco_aluno` (
  `id` int NOT NULL,
  `logradouro` varchar(100) NOT NULL,
  `num_logradouro` varchar(6) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `uf` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `endereco_empresa`
--

CREATE TABLE `endereco_empresa` (
  `id` int NOT NULL,
  `logradouro` varchar(100) NOT NULL,
  `num_logradouro` varchar(6) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `uf` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- --------------------------------------------------------

--
-- Estrutura para tabela `operacoes_aluno`
--

CREATE TABLE `operacoes_aluno` (
  `id` int NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `data` date NOT NULL,
  `operadores_id` int NOT NULL,
  `aluno_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `operacoes_empresa`
--

CREATE TABLE `operacoes_empresa` (
  `id` int NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `date_operacao` datetime NOT NULL,
  `operadores_id` int NOT NULL,
  `empresas_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- --------------------------------------------------------

--
-- Estrutura para tabela `operadores`
--

CREATE TABLE `operadores` (
  `id` int NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cargo` varchar(100) NOT NULL,
  `data_contratacao` date NOT NULL,
  `data_recisao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `vagas`
--

CREATE TABLE `vagas` (
  `id` int NOT NULL,
  `cargo` varchar(100) NOT NULL,
  `vaga_preenchida` tinyint NOT NULL,
  `data_abertura` date NOT NULL,
  `data_fechamento` date NOT NULL,
  `requisitos` varchar(200) NOT NULL,
  `empresas_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
