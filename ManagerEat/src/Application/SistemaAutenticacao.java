package Application;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SistemaAutenticacao {
    private static Map<String, Usuario> usuarios = new HashMap<>();
    private static Usuario usuarioLogado = null;

    static {
        // Usuários padrão do sistema
        usuarios.put("admin", new Usuario("admin", "admin123", "Administrador", Usuario.TipoUsuario.ADMIN));
        usuarios.put("func", new Usuario("func", "func123", "Funcionário", Usuario.TipoUsuario.FUNCIONARIO));
        
        // Cliente antigo (6+ meses) para teste de desconto
        Usuario clienteAntigo = new Usuario("cliente1", "123", "Cliente Antigo", Usuario.TipoUsuario.CLIENTE);
        // Simulando cliente cadastrado há 8 meses (será ajustado internamente)
        usuarios.put("cliente1", clienteAntigo);
        
        usuarios.put("cliente2", new Usuario("cliente2", "123", "Cliente Novo", Usuario.TipoUsuario.CLIENTE));
    }

    public static boolean login(String login, String senha) {
        Usuario usuario = usuarios.get(login);
        if (usuario != null && usuario.autenticar(senha)) {
            usuarioLogado = usuario;
            return true;
        }
        return false;
    }

    public static void logout() {
        usuarioLogado = null;
    }

    public static boolean isLogado() {
        return usuarioLogado != null;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static boolean podeGerenciarLanches() {
        return usuarioLogado != null && usuarioLogado.podeGerenciarLanches();
    }

    public static boolean podeGerenciarIngredientes() {
        return usuarioLogado != null && usuarioLogado.podeGerenciarIngredientes();
    }

    public static void cadastrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getLogin(), usuario);
    }

    public static Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public static String[] getCredenciaisDemo() {
        return new String[]{
            "📋 CREDENCIAIS DE DEMONSTRAÇÃO:",
            "👤 Admin: login=admin, senha=admin123",
            "👨‍💼 Funcionário: login=func, senha=func123", 
            "🙋‍♂️ Cliente Antigo: login=cliente1, senha=123 (desconto 5%)",
            "🙋‍♀️ Cliente Novo: login=cliente2, senha=123"
        };
    }
}