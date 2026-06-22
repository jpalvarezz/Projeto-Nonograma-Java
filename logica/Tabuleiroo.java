import java.util.ArrayList;
import java.util.List;

public class Tabuleiroo {

    private static final int LINHAS = 10;   //nao vamos fazer mais que 10x10 pelo jeito
    private static final int COLUNAS = 10;   
    
    public enum Estado {
        INTOCADA, MARCADA, VAZIO  //definicao de casas, para fazer a matriz enumerador
    }

    private int erros = 0; //quantidade de erros atua do jogo
    private String nome; //nome do tabuleiro
    public Estado[][] celulas; //matriz de enumerador de celulas (jogo atual)
    private Estado[][] estadoCorreto; //gabarito
    private int[][] pistasLinha;  //pistas (voce que vai calcular joao)
    private int[][] pistasColuna; //pistas (voce que vai calcular joao)
    //construtor
    public Tabuleiroo(String nome) {
        this.nome = nome;
        this.celulas = new Estado[LINHAS][COLUNAS];
        this.estadoCorreto = new Estado[LINHAS][COLUNAS];

        for (int l = 0; l < LINHAS; l++){
            for (int c = 0; c < COLUNAS; c++){
                this.celulas[l][c] = Estado.INTOCADA;
            }
        }

    }

    //gera um tabuleiro aleatorio, talvez mudar as chances de entrar marcado
    public void gerarTabuleiroAleatorio() {
        for (int l = 0; l < LINHAS; l++)
            for (int c = 0; c < COLUNAS; c++)
                this.estadoCorreto[l][c] = Math.random() < 0.5 ? Estado.MARCADA : Estado.VAZIO;

        //Quando criar o gabarito, ja calcula as pistas
        calcularPistas();
    }

   //aqui compara o tabuleiro atual com o correto, para ver se marcou erro e corrigir
    public void compararEstado() {
        
        for (int l = 0; l < LINHAS; l++)
            for (int c = 0; c < COLUNAS; c++) {

                if((estadoCorreto[l][c] != celulas[l][c]) && celulas[l][c] != Estado.INTOCADA){
                    erros++;
                    celulas[l][c] = estadoCorreto[l][c];
                }


            }
    }
    //confere se o jogador venceu
    public int vitoria() {
        for (int l = 0; l < LINHAS; l++) {
            for (int c = 0; c < COLUNAS; c++) {
                // Se a célula deveria ser marcada e não foi
                if (estadoCorreto[l][c] == Estado.MARCADA && celulas[l][c] != Estado.MARCADA) {
                    return 0; // Não venceu ainda
                }
                // Se a célula deveria ser vazia, mas o jogador marcou (erro)
                if (estadoCorreto[l][c] == Estado.VAZIO && celulas[l][c] == Estado.MARCADA) {
                    return 0; // Não venceu ainda
                }
            }
        }
        return 1; // Se passou por tudo sem problemas, venceu!
    }
    //marca uma casa
    public void marcar(int l, int c){
        celulas[l][c] = Estado.MARCADA;
    }
    //marca uma casa como vazio
    public void vazio(int l, int c){
        celulas[l][c] = Estado.VAZIO;
    }
    //retorna a quantidade atual de erros
    public int qtosErros() { 
        return erros; 
    }
    //retorna nome do tabuleiro
    public String qualNome() {
        return nome; 
    }

    public void calcularPistas(){
        //Inicializar as matrizes
        this.pistasLinha = new int[LINHAS][];
        this.pistasColuna = new int[COLUNAS][];

        //Calculo das pistas das LINHAS
        for (int l = 0; l < LINHAS; l++){
            List<Integer> dicasLinha = new ArrayList<>();
            int blocosSeguidos = 0;

            for(int c = 0; c < COLUNAS; c++){
                if(estadoCorreto[l][c] ==  Estado.MARCADA){
                    blocosSeguidos++;
                } else {
                    if(blocosSeguidos > 0){
                        dicasLinha.add(blocosSeguidos);
                        blocosSeguidos = 0;
                    }
                }
            }
            // Adiciona o ultimo bloco se a linha terminar com marcação
            if(blocosSeguidos > 0){
                dicasLinha.add(blocosSeguidos);
            }

            //Se a linha inteira for vazia, a dica padrão é 0
            if(dicasLinha.isEmpty()){
                dicasLinha.add(0);
            }

            //Converte a lista para o array da classe
            pistasLinha[l] = new int[dicasLinha.size()];
            for(int i = 0; i < dicasLinha.size(); i++){
                pistasLinha[l][i] = dicasLinha.get(i);
            }
        }

        ////Calculo das pistas das COLUNAS
        for(int c = 0; c < COLUNAS; c++){
            List<Integer> dicasColuna = new ArrayList<>();
            int blocosSeguidos = 0;

            for(int l = 0; l < LINHAS; l++){
                if(estadoCorreto[l][c] == Estado.MARCADA){
                    blocosSeguidos++;
                } else{
                    if(blocosSeguidos > 0){
                        dicasColuna.add(blocosSeguidos);
                        blocosSeguidos = 0;
                    }
                }
            }
            if(blocosSeguidos > 0){
                dicasColuna.add(blocosSeguidos);
            }
            if(dicasColuna.isEmpty()){
                dicasColuna.add(0);
            }

            pistasColuna[c] = new int[dicasColuna.size()];
            for(int i = 0; i < dicasColuna.size(); i++){
                pistasColuna[c][i] = dicasColuna.get(i);
            }
        }
    }

    //Getters para o GUI
    public int[][] getPistasLinha(){
        return pistasLinha;
    }

    public int[][] getPistasColuna(){
        return pistasColuna;
    }

}