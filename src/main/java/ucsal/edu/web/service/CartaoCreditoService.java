package ucsal.edu.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucsal.edu.web.helpers.Status;
import ucsal.edu.web.model.Titular;
import ucsal.edu.web.repository.TitularRepository;

@Service
public class CartaoCreditoService {

	@Autowired
	private TitularRepository titularRepository;

	public void inserirCartaoAleatorios(int quantidade) {
		Random random = new Random();
		String sql = "INSERT INTO cartaoCredito (id, status, numero_cartao, nome_cartao, validade, codigo_seguranca, limite) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "INSERT INTO titular_cartao_credito (titular_id, cartao_credito_id) VALUES (?, ?)";

		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres");
				PreparedStatement pstmt1 = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {

			for (int i = 0; i < quantidade; i++) {
				Status status = (random.nextDouble() < 0.5) ? Status.DESBLOQUEADO : Status.BLOQUEADO;
				int seq1 = random.nextInt(9999);
				int seq2 = random.nextInt(9999);
				int seq3 = random.nextInt(9999);
				String numeroCartao = seq1 + " " + seq2 + " " + seq3;
				Optional<Titular> titular = titularRepository.findById((long) random.nextInt(99999));

				if (titular.isPresent()) {
					String nomeCartao = titular.get().getNome();
					int month = random.nextInt(12) + 1;
					int year = 1980;

					// Formato desejado ANO-MES
					String validadeString = String.format("%04d-%02d-01", year, month);

					// Convertendo a string para um objeto Date
					java.sql.Date sqlValidade = java.sql.Date.valueOf(validadeString);

					int codigoSeguranca = random.nextInt(999);
					double limite = Math.ceil(random.nextInt(100000));

					pstmt1.setInt(1, i + 1);
					pstmt1.setString(2, status.name());
					pstmt1.setString(3, numeroCartao);
					pstmt1.setString(4, nomeCartao);
					pstmt1.setDate(5, sqlValidade);
					pstmt1.setInt(6, codigoSeguranca);
					pstmt1.setDouble(7, limite);
					System.out.println("Insert numero:" + i + "\nDado:" + pstmt1);
					pstmt1.executeUpdate();

					pstmt2.setLong(1, titular.get().getId());
					pstmt2.setLong(2, i + 1);
					System.out.println("Insert numero:" + i + "\nDado:" + pstmt2);
					pstmt2.executeUpdate();
				} else {
					System.out.println("Titular not found for ID: " + random.nextInt(999999));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Insert Finalizado");
	}

	public void filtroNomeCartao(String nomeCartao) {
		String sql = "SELECT * FROM cartaocredito WHERE nome_cartao LIKE ?";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + nomeCartao + "%");

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					System.out.println("ID do Cartão de Crédito: " + resultSet.getLong("id"));
					System.out.println("Número do Cartão: " + resultSet.getString("nome_cartao"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void filtroLimiteCartao(double limiteMin, double limiteMax) {
		String sql = "SELECT * FROM cartaocredito WHERE limite BETWEEN ? AND ?";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setDouble(1, limiteMin);
			pstmt.setDouble(2, limiteMax);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					System.out.println("ID do Cartão de Crédito: " + resultSet.getLong("id"));
					System.out.println("Número do Cartão: " + resultSet.getString("limite"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


public void filtroValidadeCartao(String inicio, String fim) {
    LocalDate inicioDate = parseLocalDate(inicio);
    LocalDate fimDate = parseLocalDate(fim);

    String sql = "SELECT * FROM cartaocredito WHERE validade BETWEEN ? AND ?";
    try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
            "postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

        java.sql.Date inicioSqlDate = java.sql.Date.valueOf(inicioDate);
        java.sql.Date fimSqlDate = java.sql.Date.valueOf(fimDate);

        pstmt.setDate(1, inicioSqlDate);
        pstmt.setDate(2, fimSqlDate);

        try (ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("ID do Cartão de Crédito: " + resultSet.getLong("id"));
                System.out.println("Número do Cartão: " + resultSet.getString("validade"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private LocalDate parseLocalDate(String dateStr) {
    return LocalDate.parse(dateStr + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}

	public void filtroStatusCartao(String status) {
		String sql = "SELECT * FROM cartaocredito WHERE status = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
				"postgres", "postgres"); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, status);

			try (ResultSet resultSet = pstmt.executeQuery()) {
				while (resultSet.next()) {
					System.out.println("ID do Cartão de Crédito: " + resultSet.getLong("id"));
					System.out.println("Número do Cartão: " + resultSet.getString("status"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
