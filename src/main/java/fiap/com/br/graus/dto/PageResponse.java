package fiap.com.br.graus.dto;

import java.util.List;

public class PageResponse<T> {

    private List<T> data;
    private int pagina;
    private int totalPaginas;
    private long totalElementos;

    public PageResponse(List<T> data, int pagina, int totalPaginas, long totalElementos) {
        this.data = data;
        this.pagina = pagina;
        this.totalPaginas = totalPaginas;
        this.totalElementos = totalElementos;
    }

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