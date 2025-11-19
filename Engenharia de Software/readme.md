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
1-Engenharia de software vai além de só programar. Embora muitos de nós de C.C. viremos programadores, o termo "engenharia" sugere aplicar teoria para criar algo real. O detalhe é que, diferente da engenharia civil, nosso "real" é menos tangível; software é feito de lógica e dados, não de tijolos.

Historicamente, talvez por ser assim mais "abstrato", o desenvolvimento de software não teve o mesmo rigor que outras engenharias, onde um erro de cálculo pode causar um dano físico grave.

Contudo, como hoje tudo depende de tecnologia, aplicar métodos mais rigorosos se tornou essencial para garantir a confiabilidade. É nesse ponto que a experiência prática de empresas como o Google entra, ajudando a definir práticas melhores para a área.

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
2-Engenharia de software envolve mais do que escrever código — inclui também as ferramentas e processos usados para criar e manter esse código ao longo do tempo. O desafio é definir práticas que mantenham o código útil e sustentável durante todo o seu ciclo de vida, desde a concepção até a descontinuação. O Google, com sua experiência de duas décadas, propõe que engenharia de software seja vista como “programação integrada ao longo do tempo”, destacando a importância de preparar o código para mudanças inevitáveis.

O livro apresenta três princípios essenciais para guiar organizações de software:

Tempo e Mudança – como o código deve se adaptar ao longo de sua vida útil.

Escala e Crescimento – como a organização precisa evoluir à medida que se expande. Isso não se aplica apenas à equipe, mas também à arquitetura do próprio software, que deve ser capaz de escalar para atender com eficiência tanto a um único usuário quanto a um milhão de usuários simultaneamente.

Compensações e Custos – como tomar decisões equilibradas considerando os dois princípios anteriores.

**questão 3**

tres exemplos de tradeoffs são:
* **Escabilidade X simplicidade** :
  Um código simples é mais rápido e mais fácil de ser codificado, porém ele muito provavelmente não vai funcionar em larga escala. Todavia, um código feiro próprio para uma larga escala e grande número de usuário, muito provavelmnete será mais complexo e vai demorar mais de ser produzido.
* **SQL X NoSQL** :
    O SQL é ótimo para dados com muitas relações, ele é consistente e muito bem organizado (ex: **um sistema bancário**), porém não se torna tão funcional quando aplicado em larga escala. Em contrapartida, o NoSQL se faz muito melhor adaptado em operações em larga escala (ex: **o feed do Instagram**), mas perde na consistência imediata e na organização rigorosa dos dados, fazendo um trade-off em favor da velocidade.
* **Otimização de desempenho X Clareza e manutenibilidade** :
  Um código otimizado pode ser mais rápido, pórem mais cpmplicado de se editar  e se torna um código complexo. Por outro lado um código claro e mais facil de entender e editar na maioria das vezes nao tem o melhor desempeenho que poderia atingir se tornando um código mmais lento

  **questão 4**
  Anexado no arquivo chamado - UML-AEROPORTO

  **questao 5**

package org.example;

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
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getRg() {
        return rg;
    }
    public void setRg(int rg) {
        this.rg = rg;
    }
    @Override
    public String toString() {
        return " Nome: " + nome;
    }
}


package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Voo {
    private String destino;
    private int tempo;
    private int codigo;
    private List<Passageiro> passageiros;


    public Voo(String destino, int tempo, int codigo) {
        this.destino = destino;
        this.tempo = tempo;
        this.codigo = codigo;
        this.passageiros= new ArrayList<>();
    }

    public void addPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }

    public List<Passageiro> buscarPassageiroNome(String nome){
        List<Passageiro> nm_encontrados = new LinkedList<Passageiro>();
        for (Passageiro passageiro:passageiros) {
            if(passageiro.getNome().equals(nome)) nm_encontrados.add(passageiro);
        }

        return nm_encontrados;
    }

    public List<Passageiro> buscarPassageiroRg(int rg) {
        List<Passageiro> rg_encontrados = new LinkedList<Passageiro>();
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getRg() == rg) {
                rg_encontrados.add(passageiro);
            }
        }
        return rg_encontrados;
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

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo (int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return " Destino: " + destino + " Tempo: " + tempo +" horas "+ " Código: " + codigo + " Passageiros: " + passageiros ;
    }
}

