# ProyectoAvance
package com.curso.curso.controllers;

import com.curso.curso.dao.UsuarioDao;
import com.curso.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario obj = new Usuario();
        obj.setId(id);
        obj.setNombre("Joel");
        obj.setApellido("Condori");
        obj.setEmail("joeleduca87@gmail.com");
        obj.setTelefono("70169627");
        return obj;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registarUser(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    public Usuario editar(){
        Usuario obj = new Usuario();
        obj.setNombre("Joel");
        obj.setApellido("Condori");
        obj.setEmail("joeleduca87@gmail.com");
        obj.setTelefono("70169627");
        return obj;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar( @PathVariable Long id ){
        usuarioDao.eliminar(id);
    }

    public Usuario buscar(){
        Usuario obj = new Usuario();
        obj.setNombre("Joel");
        obj.setApellido("Condori");
        obj.setEmail("joeleduca87@gmail.com");
        obj.setTelefono("70169627");
        return obj;
    }
}
