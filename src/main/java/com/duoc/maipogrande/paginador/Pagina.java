package com.duoc.maipogrande.paginador;

public class Pagina {

    private short totalPaginas;
    private short desde;
    private short hasta;

    public Pagina(short totalPaginas, short desde){
        this.totalPaginas = totalPaginas;
        this.desde = desde;
        this.hasta = (short)(desde+3);
    }

    public short getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(short totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public short getDesde() {
        return desde;
    }

    public void setDesde(short desde) {
        this.desde = desde;
    }

    public short getHasta() {
        return hasta;
    }

    public void setHasta(short hasta) {
        this.hasta = hasta;
    }
}
