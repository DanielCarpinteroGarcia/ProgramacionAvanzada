package LecturaCSV;

import Tables.Table;

public abstract class ReaderTemplate {

    private String fichero;
    private CSVUnlabeledFileReader leerSinEtiqueta;
    private CSVLabeledFileReader leerConEtiqueta;

    public ReaderTemplate(String f){
        this.fichero = f;
    }
    public abstract void openSource(String source);
    public abstract void processHeaders(String headers);
    public abstract void processData(String data);
    public abstract void closeSource();
    public abstract boolean hasMoreData();
    public abstract String getNextData();

    public final Table readTableFromSource(){
        Table t = new Table();
        // 1º Abrir la fuente de datos
        openSource(fichero);
        // 2º Leer la cabecera
        String headers = getNextData();
        processHeaders(headers);
        // 3º Leer todos los puntos de datos (hasta que no haya más datos)
        while(hasMoreData()){
            processData(getNextData());
        }
        // 4º Cerrar la fuente de datos
        closeSource();
    }
}





