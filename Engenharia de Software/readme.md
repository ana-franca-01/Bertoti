**texto 1**

What precisely do we mean by software engineering? What distinguishes “software engineering” from “programming” or “computer science”? And why would Google have
a unique perspective to add to the corpus of previous software engineering literature written over the past 50 years? The terms “programming” and “software 
engineering” have been used interchangeably for quite some time in our industry, although each term has a different emphasis and different implications. University 
students tend to study computer science and get jobs writing code as “programmers.” “Software engineering,” however, sounds more serious, as if it implies the 
application of some theoretical knowledge to build something real and precise. Mechanical engineers, civil engineers, aeronautical engineers, and those in other 
engineering disciplines all practice engineering. They all work in the real world and use the application of their theoretical knowledge to create something real.
Software engineers also create “something real,” though it is less tangible than the things other engineers create. Unlike those more established engineering 
professions, current software engineering theory or practice is not nearly as rigorous. Aeronautical engineers must follow rigid guidelines and practices, because 
errors in their calculations can cause real damage; programming, on the whole, has traditionally not followed such rigorous practices. But, as software becomes 
more integrated into our lives, we must adopt and rely on more rigorous engineering methods. We hope this book helps others see a path toward more reliable 
software practices.

**comentário**
1- Software engineering vai além de apenas programar. Enquanto muitos estudantes de Ciência da Computação acabam atuando como programadores, o termo “engenharia 
de software” transmite a ideia de aplicar conhecimentos teóricos para criar algo concreto, mesmo que menos tangível que obras da engenharia civil ou mecânica.
Diferente dessas áreas, que seguem normas rigorosas para evitar erros críticos, o desenvolvimento de software historicamente não adotou práticas tão estruturadas.
No entanto, com a crescente presença da tecnologia no cotidiano, torna-se essencial aplicar métodos mais rigorosos para garantir confiabilidade. É nesse contexto
que o Google traz uma visão própria, baseada em sua experiência, para contribuir com práticas mais sólidas na área.

**texto 2**
 
We propose that “software engineering” encompasses not just the act of writing code, but all of the tools and processes an organization uses to build and maintain
that code over time. What practices can a software organization introduce that will best keep its code valuable over the long term? How can engineers make a 
codebase more sustainable and the software engineering discipline itself more rigorous? We don’t have fundamental answers to these questions, but we hope that 
Google’s collective experience over the past two decades illuminates possible paths toward finding those answers. One key insight we share in this book is that 
software engineering can be thought of as “programming integrated over time.” What practices can we introduce to our code to make it sustainable—able to react to 
necessary change—over its life cycle, from conception to introduction to maintenance to deprecation?
 
The book emphasizes three fundamental principles that we feel software organizations should keep in mind when designing, architecting, and writing their code:
 
Time and Change
How code will need to adapt over the length of its life
Scale and Growth
 
How an organization will need to adapt as it evolves
Trade-offs and Costs 
How an organization makes decisions, based on the lessons of Time and Change and Scale and Growth

**comentário**
2- Engenharia de software envolve mais do que escrever código — inclui também as ferramentas e processos usados para criar e manter esse código ao longo do tempo.
O desafio é definir práticas que mantenham o código útil e sustentável durante todo o seu ciclo de vida, desde a concepção até a descontinuação. O Google, com sua 
experiência de duas décadas, propõe que engenharia de software seja vista como “programação integrada ao longo do tempo”, destacando a importância de preparar o 
código para mudanças inevitáveis.

O livro apresenta três princípios essenciais para guiar organizações de software:

* **Tempo e Mudança** – como o código deve se adaptar ao longo de sua vida útil.
* **Escala e Crescimento** – como a organização precisa evoluir à medida que se expande.
* **Compensações e Custos** – como tomar decisões equilibradas considerando os dois princípios anteriores.

**questão 3**

