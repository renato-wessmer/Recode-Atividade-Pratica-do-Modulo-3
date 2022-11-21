package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.HospedagemDtoMetadata;
import model.Hospedagem;

public class HospedagemDAO {

	public static String sql;
    private final Connection connection;
    private final ClienteDAO clienteDao;
    public HospedagemDAO(Connection connection, ClienteDAO clienteDao) {
        this.connection = connection;
        this.clienteDao = clienteDao;
    }
    
    public void create(Hospedagem hospedagem, long clienteId) {
        sql = "INSERT INTO %s VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        sql = String.format(sql, HospedagemDtoMetadata.tableName);

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, hospedagem.getNomeHotel());
            preparedStatement.setString(2, hospedagem.getEndereço());
            preparedStatement.setString(3, hospedagem.getEstado());
            preparedStatement.setFloat(4, hospedagem.getValorIntegral());
            preparedStatement.setDate(5, new Date(hospedagem.getCheckIn().getTime()));
            preparedStatement.setDate(6, new Date(hospedagem.getCheckOut().getTime()));
            preparedStatement.setString(7, hospedagem.getNumeroHotel());
            preparedStatement.setLong(8, clienteId);

            int result = preparedStatement.executeUpdate();
            System.out.printf("[LOG] Hospedagem inserida com sucesso. Result: %s", result);

        } catch (SQLException e) {
            System.out.printf("[ERROR] Hospedagem n�o foi inserido. Message:\n%s", e.getMessage());
        }
    }
    
    public List<Hospedagem> findAll() {
        sql = "SELECT * FROM %s";
        sql = String.format(sql, HospedagemDtoMetadata.tableName);

        try {
            Statement statement = this.connection.createStatement();
            var result = statement.executeQuery(sql);
            List<Hospedagem> hospedagems = new ArrayList<>();

            while (result.next()) {
                Hospedagem hospedagem = new Hospedagem();
                hospedagem.setId(result.getLong(HospedagemDtoMetadata.id));
                hospedagem.setNomeHotel(result.getString(HospedagemDtoMetadata.nomeHotel));
                hospedagem.setEndereço(result.getString(HospedagemDtoMetadata.endereco));
                hospedagem.setEstado(result.getString(HospedagemDtoMetadata.estado));
                hospedagem.setValorIntegral(result.getFloat(HospedagemDtoMetadata.valorIntegral));
                hospedagem.setCheckIn(result.getDate(HospedagemDtoMetadata.checkIn));
                hospedagem.setCheckOut(result.getDate(HospedagemDtoMetadata.checkOut));
                hospedagem.setNumeroHotel(result.getString(HospedagemDtoMetadata.numeroHotel));
                hospedagem.setCliente(clienteDao.findByPk(result.getLong(HospedagemDtoMetadata.cliente)));
                hospedagems.add(hospedagem);
            }
            System.out.println("[LOG] Busca por hospedagem realizada com sucesso.");
            return hospedagems;

        } catch (SQLException e) {
            System.out.printf("[ERROR] busca por hospedagem falhou. Message:\n%s", e.getMessage());
            return null;
        }
    }
    
    public List<Hospedagem> findByPk(long hospedagemId) {
        sql = "SELECT * FROM %s WHERE %s = ?";
        sql = String.format(
                sql,
                HospedagemDtoMetadata.tableName,
                HospedagemDtoMetadata.id
        );

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, hospedagemId);
            var result = preparedStatement.executeQuery();
            List<Hospedagem> hospedagems = new ArrayList<>();

            while (result.next()) {
            	Hospedagem hospedagem = new Hospedagem();
                hospedagem.setId(result.getLong(HospedagemDtoMetadata.id));
                hospedagem.setNomeHotel(result.getString(HospedagemDtoMetadata.nomeHotel));
                hospedagem.setEndereço(result.getString(HospedagemDtoMetadata.endereco));
                hospedagem.setEstado(result.getString(HospedagemDtoMetadata.estado));
                hospedagem.setValorIntegral(result.getFloat(HospedagemDtoMetadata.valorIntegral));
                hospedagem.setCheckIn(result.getDate(HospedagemDtoMetadata.checkIn));
                hospedagem.setCheckOut(result.getDate(HospedagemDtoMetadata.checkOut));
                hospedagem.setNumeroHotel(result.getString(HospedagemDtoMetadata.numeroHotel));
                hospedagems.add(hospedagem);
            }

            System.out.println("[LOG] Query PK no banco de dados.");
            return hospedagems;

        } catch (SQLException e) {
            System.out.printf("[ERROR] Cant query one Client in database. Message:\n%s", e.getMessage());
            return null;
        }
    }
    
    
    public void delete(long hospedagemId) {
        sql = "DELETE FROM %s WHERE %s = ?";
        sql = String.format(
                sql,
                HospedagemDtoMetadata.tableName,
                HospedagemDtoMetadata.id
        );

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, hospedagemId);
            preparedStatement.executeUpdate();
            System.out.println("[LOG] Hospedagem excluida com sucesso.");
        } catch (SQLException e) {
            System.out.printf("[ERROR] exclus�o hospedagem falhou. Message:\n%s", e.getMessage());
        }
    }

    public void update(Hospedagem hospedagem) {
        sql = "UPDATE %s " +
                "SET %s = ?, %s = ? %s = ?, %s = ? %s = ?, %s = ? %s = ?, %s = ? " +
                "WHERE %s = ?";
        sql = String.format(
                sql,
                HospedagemDtoMetadata.tableName,
                HospedagemDtoMetadata.nomeHotel,
                HospedagemDtoMetadata.endereco,
                HospedagemDtoMetadata.estado,
                HospedagemDtoMetadata.valorIntegral,
                HospedagemDtoMetadata.checkIn,
                HospedagemDtoMetadata.checkOut,
                HospedagemDtoMetadata.numeroHotel,
                HospedagemDtoMetadata.cliente,
                HospedagemDtoMetadata.id
                
        );

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, hospedagem.getNomeHotel());
            preparedStatement.setString(2, hospedagem.getEndereço());
            preparedStatement.setString(3, hospedagem.getEstado());
            preparedStatement.setFloat(4, hospedagem.getValorIntegral());
            preparedStatement.setDate(5, new Date(hospedagem.getCheckIn().getTime()));
            preparedStatement.setDate(7, new Date(hospedagem.getCheckOut().getTime()));
            preparedStatement.setString(8, hospedagem.getNumeroHotel());
            preparedStatement.setLong(9, hospedagem.getId());
            preparedStatement.executeUpdate();
            System.out.println("[LOG] Atualiza��o realizada com sucesso.");
        } catch (SQLException e) {
            System.out.printf("[ERROR] Atualiza��o falhou. Message:\n%s", e.getMessage());
        }
    }

}
