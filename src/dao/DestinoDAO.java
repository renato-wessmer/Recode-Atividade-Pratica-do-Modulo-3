package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DestinoDtoMetadata;
import model.Destino;

public class DestinoDAO {

	public static String sql;
    private final Connection connection;
    private final ClienteDAO clienteDao;
    public DestinoDAO(Connection connection, ClienteDAO clienteDao) {
        this.connection = connection;
        this.clienteDao = clienteDao;
    }
    
    public void create(Destino destino, long clienteId) {
        sql = "INSERT INTO %s VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
        sql = String.format(sql, DestinoDtoMetadata.tableName);

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, destino.getNome());
            preparedStatement.setString(2, destino.getCidade());
            preparedStatement.setString(3, destino.getEstado());
            preparedStatement.setDate(4, new Date(destino.getDataIda().getTime()));
            preparedStatement.setDate(5, new Date(destino.getDataVolta().getTime()));
            preparedStatement.setFloat(6, destino.getValorIntegral());
            preparedStatement.setLong(7, clienteId);

            int result = preparedStatement.executeUpdate();
            System.out.printf("[LOG] Destino inserida com sucesso. Result: %s", result);

        } catch (SQLException e) {
            System.out.printf("[ERROR] Destino n�o foi inserido. Message:\n%s", e.getMessage());
        }
    }
    
    public List<Destino> findAll() {
        sql = "SELECT * FROM %s";
        sql = String.format(sql, DestinoDtoMetadata.tableName);

        try {
            Statement statement = this.connection.createStatement();
            var result = statement.executeQuery(sql);
            List<Destino> destinos = new ArrayList<>();

            while (result.next()) {
                Destino destino = new Destino();
                destino.setId(result.getLong(DestinoDtoMetadata.id));
                destino.setNome(result.getString(DestinoDtoMetadata.nome));
                destino.setCidade(result.getString(DestinoDtoMetadata.cidade));
                destino.setEstado(result.getString(DestinoDtoMetadata.estado));
                destino.setDataIda(result.getDate(DestinoDtoMetadata.dataIda));
                destino.setDataVolta(result.getDate(DestinoDtoMetadata.dataVolta));
                destino.setValorIntegral(result.getFloat(DestinoDtoMetadata.valorIntegral));
                destino.setCliente(clienteDao.findByPk(result.getLong(DestinoDtoMetadata.cliente)));
                destinos.add(destino);
            }
            System.out.println("[LOG] Busca por destino realizada com sucesso.");
            return destinos;

        } catch (SQLException e) {
            System.out.printf("[ERROR] busca por destino falhou. Message:\n%s", e.getMessage());
            return null;
        }
    }
    
    public List<Destino> findByPk(long destinoId) {
        sql = "SELECT * FROM %s WHERE %s = ?";
        sql = String.format(
                sql,
                DestinoDtoMetadata.tableName,
                DestinoDtoMetadata.id
        );

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, destinoId);
            var result = preparedStatement.executeQuery();
            List<Destino> destinos = new ArrayList<>();

            while (result.next()) {
            	Destino destino = new Destino();
                destino.setId(result.getLong(DestinoDtoMetadata.id));
                destino.setNome(result.getString(DestinoDtoMetadata.nome));
                destino.setCidade(result.getString(DestinoDtoMetadata.cidade));
                destino.setEstado(result.getString(DestinoDtoMetadata.estado));
                destino.setDataIda(result.getDate(DestinoDtoMetadata.dataIda));
                destino.setDataVolta(result.getDate(DestinoDtoMetadata.dataVolta));
                destino.setValorIntegral(result.getFloat(DestinoDtoMetadata.valorIntegral));
                destinos.add(destino);
            }

            System.out.println("[LOG] Query PK no banco de dados.");
            return destinos;

        } catch (SQLException e) {
            System.out.printf("[ERROR] Cant query one Client in database. Message:\n%s", e.getMessage());
            return null;
        }
    }
    
    public void delete(long destinoId) {
        sql = "DELETE FROM %s WHERE %s = ?";
        sql = String.format(
                sql,
                DestinoDtoMetadata.tableName,
                DestinoDtoMetadata.id
        );

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, destinoId);
            preparedStatement.executeUpdate();
            System.out.println("[LOG] destino excluida com sucesso.");
        } catch (SQLException e) {
            System.out.printf("[ERROR] exclus�o destino falhou. Message:\n%s", e.getMessage());
        }
    }

    public void update(Destino destino) {
        sql = "UPDATE %s " +
                "SET %s = ?, %s = ? %s = ?, %s = ? %s = ?, %s = ? %s = ?" +
                "WHERE %s = ?";
        sql = String.format(
                sql,
                DestinoDtoMetadata.tableName,
                DestinoDtoMetadata.nome,
                DestinoDtoMetadata.cidade,
                DestinoDtoMetadata.estado,
                DestinoDtoMetadata.dataIda,
                DestinoDtoMetadata.dataVolta,
                DestinoDtoMetadata.valorIntegral,
                DestinoDtoMetadata.cliente,
                DestinoDtoMetadata.id
                
        );

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, destino.getNome());
            preparedStatement.setString(2, destino.getCidade());
            preparedStatement.setString(3, destino.getEstado());
            preparedStatement.setDate(4, new Date(destino.getDataIda().getTime()));
            preparedStatement.setDate(5, new Date(destino.getDataVolta().getTime()));
            preparedStatement.setFloat(6, destino.getValorIntegral());
            preparedStatement.setLong(7, destino.getId());
            preparedStatement.executeUpdate();
            System.out.println("[LOG] Atualiza��o realizada com sucesso.");
        } catch (SQLException e) {
            System.out.printf("[ERROR] Atualiza��o falhou. Message:\n%s", e.getMessage());
        }
    }

}
