package dao;


import model.Jugador;

import java.util.List;

public interface JugadorDao {
    Jugador findById(int id) throws Exception;
    void insertar(Jugador jugador) throws Exception;
    void actualizar(Jugador jugador) throws Exception;
    void eliminar(Jugador jugador) throws Exception;
    List<Jugador> listar() throws Exception;
}
