//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
public class Animacao {
  private String prefix;//prefix do nome da imagem 

  private int frameInicial;//frame inicial do trecho para importação
  private int totalFrames;//numero de frames da pasta
  private int frameAtual;//frame "exibido" no momento

  private ArrayList<Loop> loops;//array que armazena os loops
  private int qtdLoops;//variavel que armazena a quantidade de loops

  private PImage[] frames;//array para armazenar as imagens da animação

  public Animacao (String prefix, int frameInicial, int totalFrames) {
    this.prefix = prefix; 
    this.totalFrames = totalFrames;
    this.frameInicial = frameInicial;
    this.frameAtual = frameInicial;
    
    frames = new PImage[totalFrames + 1];//inicializa o array, e sem o +1 da erro de ArrayOutOfBoundsExeption
                                         //já que o indice das imagens tambem começa do zero                                                  
    loops = new ArrayList<Loop>();
    //inicializa o arrayList
  }// construtor que inicializa os dados 

  public void importIm() {
    for (int i = frameInicial; i < totalFrames; i++) {    
      String nomeIm = prefix + nf(i, 5) + ".png";  
      frames[i] = loadImage(nomeIm);
      //nf() serve como um "display contator", nesse caso 
      //usado para os numeros do nome da imagem
    }
  }//função que le as imagens da pasta com um laço

  public void criarLoop(String nome, int n, int i, int f, String e, boolean s, boolean a) {
    Loop l = new Loop(nome, n, i, f, s, a);//instancia um novo loop na memoria   
    loops.add(l);//add o loop no array de loops    
    l.setFundo(e);//define o fundo para o endereço correspondente a string "e"   
    qtdLoops++;//incrementa a variavel q conta os loops
    
    println("loop " + nome + " add a animacao");
  }//cria um loop e add ele ao array
  
  public void addBotao(int n, String nome, int x, int y, int h, int w, boolean ba, boolean ne) {
    //n = numero do loop no qual tu vai add o botao
    for (int i = 0; i < n; i++) {
      if (loops.get(i).getN() == n) {
        //se achou o loop
        loops.get(i).addBotao(nome, x, y, h, w, ba, ne);
        //add o botao nele
      }
    }//vasculha o array loop em busca do numero fornecido
  }//ADD BTAO
  
  public void addHome(int n, String nome, int x, int y, int h, int w, boolean home, int an, int lo) {
    //n = numero do loop no qual tu vai add o botao
    for (int i = 0; i < n; i++) {
      if (loops.get(i).getN() == n) {
        //se achou o loop
        loops.get(i).addHome(nome, x, y, h, w, home, an, lo);
        //add o botao nele
      }
    }//vasculha o array loop em busca do numero fornecido
  }//ADD BTAO
}//CLASS ANIMACAO