
public class Main {
    public static void main(String[] args){
        Tabuleiroo tb = new Tabuleiroo("Paiola");
        tb.gerarTabuleiroAleatorio();
        
        int dificuldade = 1;  //dificuldade pega pelo gui
        int erros = 0;
        
        switch(dificuldade){
            //aqui so depende do tempo
            case 1:
            


            break;

            case 2:
            //Acaba quando chegar ao limite de erros ou ou o jogador vencer( break no final)
            while(erros < 3){
                int linha, coluna;
                linha = coluna = 0; //gui que vai pegar
                int opcao = 0;   //A opcao o gui que vai pegar
                //para o que o jogador quer fazer 
                switch(opcao){


                    case 1:
                    tb.marcar(linha, coluna);
                    break;
                    
                    case 2:
                    tb.vazio(linha, coluna);
                    break;

                    default:
                    break;
                }



                tb.compararEstado();
                erros = tb.qtosErros();
                int vitoria = tb.vitoria();
                if(vitoria == 1){
                    System.out.println("Voce venceu");   //aqui o gui deve levar pra tela de vitoria
                    break;
                }
            }


            break;
            case 3:
                
            while(erros < 1){
                int linha, coluna;
                linha = coluna = 0; //gui que vai pegar
                int opcao = 0;   //A opcao o gui que vai pegar
                //para o que o jogador quer fazer 
                switch(opcao){


                    case 1:
                    tb.marcar(linha, coluna);
                    break;
                    
                    case 2:
                    tb.vazio(linha, coluna);
                    break;

                    default:
                    break;
                }



                tb.compararEstado();
                erros = tb.qtosErros();
                int vitoria = tb.vitoria();
                if(vitoria == 1){
                    System.out.println("Voce venceu");   //aqui o gui deve levar pra tela de vitoria
                    break;
                }
            }          

            break;
            default:
            break;
        }
    }
}
