package duoc.cl.cachupin.admin.service;

import duoc.cl.cachupin.admin.dto.client.CitaRequest;
import duoc.cl.cachupin.admin.model.ModelCita;
import duoc.cl.cachupin.admin.repository.CitaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;

    /**
     * Agendar cita desde el formulario del usuario logueado.
     */
    public ModelCita agendarCita(CitaRequest request, String correoUsuario, String uidUsuario) {
        ModelCita cita = new ModelCita();

        // Datos del formulario
        cita.setNombre(request.getNombre());
        cita.setTelefono(request.getTelefono());
        cita.setMascota(request.getMascota());
        cita.setServicio(request.getServicio());
        cita.setFecha(request.getFecha());
        cita.setHora(request.getHora());
        cita.setComentarios(request.getComentarios());

        // Datos del usuario logueado (desde Firebase)
        cita.setCorreoUsuario(correoUsuario);
        cita.setUidUsuario(uidUsuario);

        return citaRepository.save(cita);
    }

    /**
     * Lista todas las citas asociadas a un correo de usuario (ej: "Mis citas").
     */
    public List<ModelCita> listarCitasPorCorreo(String correoUsuario) {
        return citaRepository.findByCorreoUsuario(correoUsuario);
    }

    /**
     * Lista todas las citas (para panel admin).
     */
    public List<ModelCita> listarTodas() {
        return citaRepository.findAll();
    }

    /**
     * Elimina una cita por ID (solo admin).
     */
    public void eliminar(Long idCita) {
        if (!citaRepository.existsById(idCita)) {
            throw new EntityNotFoundException("Cita no encontrada con id " + idCita);
        }
        citaRepository.deleteById(idCita);
    }
}
