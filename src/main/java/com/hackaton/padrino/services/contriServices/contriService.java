package com.hackaton.padrino.services.contriServices;

import java.util.List;

import com.hackaton.padrino.models.contribucion;

public interface contriService {
    public abstract List<contribucion> listContriForId_Pitch(Long id_pitch);
    public abstract List<contribucion> listContriForId_usuario(Long id_usuario);
    public abstract contribucion saveContribucion (contribucion c);
    public abstract contribucion updateContribucion (contribucion c);
    public abstract void deleteContribucion (Long id);
}
