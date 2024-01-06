package ucsal.edu.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import ucsal.edu.web.helpers.ROLE;
import ucsal.edu.web.model.Classificacao;
import ucsal.edu.web.repository.ClassificacaoRepository;

@Service
public class TitularService {

	@Autowired
	private ClassificacaoRepository classificacaoRepository;

	public void inserirUsuariosAleatorios(int quantidade) {
		Random random = new Random();
		Faker faker = new Faker();
		String sql = "INSERT INTO titular (id, role, nome, cpf, endereco, num_identificacao, classificacao, nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			for (int i = 0; i < quantidade; i++) {
				ROLE role = (random.nextDouble() < 0.5) ? ROLE.TITULAR : ROLE.DEPENDENTE;
				String name = faker.name().fullName();
				int digit1 = random.nextInt(10);
				int digit2 = random.nextInt(10);
				int digit3 = random.nextInt(10);
				int digit4 = random.nextInt(10);
				int digit5 = random.nextInt(10);
				int digit6 = random.nextInt(10);
				int digit7 = random.nextInt(10);
				int digit8 = random.nextInt(10);
				int digit9 = random.nextInt(10);

				int verificador1 = random.nextInt(10);
				int verificador2 = random.nextInt(10);

				String cpfAleatorio = String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", digit1, digit2, digit3, digit4, digit5,
						digit6, digit7, digit8, digit9, verificador1, verificador2);
				String streetAddress = faker.address().streetAddress();
				String numIdent = random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10) + ""
						+ random.nextInt(10) + "" + random.nextInt(10);

				Optional<Classificacao> optionalClassificacao = classificacaoRepository
						.findById(Math.round((Math.random() * 72) + 1));

				// String classificacao = (ClassificacaoRepository.findById((Math.random() *
				// 146) + 1)).orElse(null)).toString();

				int day = random.nextInt(30) + 1;
				int month = random.nextInt(12) + 1;
				int year = 1980;
				int lastDayOfMonth = YearMonth.of(year, month).lengthOfMonth();
				day = Math.min(day, lastDayOfMonth);

				String nascimentoString = String.format("%02d/%02d/%04d", day, month, year);

				LocalDate nascimento = LocalDate.parse(nascimentoString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

				java.sql.Date sqlDate = java.sql.Date.valueOf(nascimento);

				pstmt.setInt(1, i + 1);
				pstmt.setString(2, role.name());
				pstmt.setString(3, name);
				pstmt.setString(4, cpfAleatorio);
				pstmt.setString(5, streetAddress);
				pstmt.setString(6, numIdent);
				pstmt.setString(7, optionalClassificacao.get().getType().toString());
				pstmt.setDate(8, sqlDate);

				System.out.println("Insert numero:" + i + "\nDado:" + pstmt);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Insert Finalizado");
	}

	public void filtroNascimento(String dataInicio, String dataFim) {
		String sql = "SELECT * FROM titular WHERE nascimento::date BETWEEN ?::date AND ?::date";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			LocalDate dataInicioParsed = parseLocalDate(dataInicio);
			LocalDate dataFimParsed = parseLocalDate(dataFim);

			pstmt.setDate(1, java.sql.Date.valueOf(dataInicioParsed));
			pstmt.setDate(2, java.sql.Date.valueOf(dataFimParsed));

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					System.out.println("ID: " + resultSet.getInt("id"));
					System.out.println("Nascimento: " + resultSet.getString("nascimento"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private LocalDate parseLocalDate(String dateString) {
		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public void filtroNome(String nome) {
		String sql = "SELECT * FROM titular WHERE nome LIKE ?";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + nome + "%");

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					System.out.println("ID: " + resultSet.getInt("id"));
					System.out.println("Nome: " + resultSet.getString("nome"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void filtroRole(String role) {
		String sql = "SELECT * FROM titular WHERE role = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, role);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					System.out.println("ID: " + resultSet.getInt("id"));
					System.out.println("Nome: " + resultSet.getString("role"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void filtroQuantidadeCartoes(int quantidade) {
		String sql = "SELECT tc.titular_id, COUNT(tc.cartao_credito_id) AS quantidade_cartoes "
				+ "FROM titular_cartao_credito tc " + "GROUP BY tc.titular_id "
				+ "HAVING COUNT(tc.cartao_credito_id) = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, quantidade);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					System.out.println("Titular ID: " + resultSet.getInt("titular_id"));
					System.out.println("Quantidade de Cartões: " + resultSet.getInt("quantidade_cartoes"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
