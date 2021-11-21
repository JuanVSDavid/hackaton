package com.hackaton.padrino.services.pitchServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hackaton.padrino.models.pitch;
import com.hackaton.padrino.repositories.pitchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pitchService")
public class IPitchService implements pitchService {

    @Autowired
    @Qualifier("pitchRepository")
    private pitchRepository pr;

    public static String FechaFull() {
        Calendar calendario = new GregorianCalendar();
        return String.valueOf(calendario.get(Calendar.YEAR)) + "-" + String.valueOf(calendario.get(Calendar.MONTH))
                + "-" + String.valueOf(calendario.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public List<pitch> listPitch() {
        List<pitch> list = pr.findAll();
        Date actual = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        return list.stream().filter(p -> {
            try {
                String[] date_final = p.getFecha_final().split("-");
                return p.getEstado() == true && actual.before(date.parse(date_final[0]+"/"+date_final[1]+"/"+date_final[2]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<pitch> getPitchById(Long id) {
        return pr.findById(id);
    }

    @Override
    public void deletePitchById(Long id) {
        pr.deleteById(id);
    };

    @Override
    public pitch updatePitch(pitch p) {
        return pr.save(p);
    };

    @Override
    public pitch savePitch(pitch p) {
        p.setFecha_inicial(FechaFull());
        return pr.save(p);
    };

}
