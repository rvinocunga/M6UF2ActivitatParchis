package dao;

import model.Partida;
import java.util.List;

public interface PartidaDao {
    Partida findById(int id);
    void save(Partida partida);
    void update(Partida partida);
    void delete(Partida partida);
    List<Partida> findAll();
    List<Partida> findEnCurso();
}
