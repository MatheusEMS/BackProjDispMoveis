package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;
import br.ufsm.csi.poow2.spring_rest_security.model.Produto;
import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

public class UsuarioDAO {

    private static final ArrayList<Usuario> CLIENTES = new ArrayList<>();


    public Usuario getUsuario(String email) {
        System.out.println(email);

        for (Usuario usuario : CLIENTES) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }

        return null;
    }

    public String getNome(int id){
        for (Usuario usuario : CLIENTES) {
            if (usuario.getId().equals(id)) {
                return usuario.getNome();
            }
        }

        return null;
    }

    public Usuario editar(Usuario usuarioedit){


        for (Usuario usuario : CLIENTES) {
            System.out.println("id cliente lista: "+usuario.getId());
            if (usuario.getId().equals(usuarioedit.getId())) {
                System.out.println("chegou aqui");
                usuario.setNome(usuarioedit.getNome());
                usuario.setEmail(usuarioedit.getEmail());
                System.out.println("nome: "+usuario.getNome());
                System.out.println("email: "+usuario.getEmail());
                return usuario;
            }
        }
       return null;
    }


    public Usuario incluir(Usuario usuario){

        String newSenha = new BCryptPasswordEncoder().encode(usuario.getSenha());

        usuario.setSenha(newSenha);

        CLIENTES.add(usuario);

        System.out.println(CLIENTES);

        System.out.println("abrir conexão com banco de dados");
        System.out.println("Executar SQL");
        System.out.println("Salvar o cliente: "+usuario.getNome());

        return usuario;
    }

}
