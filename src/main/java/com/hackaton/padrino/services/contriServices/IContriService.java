package com.hackaton.padrino.services.contriServices;

import java.util.List;

import com.hackaton.padrino.models.contribucion;
import com.hackaton.padrino.repositories.contribucionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("contriService")
public class IContriService implements contriService{

    @Autowired
    @Qualifier("contribucionRepository")
    private contribucionRepository cr;

    @Override
    public List<contribucion> listContriForId_Pitch(Long id_pitch) {
        return cr.ListForId_Pitch(id_pitch);
    }

    @Override
    public contribucion saveContribucion(contribucion c) {
        return cr.save(c);
    }

    @Override
    public contribucion updateContribucion(contribucion c) {
        if(cr.existsById(c.getId_contribucion())){
            return cr.save(c);
        }
        return null;
    }

    @Override
    public void deleteContribucion(Long id) {
        if(!cr.existsById(id)){
            cr.deleteById(id);
        }
    }
    
}