package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Aeroporto {
    private List<Voo> voos;
    private String nome;

    public Aeroporto(String nome) {
        this.voos = new ArrayList<>();
        this.nome = nome;
    }
    public List<Voo> getVoos() {
        return voos;
    }
    public void addVoo(Voo voo) {
        voos.add(voo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public List<Voo> buscarVooCodigo(int codigo) {
        List<Voo> cod_encontrados = new LinkedList<Voo>();
        for (Voo voo : voos) {
            if (voo.getCodigo() == codigo) {
                cod_encontrados.add(voo);
            }
        }
        return cod_encontrados;
    }

    public List<Voo> buscarVooDestino(String destino){
        List<Voo> dest_encontrados = new LinkedList<Voo>();
        for (Voo voo:voos) {
            if(voo.getDestino().equals(destino)) dest_encontrados.add(voo);
        }

        return dest_encontrados;
    }
    @Override
    public String toString() {
        return " Voo: " + voos;
    }
}


**questao 6**


package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Aeroporto aeroporto = new Aeroporto("guarulhos");

        Voo vooSjc = new Voo("sjc", 3, 6);
        vooSjc.addPassageiro(new Passageiro("passageiro1", 123));
        vooSjc.addPassageiro(new Passageiro("passageiro2", 345));

        Voo vooSp= new Voo("sp", 4, 999);
        vooSp.addPassageiro(new Passageiro("passageiro3", 000));
        vooSp.addPassageiro(new Passageiro("passageiro4", 111));

        Voo vooAju= new Voo("aju", 4, 456);
        vooAju.addPassageiro(new Passageiro("passageiro5", 222));
        vooAju.addPassageiro(new Passageiro("passageiro6", 333));

        aeroporto.addVoo(vooSjc);
        aeroporto.addVoo(vooSp);
        aeroporto.addVoo(vooAju);

        System.out.println(aeroporto);

        System.out.println("\nBUSCANDO Voos com destino 'sp'...");
        List<Voo> voosParaSpEncontrados = aeroporto.buscarVooDestino("sp");

        if (voosParaSpEncontrados.isEmpty()) {
            System.out.println("Nenhum voo encontrado para 'sp'.");
        } else {
            System.out.println("Resultado: " + voosParaSpEncontrados);
        }

        System.out.println("\nBUSCANDO Voo com código 456 (era 123)...");
        List<Voo> vooPorCodigo = aeroporto.buscarVooCodigo(456);

        if (vooPorCodigo.isEmpty()) {
            System.out.println("Nenhum voo encontrado com código 456.");
        } else {
            System.out.println("Resultado: " + vooPorCodigo);
        }

        System.out.println("\nBUSCANDO Passageiro 'passageiro3' no voo para 'sp'...");

        Voo vooSpEncontrado = voosParaSpEncontrados.get(0);

        List<Passageiro> passageirosNoVooSp = vooSpEncontrado.buscarPassageiroNome("passageiro3");

        if (passageirosNoVooSp.isEmpty()) {
            System.out.println("não tem passageiro com esse nome");
        } else {
            System.out.println("Resultado: " + passageirosNoVooSp);
        }

        System.out.println("\nBUSCANDO Passageiro com RG 333 no voo para 'aju' (cód 456)...");

        Voo vooAjuEncontrado = vooPorCodigo.get(0);
        List<Passageiro> passageiroRg333 = vooAjuEncontrado.buscarPassageiroRg(333);

        if (passageiroRg333.isEmpty()) {
            System.out.println("Nenhum passageiro com RG 333 encontrado.");
        } else {
            System.out.println("Resultado: " + passageiroRg333);
        }
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

  

  
