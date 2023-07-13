package br.ufsm.csi.poow2.spring_rest_security.controller;

import br.ufsm.csi.poow2.spring_rest_security.dao.ProdutoDAO;
import br.ufsm.csi.poow2.spring_rest_security.dao.UsuarioDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;
import br.ufsm.csi.poow2.spring_rest_security.model.Produto;
import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private static ProdutoDAO produtoDAO = new ProdutoDAO();

    @PostMapping("/add")
    public Produto addProduto(@RequestBody Produto produto){

        Random random = new Random();

// Gerar um número inteiro aleatório entre 0 e 9
        int numeroAleatorio = random.nextInt(1000);

        System.out.println("idusuario: "+produto.getIdUsuario());
        System.out.println("nome: "+produto.getNome());
        System.out.println("valor: "+produto.getValor());
        System.out.println("quantd: "+produto.getQuantidade());
        System.out.println("data: "+produto.getDataColheita());


        produtoDAO.incluir(new Produto(numeroAleatorio, produto.getNome(),produto.getValor()
                ,produto.getQuantidade(),produto.getDataColheita(),produto.getIdUsuario()));

        return produto;
    }

    @PostMapping("/EditarProduto")
    public Produto editarProduto(@RequestBody Produto produto){


        System.out.println("id: "+produto.getId());
        System.out.println("nome: "+produto.getNome());
        System.out.println("valor: "+produto.getValor());
        System.out.println("quantd: "+produto.getQuantidade());
        System.out.println("data: "+produto.getDataColheita());


        produtoDAO.editar(new Produto(produto.getId(), produto.getNome(),produto.getValor()
                ,produto.getQuantidade(),produto.getDataColheita(),produto.getIdUsuario()));

        return produto;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/readAllProduto/{id}")
    public ArrayList<Produto> getProdutos(@PathVariable String id){
        int idConsole = Integer.parseInt(id);
        System.out.println("produtos"+produtoDAO.getProdutos(idConsole));
        return  produtoDAO.getProdutos(idConsole);

    }

    @GetMapping("/readAllProdutoFruta/{nome}")
    public ArrayList<Produto> getProdutosNome(@PathVariable String nome){
        System.out.println("produtos"+produtoDAO.getProdutosFruta(nome));
        return  produtoDAO.getProdutosFruta(nome);

    }

    @PostMapping("/deletar")
    public Produto excluirProduto(@RequestBody Produto produto){
        System.out.println(produto.getId());

        produtoDAO.deletar(produto.getId());

        return produto;
    }

    @PostMapping("/Pagamento")
    public ResponseEntity<Object> Pagamento(@RequestBody Produto produto){
        System.out.println(produto.getId());
        System.out.println(produto.getQuantidade());

        produtoDAO.pagamento(produto.getId(),produto.getQuantidade());

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

}
