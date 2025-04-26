package com.example.noticias_api.dto;

public class NotaDTO {
    private Long noticiaId;
    private Boolean gostei;

    public NotaDTO() {
    }

    public NotaDTO(Long noticiaId, Boolean gostei) {
        this.noticiaId = noticiaId;
        this.gostei = gostei;
    }

    public Long getNoticiaId() {
        return noticiaId;
    }

    public void setNoticiaId(Long noticiaId) {
        this.noticiaId = noticiaId;
    }

    public Boolean getGostei() {
        return gostei;
    }

    public void setGostei(Boolean gostei) {
        this.gostei = gostei;
    }
}
