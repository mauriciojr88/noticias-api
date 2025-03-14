package com.example.noticias_api.controller;

import com.example.noticias_api.model.Noticia;
import com.example.noticias_api.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @PostMapping
    public Noticia criarNoticia(@RequestBody Noticia noticia) {
        return noticiaService.salvarNoticia(noticia);
    }

    @GetMapping
    public List<Noticia> listarNoticias() {
        return noticiaService.listarNoticias();
    }

    @GetMapping("/{id}")
    public Noticia buscarNoticiaPorId(@PathVariable Long id) {
        return noticiaService.buscarNoticiaPorId(id);
    }

    @PutMapping("/{id}")
    public Noticia atualizarNoticia(@PathVariable Long id, @RequestBody Noticia noticia) {
        return noticiaService.atualizarNoticia(id, noticia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirNoticia(@PathVariable Long id) {
        try {
            noticiaService.excluirNoticia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
