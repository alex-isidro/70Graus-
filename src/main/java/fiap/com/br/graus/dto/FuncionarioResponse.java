package fiap.com.br.graus.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FuncionarioResponse {

    private Long id;
    private String nome;
    private String email;


    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
}