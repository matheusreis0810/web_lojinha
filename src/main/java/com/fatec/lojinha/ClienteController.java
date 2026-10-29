package com.fatec.lojinha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    private ClienteRepository bd;

    @GetMapping("/api/cliente/{codigo}")
    public Cliente carregarDetalhe(@PathVariable("codigo") int id) {
        if (bd.existsById(id)) {
            return bd.findById(id).get();
        } else {
            return new Cliente();
        }
    }

    @PostMapping("/api/cliente/")
    public String gravar(@RequestBody Cliente obj) {
        bd.save(obj);
        return "Cliente gravado com sucesso " + obj.getNome();
    }

    @PutMapping("/api/cliente/")
    public String atualizar(@RequestBody Cliente obj) {
        if (bd.existsById(obj.getCodigo())) {
            bd.save(obj);
            return "Cliente " + obj.getCodigo() + " atualizado com sucesso " + obj.getNome();
        } else {
            return "Cliente não existe!";
        }
    }

    @DeleteMapping("/api/cliente/{codigo}")
    public String excluir(@PathVariable("codigo") int id) {
        if (bd.existsById(id)) {
            bd.deleteById(id);
            return "Cliente " + id + " excluído com sucesso";
        } else {
            return "Cliente não existe!";
        }
    }
    
    @PostMapping("/api/cliente/login")
    public Cliente login(@RequestParam("email") String email, 
                        @RequestParam("senha") String senha) {
        Cliente obj = bd.login(email, senha);
        if (obj != null) {
            return obj;
        } else {
            return new Cliente();
        }
    }
}