tres exemplos de tradeoffs são:
* **Escabilidade X simplicidade** :
  Um código simples é mais rápido e mais fácil de ser codificado, porém ele muito provavelmente não vai funcionar em larga escala. Todavia, um código feiro próprio para uma larga escala e grande número de usuário, muito provavelmnete será mais complexo e vai demorar mais de ser produzido.
* **SQL X NoSQL** :
  O SQL é ótimo para dados com muitas relações ele é consistente e muito bem organizado, porém não se torna tão funcional quando aplicado em larga escala, em contra partida o NoSQL se faz muito melhor adaptado em operações em larga escala, mas perde na cosistência e organização de dados.
* **Otimização de desempenho X Clareza e manutenibilidade** :
  Um código otimizado pode ser mais rápido, pórem mais cpmplicado de se editar  e se torna um código complexo. Por outro lado um código claro e mais facil de entender e editar na maioria das vezes nao tem o melhor desempeenho que poderia atingir se tornando um código mmais lento

  **questão 4**
  Anexado no arquivo chamado - UML-AEROPORTO

  **questao 5**

package airplane;

public class Passageiro {
	
	private String nome;
	private int rg;
	
	public Passageiro(String nome, int rg) {
		this.nome = nome;
		this.rg = rg;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome=nome;
	}
	
	public int getRg() {
		return rg;
	}
	
	public void setRg (int rg) {
		this.rg= rg;
	}
	
	@Override
	public String toString() {
		return " Nome: " + nome;
	}
}

package airplane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Voo {
	
	private String destino;
	private int tempo;
	private List<Passageiro> passageiros;
	
	
	public Voo(String destino, int tempo) {
		this.destino = destino;
		this.tempo = tempo;
		this.passageiros= new ArrayList<>();
	}
	
	public Voo() {
		
	}
	
	
	public void adicionarPassageiro(Passageiro passageiro) {
		passageiros.add(passageiro);
	}
	
	public List<Passageiro> buscarPassageiroNome(String nome){
		List<Passageiro> encontrados = new LinkedList<Passageiro>();
			for (Passageiro passageiro:passageiros) {
				if(passageiro.getNome().equals(nome)) encontrados.add(passageiro);
			}
			
			return encontrados;
	}
	
	
	
	
	
	public List<Passageiro> getPassageiros(){
		return passageiros;
	}
	 
	public String getDestino() {
		return destino;
	}
	
	public void setDestino (String 	destino) {
		this.destino = destino;
	}
	
	public int getTempo() {
		return tempo;
	}
	
	public void setTempo (int tempo) {
		this.tempo = tempo;
	}
	
	@Override
	public String toString() {
		return " Destino: " + destino + " Tempo: " + tempo +"h"+ " Passageiros: " + passageiros ;
	}
	

}

package airplane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Aviao {
		
		private String nome;
		private Voo voo;
		private List<Voo> voos = new ArrayList<>();
		
		public Aviao (String nome, Voo voo) {
			this.nome = nome;
			this.voo = voo;
		}
		
		public String getNome() {			return nome;
		}		
		public void setNome (String nome) {
			this.nome=nome;
		}
		
		
		public void adicionarVoo(Voo voo) {
			voos.add(voo);
		}
		public List<Voo> buscarVooDestino(String destino){
			List<Voo> encontrados = new LinkedList<Voo>();
				for (Voo voo:voos) {
					if(voo.getDestino().equals(destino)) encontrados.add(voo);
				}
				
				return encontrados;
		}
		
		public Voo getVoo(){
			return voo;
		}
		
		public void setVoo(Voo voo) {
			this.voo = voo;
		}
		
		@Override
		public String toString() {
			return " Nome: " + nome + voo;
		}
		
		

	}


**questao 6**


package airplane;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;

class teste {

