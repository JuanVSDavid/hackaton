package com.hackaton.padrino.services.pitchServices;

import java.util.List;
import java.util.Optional;

import com.hackaton.padrino.models.pitch;

public interface pitchService {
    public abstract List<pitch> listPitch();
    public abstract Optional<pitch> getPitchById(Long id);
    public abstract void deletePitchById(Long id);
    public abstract pitch updatePitch(pitch p);
    public abstract pitch savePitch(pitch p);
}
