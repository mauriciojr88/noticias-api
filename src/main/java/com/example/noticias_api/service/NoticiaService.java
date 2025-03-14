package com.example.noticias_api.service;

import com.example.noticias_api.model.Noticia;
import com.example.noticias_api.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public Noticia buscarNoticiaPorId(Long id) {
        Optional<Noticia> noticia = noticiaRepository.findById(id);
        return noticia.orElseThrow(() -> new RuntimeException("Notícia não encontrada"));
    }

    public List<Noticia> listarNoticias() {
        return noticiaRepository.findAll();
    }

    public Noticia atualizarNoticia(Long id, Noticia noticiaAtualizada) {
        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notícia não encontrada"));

        noticia.setTitulo(noticiaAtualizada.getTitulo());
        noticia.setConteudo(noticiaAtualizada.getConteudo());
        noticia.setAutor(noticiaAtualizada.getAutor());
        noticia.setDataPublicacao(noticiaAtualizada.getDataPublicacao());

        return noticiaRepository.save(noticia);
    }

    public Noticia salvarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public void excluirNoticia(Long id) {
        if (noticiaRepository.existsById(id)) {
            noticiaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notícia não encontrada");
        }
    }
}
