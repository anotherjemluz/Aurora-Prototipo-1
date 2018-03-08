//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
public class Botao {
  private String nome;//nome la do botao
  private int x;//cordenada x
  private int y;//cordenada y  
  private int xf;//x final
  private int yf;//y final
  private int h;//altura
  private int w;//largura
  
  private int an;//variavel que define o indice da animação desejada
  private int lo;//variavel que define o indice do loop desejado
  private boolean home = false;//se for um botao que vai pra qualquer lugar que vc queira
  
  private boolean next = false;//se for um botao que exibe o proximo loop
  private boolean back = false;//se for um botao que exibe o loop anterior
  
  Botao(String nome, int x, int y, int xf, int yf, boolean ba, boolean ne) {
    if (ba & ne) {
      //validação
      println("O botão só pode ter um boolean como true");
    } else {
      this.nome = nome;
      this.x = x;
      this.y = y;
      this.xf = xf;
      this.yf = yf;
      this.h = yf - y;
      this.w = xf - x;
      
      this.back = ba;
      this.next = ne;
      println("botao "+ nome + " add");
    }   
  }//construtor da classe
  
  Botao(String nome, int x, int y, int xf, int yf, boolean home, int an, int lo) {
    this.nome = nome;
    this.x = x;
    this.y = y;
    this.xf = xf;
    this.yf = yf;
    this.h = yf - y;
    this.w = xf - x;
    
    this.home = home;
    this.an = an;
    this.lo = lo;
  }//construtor para botao tipo home
  
  public void on() {
    noFill();
    noStroke();
    rect(x, y, w, h);
  }//cria o botao na tela
}