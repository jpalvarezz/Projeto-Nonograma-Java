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
        int venceu = 1;
        for (int l = 0; l < LINHAS; l++)
            for (int c = 0; c < COLUNAS; c++) {

                if((estadoCorreto[l][c] != celulas[l][c])){
                    venceu = 0;
                }

            
            }
        return venceu;
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
}