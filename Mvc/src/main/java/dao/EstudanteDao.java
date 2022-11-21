package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.MySqlConnection;
import model.Estudante;

public class EstudanteDao implements CRUD {
	
	private static Connection connection = MySqlConnection.createConnection();
	private static String sql;
	
	public static void create(Estudante estudante) {
		sql = "INSERT INTO estudante VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            preparedStatement.setString(1, estudante.getNome());
            preparedStatement.setString(2, estudante.getSobrenome());
            preparedStatement.setString(3, estudante.getNascimento());
            preparedStatement.setString(4, estudante.getSerie());
            preparedStatement.setString(5, estudante.getEstado());
            preparedStatement.setString(6, estudante.getCidade());
            preparedStatement.setString(7, estudante.getEmail());
            preparedStatement.setString(8, estudante.getSenha());

            preparedStatement.executeUpdate();
            
            System.out.println("[LOG] Estudante inserido com sucesso.");
            

        } catch (SQLException e) {
            System.out.printf("[ERROR] Estudante não foi inserido. Message:\n%s", e.getMessage());
            
        }
		
	}
	
	public static boolean validar(Estudante estudante){
		
		boolean status = false;
		sql = "SELECT * FROM estudante WHERE email = ? and senha = ? and serie = ?";
		

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, estudante.getEmail());
			ps.setString(2, estudante.getSenha());
			ps.setString(3, "quinto");
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();

			
		} catch(SQLException e) {
			System.out.println("[ERRO] lOGIN E SENHA NÃO CONFERE " + e.getMessage());
		}
		return status;
	}
	
public static boolean validarS(Estudante estudante){
		
		boolean status = false;
		sql = "SELECT * FROM estudante WHERE email = ? and senha = ? and serie = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, estudante.getEmail());
			ps.setString(2, estudante.getSenha());
			ps.setString(3, "sexto");
			
			ResultSet rs = ps.executeQuery();
			status = rs.next();

			
		} catch(SQLException e) {
			System.out.println("[ERRO] lOGIN E SENHA NÃO CONFEREsss " + e.getMessage());
		}
		return status;
	}
	
	
	
	
	public static void update(Estudante estudante) {
		
		sql = "UPDATE estudante SET nome=?, sobrenome=?, nascimento=?, serie=?, estado=?,"
				+ " cidade=?, email=?, senha=? WHERE id=?";
		
		 try {
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, estudante.getNome());
	            preparedStatement.setString(2, estudante.getSobrenome());
	            preparedStatement.setString(3, estudante.getNascimento());
	            preparedStatement.setString(4, estudante.getSerie());
	            preparedStatement.setString(5, estudante.getEstado());
	            preparedStatement.setString(6, estudante.getCidade());
	            preparedStatement.setString(7, estudante.getEmail());
	            preparedStatement.setString(8, estudante.getSenha());
	            preparedStatement.setInt(9, estudante.getId());

	            preparedStatement.executeUpdate();
	            
	            System.out.println("[LOG] Update feito.");
	            

	        } catch (SQLException e) {
	            System.out.printf("[ERROR] Update falhou. Message:\n%s", e.getMessage());
	            
	        }
			
		}
	
	public static void delete(int estudanteId) {
		sql = "DELETE FROM estudante WHERE id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, estudanteId);
			preparedStatement.executeUpdate();
			
			System.out.printf("[LOG] estudante deletado com sucesso.");
			
			
		} catch (SQLException e) {
			System.out.printf("[ERROR] Deletar estudante falhou. Message:\n%s" + e.getMessage());
			
		}
		
	}
	
	public static List<Estudante> find(String pesquisa){
		
		sql = String.format("SELECT * FROM estudante WHERE email like '%s%%' OR senha LIKE '%s%%' ", pesquisa, pesquisa);
		List<Estudante> estudantes = new ArrayList<Estudante>();
		
		try {
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				Estudante estudante = new Estudante();
				
				estudante.setId(resultSet.getInt("id"));
				estudante.setNome(resultSet.getString("nome"));
				estudante.setSobrenome(resultSet.getString("sobrenome"));
				estudante.setNascimento(resultSet.getString("nascimento"));
				estudante.setSerie(resultSet.getString("serie"));
				estudante.setEstado(resultSet.getString("estado"));
				estudante.setCidade(resultSet.getString("cidade"));
				estudante.setEmail(resultSet.getString("email"));
				estudante.setSenha(resultSet.getString("senha"));
				
                
                estudantes.add(estudante);
                
			}
			
			System.out.printf("[LOG] Busca de estudante feita.");
			return estudantes;
			
		} catch(SQLException e) {
			System.out.printf("[ERROR] busca estudante falhou. Message:\n%s" + e.getMessage());
			return null;
			
		}
	}
	
	public static Estudante findByPk(int estudanteId) {
		
		sql = String.format("SELECT * FROM estudante WHERE id = %d ", estudanteId);
		
		
		try {
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery(sql);
			
			Estudante estudante = new Estudante();
			
			while (resultSet.next()) {
								
				estudante.setId(resultSet.getInt("id"));
				estudante.setNome(resultSet.getString("nome"));
				estudante.setSobrenome(resultSet.getString("sobrenome"));
				estudante.setNascimento(resultSet.getString("nascimento"));
				estudante.setSerie(resultSet.getString("serie"));
				estudante.setEstado(resultSet.getString("estado"));
				estudante.setCidade(resultSet.getString("cidade"));
				estudante.setEmail(resultSet.getString("email"));
				estudante.setSenha(resultSet.getString("senha"));
                
			}
			
			System.out.printf("[LOG] Busca por Chave feita.");
			return estudante;
			
		} catch(SQLException e) {
			System.out.printf("[ERROR] busca por Chave falhou. Message:\n%s" + e.getMessage());
			return null;
			
		}
	}
	
}

	
