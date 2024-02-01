package dao;

import model.Casilla;
import java.util.List;

public interface CasillaDao {
    Casilla findById(int id);
    void save(Casilla casilla);
    void update(Casilla casilla);
    void delete(Casilla casilla);
    List<Casilla> findAll();
    List<Casilla> findByPartida(int idPartida);
}