	@Test
	void test() {
		
		Voo voo = new Voo("sjc", 3);
		voo.adicionarPassageiro(new Passageiro("passageiro1", 123));
		voo.adicionarPassageiro(new Passageiro("passageiro2", 345));
		
		Aviao aviao = new Aviao("aviao1", voo);
		
		aviao.adicionarVoo(new Voo("campinas", 2));
		
		List<Passageiro> buscaP1 = voo.buscarPassageiroNome("passageiro1");
		assertEquals(buscaP1.get(0).getRg(), 123);
		
		List<Passageiro> buscaP2 = voo.buscarPassageiroNome("passageiro2");
		assertEquals(buscaP2.get(0).getRg(), 345);
		
		List<Voo> buscaV = aviao.buscarVooDestino("campinas");
		assertEquals(buscaV.get(0).getTempo(), 2);

		
		
		
		System.out.print(aviao);
		
	}

}

**questao 7**

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

public class Livro {
    private String autor;
    private String isbn;
    private String nome;

    public Livro(String autor, String isbn, String nome) {
        this.autor = autor;
        this.isbn = isbn;
        this.nome = nome;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getNome() {
        return this.nome;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

public class Usuario {
    private String nome;
    private int ra;

    public Usuario(String nome, int ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRa() {
        return this.ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }
}

package org.example;

import org.example.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    // Adicionar usuário ao banco
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario(nome, ra) VALUES(?,?)";
        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setInt(2, usuario.getRa());
            pstmt.executeUpdate();
            System.out.println("Usuário adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    // Adicionar livro ao banco
    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO Livro(nome, isbn, autor) VALUES(?, ?, ?)";
        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livro.getNome());
            pstmt.setString(2, livro.getIsbn());
            pstmt.setString(3, livro.getAutor());
            pstmt.executeUpdate();
            System.out.println("Livro adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    // Buscar usuário por nome
    public List<Usuario> buscarUsuarioNome(String nome) {
        List<Usuario> encontrados = new ArrayList<>();
        String sql = "SELECT * FROM Usuario WHERE nome = ?";
        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario(rs.getString("nome"), rs.getInt("ra"));
                encontrados.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return encontrados;
    }

    // Buscar livro por ISBN
    public Livro buscarLivro(String isbn) {
        String sql = "SELECT * FROM Livro WHERE isbn = ?";
        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isbn);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Livro(rs.getString("autor"), rs.getString("isbn"), rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
        return null;
    }
}

package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:meubanco.db";

    // Conectar ao banco
    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            criarTabelas(conn); // Cria as tabelas se não existirem
            return conn;
        } catch (SQLException e) {
            return null;
        }
    }

    // Criar tabelas
    private static void criarTabelas(Connection conn) {
        String sqlUsuario = """
            CREATE TABLE IF NOT EXISTS Usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                ra int not null UNIQUE
            );
            """;

        String sqlLivro = """
            CREATE TABLE IF NOT EXISTS Livro (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                isbn TEXT NOT NULL UNIQUE,
                autor TEXT NOT NULL
            );
            """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUsuario);
            stmt.execute(sqlLivro);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}


package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Database.conectar();

        Biblioteca biblioteca = new Biblioteca();

        // Criar usuários e livros
        Usuario usuario1 = new Usuario("Ana", 567);
        Usuario usuario2 = new Usuario("Carlos", 789);

        Livro livro1 = new Livro("Aprendendo Java", "000000", "titulo1");
        Livro livro2 = new Livro("SQLite na Prática", "76543", "titulo2");

        // Adicionar ao banco
        biblioteca.adicionarUsuario(usuario1);
        biblioteca.adicionarUsuario(usuario2);
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        // Buscar usuário
        List<Usuario> encontrados = biblioteca.buscarUsuarioNome("Ana");
        System.out.println("Usuários encontrados:");
        for (Usuario u : encontrados) {
            System.out.println("- " + u.getNome() + u.getRa());
        }

        // Buscar livro
        Livro livroBuscado = biblioteca.buscarLivro("123456");
        if (livroBuscado != null) {
            System.out.println("Livro encontrado: " + livroBuscado.getNome());
        } else {
            System.out.println("Livro não encontrado!");
        }

    }

}

  

  
