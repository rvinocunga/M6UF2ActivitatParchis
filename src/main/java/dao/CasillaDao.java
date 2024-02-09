package dao;

import model.Casilla;
import java.util.List;

public interface CasillaDao {
    Casilla findById(int id) throws Exception;
    void insertar(Casilla casilla) throws Exception;
    void actualizar(Casilla casilla) throws Exception;
    void eliminar(Casilla casilla) throws Exception;
    List<Casilla> listar() throws Exception;
    List<Casilla> listarPorPartida(int idPartida) throws Exception;
}
