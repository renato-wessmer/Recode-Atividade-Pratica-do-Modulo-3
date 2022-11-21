package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDtoMetadata;
import model.Cliente;

public class ClienteDAO {

	public static String sql;
    private final Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public long create(Cliente cliente) {
        sql = "INSERT INTO %s VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        sql = String.format(sql, ClienteDtoMetadata.tableName);

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNomeCompleto());
            preparedStatement.setString(3, cliente.getRua());
            preparedStatement.setString(4, cliente.getBairro());
            preparedStatement.setString(5, cliente.getCep());
            preparedStatement.setString(6, cliente.getCidade());
            preparedStatement.setString(7, cliente.getEstado());
            preparedStatement.setString(8, cliente.getEmail());
            preparedStatement.setString(9, cliente.getNumero());

            preparedStatement.executeUpdate();
            var result = preparedStatement.getGeneratedKeys();

            long clienteId = result.next() ? result.getLong(1) : -1L;
            System.out.println("[LOG] Cliente inserido com sucesso.");
            return  clienteId;

        } catch (SQLException e) {
            System.out.printf("[ERROR] Cliente n�o foi inserido. Message:\n%s", e.getMessage());
            return -1L;
        }
    }

    public Cliente findByPk(long clienteId) {
        sql = "SELECT * FROM %s WHERE %s = ?";
        sql = String.format(
                sql,
                ClienteDtoMetadata.tableName,
                ClienteDtoMetadata.id
        );

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, clienteId);
            var result = preparedStatement.executeQuery();
            System.out.println("[LOG] Query cliente realizada com sucesso");

            Cliente cliente = new Cliente();

            if (result.next()) {
                cliente.setId(result.getLong(ClienteDtoMetadata.id));
                cliente.setCpf(result.getString(ClienteDtoMetadata.cpf));
                cliente.setNomeCompleto(result.getString(ClienteDtoMetadata.nomeCompleto));
                cliente.setRua(result.getString(ClienteDtoMetadata.rua));
                cliente.setBairro(result.getString(ClienteDtoMetadata.bairro));
                cliente.setCep(result.getString(ClienteDtoMetadata.cep));
                cliente.setCidade(result.getString(ClienteDtoMetadata.cidade));
                cliente.setEstado(result.getString(ClienteDtoMetadata.estado));
                cliente.setEmail(result.getString(ClienteDtoMetadata.email));
                cliente.setNumero(result.getString(ClienteDtoMetadata.numero));
            }
            return cliente;

        } catch (SQLException e) {
            System.out.printf("[ERROR] Query cliente falhou. Message:\n%s", e.getMessage());
            return null;
        }
    }
    
    public List<Cliente> findByPk2(long clienteId) {
        sql = "SELECT * FROM %s WHERE %s = ?";
        sql = String.format(
                sql,
                ClienteDtoMetadata.tableName,
                ClienteDtoMetadata.id
        );

        try {
        	PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, clienteId);
            var result = preparedStatement.executeQuery();
            List<Cliente> clientes = new ArrayList<>();

            while (result.next()) {
            	Cliente cliente = new Cliente();
                cliente.setId(result.getLong(ClienteDtoMetadata.id));
                cliente.setCpf(result.getString(ClienteDtoMetadata.cpf));
                cliente.setNomeCompleto(result.getString(ClienteDtoMetadata.nomeCompleto));
                cliente.setRua(result.getString(ClienteDtoMetadata.rua));
                cliente.setBairro(result.getString(ClienteDtoMetadata.bairro));
                cliente.setCep(result.getString(ClienteDtoMetadata.cep));
                cliente.setCidade(result.getString(ClienteDtoMetadata.cidade));
                cliente.setEstado(result.getString(ClienteDtoMetadata.estado));
                cliente.setEmail(result.getString(ClienteDtoMetadata.email));
                cliente.setNumero(result.getString(ClienteDtoMetadata.numero));
                clientes.add(cliente);
            }
            return clientes;

        } catch (SQLException e) {
            System.out.printf("[ERROR] Query cliente falhou. Message:\n%s", e.getMessage());
            return null;
        }
    }
}