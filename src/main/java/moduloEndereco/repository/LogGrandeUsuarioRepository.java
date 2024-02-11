package moduloEndereco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloEndereco.model.LogGrandeUsuario;

@Repository
public interface LogGrandeUsuarioRepository  extends  JpaRepository<LogGrandeUsuario,Integer>{
	List<LogGrandeUsuario> findByCep(String cep);
}
