package fiap.com.br.graus.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageResponse<T> {

    private List<T> data;
    private int pagina;
    private int totalPaginas;
    private long totalElementos;

    public List<T> getData() {
        return data;
    }

    public int getPagina() {
        return pagina;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public long getTotalElementos() {
        return totalElementos;
    }
}