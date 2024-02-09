package dao;

import model.Partida;
import java.util.List;

public interface PartidaDao {
    Partida findById(int id) throws Exception ;
    void insertar(Partida partida) throws Exception;
    void actualizar(Partida partida) throws Exception;
    void eliminar(Partida partida) throws Exception;
    List<Partida> listarTodas() throws Exception;
    List<Partida> listarEnCurso() throws Exception;
}
