package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;
import br.ufsm.csi.poow2.spring_rest_security.model.Produto;
import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

public class ProdutoDAO {

    private static final ArrayList<Produto> PRODUTOS = new ArrayList<>();


    public Produto incluir(Produto produto){

        PRODUTOS.add(produto);

        System.out.println(PRODUTOS);


        return produto;
    }

    public Produto editar(Produto produtoedit){
        for (Produto produto : PRODUTOS) {
            if (produto.getId().equals(produtoedit.getId())) {
                produto.setNome(produtoedit.getNome());
                produto.setValor(produtoedit.getValor());
                produto.setDataColheita(produtoedit.getDataColheita());
                produto.setQuantidade(produtoedit.getQuantidade());
            }
        }
        return produtoedit;
    }

    public ArrayList<Produto> getProdutos(int id){
        ArrayList<Produto> produtosComMesmoId = new ArrayList<>();

        for (Produto produto : PRODUTOS) {
            if (produto.getIdUsuario() == id) {
                produtosComMesmoId.add(produto);
            }
        }
        return produtosComMesmoId;
    }

    public ArrayList<Produto> getProdutosFruta(String nome){
        ArrayList<Produto> produtosComMesmoNome = new ArrayList<>();

        for (Produto produto : PRODUTOS) {
            if (produto.getNome().equals(nome)) {
                produtosComMesmoNome.add(produto);
            }
        }
        return produtosComMesmoNome;
    }

    public int deletar(int id){
        for (Produto produto : PRODUTOS) {
            if (produto.getId().equals(id)) {
                PRODUTOS.remove(produto);

            }
        }
        return id;
    }

    public Produto pagamento(int id,String quantd){

        for (Produto produto : PRODUTOS) {
            if (produto.getId().equals(id)) {
                produto.setQuantidade(quantd);
                return produto;
            }
        }
        return null;
    }
}
