package moduloEndereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.HorarioAtendimento;

@Repository
public interface HorarioAtendimentoRepository  extends JpaRepository<HorarioAtendimento,Short> {

	
}
