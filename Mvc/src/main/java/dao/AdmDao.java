package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.MySqlConnection;
import model.Adm;

public class AdmDao implements CRUD{
	
	private static Connection connection = MySqlConnection.createConnection();
	private static String sql;
	
	public static void createAdm(Adm adm) {
		sql = "INSERT INTO adm VALUES (null, ?, ?, ?, ?)";
		
		try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adm.getNome());
            preparedStatement.setString(2, adm.getSobrenome());
            preparedStatement.setString(3, adm.getEmail());
            preparedStatement.setString(4, adm.getSenha());

            preparedStatement.executeUpdate();
            
            System.out.println("[LOG] Adm inserido com sucesso.");
            

        } catch (SQLException e) {
            System.out.printf("[ERROR] Adm não foi inserido. Message:\n%s", e.getMessage());
            
        }
		
	}
	
	public static boolean validarAdm(Adm adm){
		
		boolean status = false;
		sql = "SELECT * FROM adm WHERE email = ? and senha = ?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, adm.getEmail());
			ps.setString(2, adm.getSenha());
			ResultSet rs = ps.executeQuery();
			status = rs.next();

			
		} catch(SQLException e) {
			System.out.println("[ERRO] lOGIN E SENHA NÃO CONFERE " + e.getMessage());
		}
		return status;
	}
	
	public static void updateAdm(Adm adm) {
		
		sql = "UPDATE adm SET nome=?, sobrenome=?, email=?, senha=? WHERE id=?";
		
		 try {
			 PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, adm.getNome());
	            preparedStatement.setString(2, adm.getSobrenome());
	            preparedStatement.setString(3, adm.getEmail());
	            preparedStatement.setString(4, adm.getSenha());

	            preparedStatement.executeUpdate();
	            
	            System.out.println("[LOG] Update feito.");
	            

	        } catch (SQLException e) {
	            System.out.printf("[ERROR] Update falhou. Message:\n%s", e.getMessage());
	            
	        }
			
		}
	
	public static void deleteAdm(int admId) {
		sql = "DELETE FROM adm WHERE id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, admId);
			preparedStatement.executeUpdate();
			
			System.out.printf("[LOG] adm deletado com sucesso.");
			
			
		} catch (SQLException e) {
			System.out.printf("[ERROR] Deletar adm falhou. Message:\n%s" + e.getMessage());
			
		}
		
	}
	
	public static List<Adm> findAdm(String pesquisa){
		
		sql = String.format("SELECT * FROM adm WHERE email like '%s%%' OR senha LIKE '%s%%' ", pesquisa, pesquisa);
		List<Adm> adms = new ArrayList<Adm>();
		
		try {
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				Adm adm = new Adm();
				
				adm.setId(resultSet.getInt("id"));
				adm.setNome(resultSet.getString("nome"));
				adm.setSobrenome(resultSet.getString("sobrenome"));
				adm.setEmail(resultSet.getString("email"));
				adm.setSenha(resultSet.getString("senha"));
				
                
                adms.add(adm);
                
			}
			
			System.out.printf("[LOG] Busca de adm feita.");
			return adms;
			
		} catch(SQLException e) {
			System.out.printf("[ERROR] busca adm falhou. Message:\n%s" + e.getMessage());
			return null;
			
		}
	}
	
	public static Adm findByPkAdm(int admId) {
		
		sql = String.format("SELECT * FROM adm WHERE id = %d ", admId);
		
		
		try {
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery(sql);
			
			Adm adm = new Adm();
			
			while (resultSet.next()) {
								
				adm.setId(resultSet.getInt("id"));
				adm.setNome(resultSet.getString("nome"));
				adm.setSobrenome(resultSet.getString("sobrenome"));
				adm.setEmail(resultSet.getString("email"));
				adm.setSenha(resultSet.getString("senha"));
                
			}
			
			System.out.printf("[LOG] Busca por Chave feita.");
			return adm;
			
		} catch(SQLException e) {
			System.out.printf("[ERROR] busca por Chave falhou. Message:\n%s" + e.getMessage());
			return null;
			
		}
	}
	
}
