import static java.lang.System.in;
import static java.lang.System.out;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import dao.ClienteDAO;
import dao.DestinoDAO;
import dao.HospedagemDAO;
import database.Database;
import model.Cliente;
import model.Destino;
import model.Hospedagem;

public class main {

	public  static void consultarCliente(List<Cliente> clientes)throws ParseException {
		
        out.println("---------------------------------");
        for (var cliente : clientes) {
            out.println(
                    "Id: " + cliente.getId() +
                    "Cf: " + cliente.getCpf() +
                    "Nome Completo: " + cliente.getNomeCompleto() +
                    "rua: " + cliente.getRua() +
                    "Bairro: " + cliente.getBairro() +
                    "Cep: " + cliente.getCep() +
                    "Cidade: " + cliente.getCidade() +
                    "Estado: " + cliente.getEstado() +
                    "Email: " + cliente.getEmail() +
                    "Numero: " + cliente.getNumero() 
            );
            out.println("---------------------------------");
        }
    }
	
	public  static void consultarHospedagem(List<Hospedagem> hospedagems)throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        out.println("---------------------------------");
        for (var hospedagem : hospedagems) {
            out.println(
                    "Id: " + hospedagem.getId() +
                    "Nome do Hotel: " + hospedagem.getNomeHotel() +
                    "Endereço: " + hospedagem.getEndereço() +
                    "Estado: " + hospedagem.getEstado() +
                    "Valor: " + hospedagem.getValorIntegral() +
                    "Data de Checkin (yyyy/MM/dd): " + simpleDateFormat.format(hospedagem.getCheckIn()) +
                    "Data de Checkout (yyyy/MM/dd): " + simpleDateFormat.format(hospedagem.getCheckOut()) +
                    "Dados do Cliente: " + hospedagem.getCliente()
                     
            );
            out.println("---------------------------------");
        }
    }
	
	public static void insiraHospedagemCliente(Scanner scanner, Hospedagem hospedagem, Cliente cliente) throws ParseException{
        
		out.println("---- Cadastro de Hospedagem -----");
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        out.println("Informe o nome do Hotel: ");
        	hospedagem.setNomeHotel(scanner.next());
        out.println("Informe o Endereço do hotel");
            hospedagem.setEndereço(scanner.next());
        out.println("Informe o estado");
            hospedagem.setEstado(scanner.next());
        out.println("Informe o valor da hospedagem");
        	hospedagem.setValorIntegral(scanner.nextFloat());
        out.println("Informe a data do chekin (yyyy/MM/dd)");
            hospedagem.setCheckIn(date.parse(scanner.next()));
        out.println("Informe a data do chekout (yyyy/MM/dd)");
            hospedagem.setCheckOut(date.parse(scanner.next()));
        out.println("Informe o NUmero do hotel:");
        	hospedagem.setNumeroHotel(scanner.next());
        out.println("---- Dados do Cliente -----");
        out.println("Informe o Cpf do Cliente:");
            cliente.setCpf(scanner.next());
        out.println("Informe o nome completo do Cliente:");
        	cliente.setNomeCompleto(scanner.next());
        out.println("Informe a rua do Cliente:");
        	cliente.setRua(scanner.next());
        out.println("Informe o Bairro do Cliente:");
        	cliente.setBairro(scanner.next());
        out.println("Informe o CEP Cliente:");
        	cliente.setCep(scanner.next());
        out.println("Informe a cidade: ");
        	cliente.setCidade(scanner.next());
        out.println("Informe o estado UF:");
        	cliente.setEstado(scanner.next());
        out.println("Informe o Email do cliente:");
        	cliente.setEmail(scanner.next());
        out.println("Informe o numero ");
        	cliente.setNumero(scanner.next());
    }
	
	public  static void consultarDestino(List<Destino> destinos)throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        out.println("---------------------------------");
        for (var destino : destinos) {
            out.println(
                    "Id: " + destino.getId() +
                    "     Nome do Destino: \n" + destino.getNome() +
                    "Cidade: " + destino.getCidade() +
                    "   Estado: \n" + destino.getEstado() +
                    "Data ida (yyyy/MM/dd): " + simpleDateFormat.format(destino.getDataIda()) +
                    "    Data volta (yyyy/MM/dd): \n" + simpleDateFormat.format(destino.getDataVolta()) +
                    "Valor: " + destino.getValorIntegral() +
                    "   Dados do Cliente: " + destino.getCliente()
                     
            );
            out.println("---------------------------------");
        }
    }
	
	public static void insiraDestinoCliente(Scanner scanner, Destino destino, Cliente cliente) throws ParseException{
        
		out.println("---- Cadastro de Destino -----");
		DateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        out.println("Informe o nome do Destino: ");
        	destino.setNome(scanner.next());
        out.println("Informe o a cidade:");
        	destino.setCidade(scanner.next());
        out.println("Informe o estado:");
        	destino.setEstado(scanner.next());
        out.println("Informe a data de ida (yyyy/MM/dd)");
        	destino.setDataIda(date.parse(scanner.next()));
        out.println("Informe a data de volta (yyyy/MM/dd)");
        	destino.setDataVolta(date.parse(scanner.next()));
        out.println("Informe o valor do destino");
        	destino.setValorIntegral(scanner.nextFloat());
        out.println("---- Dados do Cliente -----");
        out.println("Informe o Cpf do Cliente:");
            cliente.setCpf(scanner.next());
        out.println("Informe o nome completo do Cliente:");
        	cliente.setNomeCompleto(scanner.next());
        out.println("Informe a rua do Cliente:");
        	cliente.setRua(scanner.next());
        out.println("Informe o Bairro do Cliente:");
        	cliente.setBairro(scanner.next());
        out.println("Informe o CEP Cliente:");
        	cliente.setCep(scanner.next());
        out.println("Informe a cidade: ");
        	cliente.setCidade(scanner.next());
        out.println("Informe o estado UF:");
        	cliente.setEstado(scanner.next());
        out.println("Informe o Email do cliente:");
        	cliente.setEmail(scanner.next());
        out.println("Informe o numero ");
        	cliente.setNumero(scanner.next());
    }
	
    public static long readIdOfClient(Scanner scanner) {
        out.println("Informe o ID do Cliente: ");
            return scanner.nextLong();
    }
    
    public static int menu(Scanner scanner) {
        out.println(
                "OPÇÕES: \n" +
                		"Clientes só pode ser cadastrados vinculados a alguma Hospedagem\n"+
                		"ou Destino, sempre inserir os mesmos com os dados do cliente.\n" +
                		"------------------------------------------\n" +
                        "[1]  - Consultar Todas as Hospedagens\n" +
                        "[2]  - Inserir Hospedagem\n" +
                        "[3]  - Consultar Hospedagem por ID\n" +
                        "[4]  - Deletar Hospedagem por ID\n" +
                        "[5]  - Atualizar Hospedagem por ID\n" +
                        "[6]  - Consultar Todos os Destino\n" + 
                        "[7]  - Inserir Destino\n" +
                        "[8]  - Consultar Destino por ID\n" +
                        "[9]  - Deletar Destino por ID\n" +
                        "[10] - Atualizar Destino por ID\n" + 
                        "[11] - Consultar Cliente por ID\n" +
                        "ESC para Encerrar"
        );
        return scanner.nextInt();
    }
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(in);
        var connection = Database.createConnectionToMySql();
        ClienteDAO clienteDao = new ClienteDAO(connection);
        HospedagemDAO hospedagemDao = new HospedagemDAO(connection, clienteDao);
        DestinoDAO destinoDao = new DestinoDAO(connection, clienteDao);


        out.println("------ ACESSO AO BANCO DE DADOS ------");
        var option = menu(scanner);
        
        while (option > 0 && option < 12) {
            switch (option) {
                case 1:
                	consultarHospedagem(hospedagemDao.findAll());
                    option = menu(scanner);
                    break;
                case 2:
                    Hospedagem hospedagem = new Hospedagem();
                    Cliente cliente = new Cliente();
                    insiraHospedagemCliente(scanner, hospedagem, cliente);
                    long clienteId = clienteDao.create(cliente);
                    hospedagemDao.create(hospedagem, clienteId);
                    option = menu(scanner);
                    break;
                case 3:
                	consultarHospedagem(hospedagemDao.findByPk(readIdOfClient(scanner)));
                    option = menu(scanner);
                    break;
                case 4:
                    hospedagemDao.delete(readIdOfClient(scanner));
                    option = menu(scanner);
                    break;
                case 5:
                    Hospedagem hospedagemToUpdate = new Hospedagem();
                    Cliente clienteToUpdate = new Cliente();
                    hospedagemToUpdate.setId(readIdOfClient(scanner));
                    insiraHospedagemCliente(scanner, hospedagemToUpdate, clienteToUpdate);
                    hospedagemDao.update(hospedagemToUpdate);
                    option = menu(scanner);
                    break;    
                      
            }
            
        while (option > 6 && option < 12) {
        	switch(option) {
        	case 6:
            	consultarDestino(destinoDao.findAll());
                option = menu(scanner);
                break;
        case 7:
            Destino destino = new Destino();
            Cliente cliente = new Cliente();
            insiraDestinoCliente(scanner, destino, cliente);
            long clienteId = clienteDao.create(cliente);
            destinoDao.create(destino, clienteId);
            option = menu(scanner);
            break;
        case 8:
        	consultarDestino(destinoDao.findByPk(readIdOfClient(scanner)));
            option = menu(scanner);
            break;
        case 9:
            destinoDao.delete(readIdOfClient(scanner));
            option = menu(scanner);
            break;
        case 10:
            Destino destinoToUpdate = new Destino();
            Cliente clienteToUpdate = new Cliente();
            destinoToUpdate.setId(readIdOfClient(scanner));
            insiraDestinoCliente(scanner, destinoToUpdate, clienteToUpdate);
            destinoDao.update(destinoToUpdate);
            option = menu(scanner);
            break;
        case 11:
        	consultarCliente(clienteDao.findByPk2(readIdOfClient(scanner)));
            option = menu(scanner);
            break;
            
        	
        }
                
                    
                  
                
                
                
                
            
                }
            }

	}

}