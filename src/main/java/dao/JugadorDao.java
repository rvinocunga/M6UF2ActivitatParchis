package dao;


import model.Jugador;

import java.util.List;

public interface JugadorDao {
    Jugador findById(int id);
    void save(Jugador jugador);
    void update(Jugador jugador);
    void delete(Jugador jugador);
    List<Jugador> findAll();
}
