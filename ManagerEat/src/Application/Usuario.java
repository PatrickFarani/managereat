package Application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Usuario {
    private String login;
    private String senha;
    private String nome;
    private TipoUsuario tipo;
    private LocalDate dataCadastro;
    private boolean ativo;

    public enum TipoUsuario {
        ADMIN, FUNCIONARIO, CLIENTE
    }

    public Usuario(String login, String senha, String nome, TipoUsuario tipo) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
        this.dataCadastro = LocalDate.now();
        this.ativo = true;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha) && this.ativo;
    }

    public boolean isClienteAntigo() {
        long mesesCadastro = ChronoUnit.MONTHS.between(dataCadastro, LocalDate.now());
        return mesesCadastro >= 6;
    }

    public boolean podeGerenciarLanches() {
        return tipo == TipoUsuario.ADMIN || tipo == TipoUsuario.FUNCIONARIO;
    }

    public boolean podeGerenciarIngredientes() {
        return tipo == TipoUsuario.ADMIN;
    }

    // Getters e Setters
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public TipoUsuario getTipo() { return tipo; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", nome, login, tipo);
    }
}