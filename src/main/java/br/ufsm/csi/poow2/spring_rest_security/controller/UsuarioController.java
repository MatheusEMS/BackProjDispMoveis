package br.ufsm.csi.poow2.spring_rest_security.controller;

import br.ufsm.csi.poow2.spring_rest_security.dao.ClienteDAO;
import br.ufsm.csi.poow2.spring_rest_security.dao.UsuarioDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;
import br.ufsm.csi.poow2.spring_rest_security.model.Produto;
import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Random;

public class UsuarioController {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();


    public Usuario addUsuario(Usuario usuario){

        Random random = new Random();

// Gerar um número inteiro aleatório entre 0 e 9
        int numeroAleatorio = random.nextInt(1000);


        usuarioDAO.incluir(new Usuario(numeroAleatorio, usuario.getNome(),usuario.getEmail(),usuario.getSenha(),"USER"));

        return usuario;
    }

    public Usuario editarUsuario(Usuario usuario){

        usuarioDAO.editar(usuario);

        return usuario;
    }


}
