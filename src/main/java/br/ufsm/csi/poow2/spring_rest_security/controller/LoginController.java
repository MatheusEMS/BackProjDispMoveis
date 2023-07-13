package br.ufsm.csi.poow2.spring_rest_security.controller;

import br.ufsm.csi.poow2.spring_rest_security.dao.UsuarioDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;
import br.ufsm.csi.poow2.spring_rest_security.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody Usuario usuario){


        try{
                final Authentication authentication = this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));

                if(authentication.isAuthenticated()){
                    //colocamos nossa instancia autenticada no contexto do spring security
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    System.out.println("Gerando token de autorizacao ****");
                   String token = new JWTUtil().geraToken(usuario);

                    Usuario user2 = new UsuarioDAO().getUsuario(usuario.getEmail());

                   usuario.setToken(token);
                   usuario.setSenha("");
                   usuario.setId(user2.getId());
                   usuario.setNome(user2.getNome());
                   usuario.setPermissao(user2.getPermissao());

                    System.out.println("id: "+usuario.getId());
                    System.out.println("nome: "+usuario.getNome());
                    System.out.println("email: "+usuario.getEmail());
                    System.out.println("senha: "+usuario.getSenha());
                    System.out.println("permissao: "+usuario.getPermissao());


                    return new ResponseEntity<>(usuario, HttpStatus.OK);
                }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/CriarConta")
    public ResponseEntity<Object> CriarConta(@RequestBody Usuario usuario){

        System.out.println("id: "+usuario.getId());
        System.out.println("nome: "+usuario.getNome());
        System.out.println("email: "+usuario.getEmail());
        System.out.println("senha: "+usuario.getSenha());
        System.out.println("permissao: "+usuario.getPermissao());

        UsuarioController usuarioController = new UsuarioController();

        usuarioController.addUsuario(usuario);


        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/EditarConta")
    public ResponseEntity<Object> EditarConta(@RequestBody Usuario usuario){

        System.out.println("id: "+usuario.getId());
        System.out.println("nome: "+usuario.getNome());
        System.out.println("email: "+usuario.getEmail());


        //int id = Integer.parseInt(usuario.getId());
        //usuario.setId();

        UsuarioController usuarioController = new UsuarioController();

        usuarioController.editarUsuario(usuario);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
