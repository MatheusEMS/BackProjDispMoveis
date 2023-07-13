package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;
import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;

import java.util.ArrayList;


/*
* Classe DAO destianda somente para exemplificar a arquitetura MVC
* */
public class ClienteDAO {

    private static final ArrayList<Cliente> CLIENTES = new ArrayList<>();
    private static void pouplaArray(){
    }

    public ClienteDAO(){


    }



    public Cliente getCliente(){
        return new Cliente(52, "Pietra");
    }

    public Cliente incluir(Cliente c){

        CLIENTES.add(c);

        System.out.println("abrir conexão com banco de dados");
        System.out.println("Executar SQL");
        System.out.println("Salvar o cliente: "+c.getNome());

        return c;
    }

    public boolean deletar(Cliente c){

        System.out.println("abrir conexão com banco de dados");
        System.out.println("Executar SQL");
        System.out.println("DELETAR o cliente: "+c.getNome());

        return true;
    }

    public ArrayList<Cliente> getClientes(){
        return CLIENTES;
    }



}
