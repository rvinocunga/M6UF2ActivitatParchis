package dao;

import model.Fitxa;
import java.util.List;

public interface FitxaDao {
    Fitxa findById(int id) throws Exception;
    void insertar(Fitxa fitxa) throws Exception;
    void actualizar(Fitxa fitxa) throws Exception;
    void eliminar(Fitxa fitxa) throws Exception;
    List<Fitxa> listar() throws Exception;
    List<Fitxa> listarFichaJugadorEnPartida(int idJugador, int idPartida) throws Exception;
}
