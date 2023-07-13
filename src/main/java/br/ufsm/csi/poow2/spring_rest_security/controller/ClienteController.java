package br.ufsm.csi.poow2.spring_rest_security.controller;

import br.ufsm.csi.poow2.spring_rest_security.dao.ClienteDAO;
import br.ufsm.csi.poow2.spring_rest_security.dao.UsuarioDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;
import br.ufsm.csi.poow2.spring_rest_security.model.Produto;
import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    //alencar - USER
    @GetMapping("/cliente")
    public Cliente getCliente(){
        return new ClienteDAO().getCliente();
    }

    //pietra - ADMIN
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/clientes")
    public ArrayList<Cliente> getClientes(){
        return  clienteDAO.getClientes();

    }



    @PostMapping("/incluir")
    public Cliente adicionar(@RequestParam(value = "id") int id, @RequestParam(value = "nome") String nome){

        return clienteDAO.incluir(new Cliente(id, nome));

    }

    @PostMapping("cadastrar")
    public String cadastrarCliente(@RequestBody Cliente cliente){


       // new ClienteDAO().incluir(cliente);

        if (clienteDAO.incluir(cliente) != null){
            return "Cliente cadastrado com sucesso";
        }else{
            return null;
        }
    }

    @GetMapping("/LerNome/{id}")
    public String getNome(@PathVariable String id){
        int idInt = Integer.parseInt(id);
        System.out.println("produtos"+usuarioDAO.getNome(idInt));
        return  usuarioDAO.getNome(idInt);

    }

}
