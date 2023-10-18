package ucsal.edu.web.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import ucsal.edu.web.helpers.ROLE;
import ucsal.edu.web.repository.TitularRepository;

@Service
public class TitularService {

    @Autowired
    private TitularRepository titularRepository;

    public void inserirUsuariosAleatorios(int quantidade) {
        Random random = new Random();
        Faker faker = new Faker();
        String sql = "INSERT INTO titular (id, role, nome, cpf, endereco, num_identificacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem", "postgres", "postgres");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < quantidade; i++) {
                ROLE role = ROLE.TITULAR;
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

                String cpfAleatorio = String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", digit1, digit2, digit3, digit4, digit5, digit6, digit7, digit8, digit9, verificador1, verificador2);
                String streetAddress = faker.address().streetAddress();
                String numIdent = random.nextInt(10) + "" + random.nextInt(10) + "" +random.nextInt(10) + "" + random.nextInt(10) + "" + random.nextInt(10);

                pstmt.setInt(1, i+1); // Adicionando valor para a coluna 'id'
                pstmt.setString(2, role.name());
                pstmt.setString(3, name);
                pstmt.setString(4, cpfAleatorio);
                pstmt.setString(5, streetAddress);
                pstmt.setString(6, numIdent);
                System.out.println("Insert numero:" + i + "\nDado:" + pstmt );
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Insert Finalizado");
    }
}
