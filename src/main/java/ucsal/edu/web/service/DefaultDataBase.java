package ucsal.edu.web.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import ucsal.edu.web.model.Classificacao;

@Component
public class DefaultDataBase {

    private String caminhoArquivo = ".//database.txt";

    @PostConstruct
    private void inserirDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
            	Classificacao classifi = parseAvatar(linha);

                if (classifi != null) {
                    System.out.println(classifi);
                    insertAvatarIntoDatabase(classifi);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Classificacao parseAvatar(String classifiInfo) {
        try {
            String[] bigpart = classifiInfo.split(" - ");
            String[] parts = bigpart[0].split("_");
            if (parts.length >= 5) {
                String fileName = parts[0] + "_" + parts[1] + "_" + parts[2] + "_" + parts[3];
                String code = parts[0] + "_" + parts[1];
                String type = parts[2];
                String identification = parts[3];
                String definition = parts[4];
                String link = bigpart[1];
                
                if (!fileName.isEmpty() && !code.isEmpty() && !type.isEmpty() && !identification.isEmpty() && !definition.isEmpty()) {
                    return new Classificacao(null, fileName, code, type, identification, definition, "", link);
                } 
            }
        } catch (Exception e) {
            System.err.println("Erro ao analisar avatarInfo: " + classifiInfo);
            e.printStackTrace();
            return null;
        }
		return null;
    }




    private void insertAvatarIntoDatabase(Classificacao classifi) {
        if (classifi != null) {
            String sql = "INSERT INTO classificacao (code, file_name, identification, type, definition, link, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CardCreditSystem",
                    "postgres", "postgres");
                 PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"})) {
                pstmt.setString(1, classifi.getCode());
                pstmt.setString(2, classifi.getFileName());
                pstmt.setString(3, classifi.getIdentification());
                pstmt.setString(4, classifi.getType());
                pstmt.setString(5, classifi.getDefinition());
                pstmt.setString(6, classifi.getLink());
                pstmt.setString(7, classifi.getDescription());
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Long generatedId = generatedKeys.getLong(1);
                        classifi.setId(generatedId);
                    } else {
                        throw new SQLException("Failed to retrieve generated ID.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
