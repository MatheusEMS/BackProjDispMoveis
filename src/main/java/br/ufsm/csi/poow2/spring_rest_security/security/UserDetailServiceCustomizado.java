package br.ufsm.csi.poow2.spring_rest_security.security;

import br.ufsm.csi.poow2.spring_rest_security.dao.UsuarioDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username1: "+username);

        Usuario usuario = new UsuarioDAO().getUsuario(username);


        if(usuario == null){
            throw  new UsernameNotFoundException("Usuário ou senha inválidos");
        }else{

            UserDetails user = User.withUsername(usuario.getEmail())
                    .password(usuario.getSenha()).authorities(usuario.getPermissao()).build();
            System.out.println("id: "+usuario.getId());
            System.out.println("nome: "+usuario.getNome());
            System.out.println("permissao: "+usuario.getPermissao());
            return user;
        }
    }


}
