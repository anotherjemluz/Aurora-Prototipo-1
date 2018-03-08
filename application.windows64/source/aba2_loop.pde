//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
public class Loop {
  private String nome;//nome do loop (etapa)
  private int n;//numero do loop 
  private int fi;//indice do frame inicial
  private int ff;//indice do frame final
  private int inicio;//fixa o valor de do frame inicial  
  private PImage fundo;//fundo da animacao
  
  private boolean auto;//define se o loop ira rodar uma vez só (FALSE), ou repetidamente (TRUE)
  private boolean statico;//define se o loop vai depender de um clique para passar para o próximo loop (TRUE), ou se passa automaticamente (FALSE)
  
  private ArrayList<Botao> botoes;//armazena os botões do intervalo correnpondente
  private boolean temBotao = false;//

  private Loop(String nome, int n, int i, int f, boolean s, boolean a) {
    this.nome = nome;
    this.n = n;
    this.fi = i;
    this.ff = f;
    this.inicio = i;
    
    this.auto = a;
    this.statico = s;
    
    botoes = new ArrayList<Botao>();
  }//inicializa as variaveis de um loop restrito
  
  public void addBotao(String nome, int x, int y, int xf, int yf, boolean ba, boolean ne) {
    if (statico) {//apenas se for um loop estático
      Botao b = new Botao(nome, x, y, xf, yf, ba, ne);//instancia um novo botao na memoria
      botoes.add(b);//adiciona o botao ao arraylist botoes
      temBotao = true;//
      
      println("Botao " + nome + " add a animacao");
    } else {
      println("Esse não é um loop estático, você não pode adicionar botões");
    }
  }//adiciona um botao
  
  public void addHome(String nome, int x, int y, int xf, int yf, boolean home, int an, int lo) {
    if (statico) {//apenas se for um loop estático
      Botao b = new Botao(nome, x, y, xf, yf, home, an, lo);//instancia um novo botao na memoria
      botoes.add(b);//adiciona o botao ao arraylist botoes
      temBotao = true;
      
      println("Home " + nome + " add a animacao");
    } else {
      println("Esse não é um loop estático, você não pode adicionar botões");
    }
  }//adiciona um botao

  public void setFundo(String endereco) {
    this.fundo = loadImage(endereco);
  }//define o fundo da animação
  
  public PImage getFundo() {
    return fundo;
  }//retorna o fundo da animação
  
  public int getN() {
    return n;
  }//retorna o numero da animação
}