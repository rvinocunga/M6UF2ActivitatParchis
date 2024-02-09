package dao;

import model.Fitxa;
import java.util.List;

public interface FitxaDao {
    Fitxa findById(int id);
    void save(Fitxa fitxa);
    void update(Fitxa fitxa);
    void delete(Fitxa fitxa);
    List<Fitxa> findAll();
    List<Fitxa> findByJugadorAndPartida(int idJugador, int idPartida);
	List<Fitxa> findByPartida(int idPartida);
}
