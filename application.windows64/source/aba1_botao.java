import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class aba1_botao extends PApplet {

//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
public class Botao {
  private String nome;//nome la do botao
  private int x;//cordenada x
  private int y;//cordenada y  
  private int xf;//x final
  private int yf;//y final
  private int h;//altura
  private int w;//largura
  
  private int an;//variavel que define o indice da anima\u00e7\u00e3o desejada
  private int lo;//variavel que define o indice do loop desejado
  private boolean home = false;//se for um botao que vai pra qualquer lugar que vc queira
  
  private boolean next = false;//se for um botao que exibe o proximo loop
  private boolean back = false;//se for um botao que exibe o loop anterior
  
  Botao(String nome, int x, int y, int xf, int yf, boolean ba, boolean ne) {
    if (ba & ne) {
      //valida\u00e7\u00e3o
      println("O bot\u00e3o s\u00f3 pode ter um boolean como true");
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
//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
public class Loop {
  private String nome;//nome do loop (etapa)
  private int n;//numero do loop 
  private int fi;//indice do frame inicial
  private int ff;//indice do frame final
  private int inicio;//fixa o valor de do frame inicial  
  private PImage fundo;//fundo da animacao
  
  private boolean auto;//define se o loop ira rodar uma vez s\u00f3 (FALSE), ou repetidamente (TRUE)
  private boolean statico;//define se o loop vai depender de um clique para passar para o pr\u00f3ximo loop (TRUE), ou se passa automaticamente (FALSE)
  
  private ArrayList<Botao> botoes;//armazena os bot\u00f5es do intervalo correnpondente
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
    if (statico) {//apenas se for um loop est\u00e1tico
      Botao b = new Botao(nome, x, y, xf, yf, ba, ne);//instancia um novo botao na memoria
      botoes.add(b);//adiciona o botao ao arraylist botoes
      temBotao = true;//
      
      println("Botao " + nome + " add a animacao");
    } else {
      println("Esse n\u00e3o \u00e9 um loop est\u00e1tico, voc\u00ea n\u00e3o pode adicionar bot\u00f5es");
    }
  }//adiciona um botao
  
  public void addHome(String nome, int x, int y, int xf, int yf, boolean home, int an, int lo) {
    if (statico) {//apenas se for um loop est\u00e1tico
      Botao b = new Botao(nome, x, y, xf, yf, home, an, lo);//instancia um novo botao na memoria
      botoes.add(b);//adiciona o botao ao arraylist botoes
      temBotao = true;
      
      println("Home " + nome + " add a animacao");
    } else {
      println("Esse n\u00e3o \u00e9 um loop est\u00e1tico, voc\u00ea n\u00e3o pode adicionar bot\u00f5es");
    }
  }//adiciona um botao

  public void setFundo(String endereco) {
    this.fundo = loadImage(endereco);
  }//define o fundo da anima\u00e7\u00e3o
  
  public PImage getFundo() {
    return fundo;
  }//retorna o fundo da anima\u00e7\u00e3o
  
  public int getN() {
    return n;
  }//retorna o numero da anima\u00e7\u00e3o
}
//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
public class Animacao {
  private String prefix;//prefix do nome da imagem 

  private int frameInicial;//frame inicial do trecho para importa\u00e7\u00e3o
  private int totalFrames;//numero de frames da pasta
  private int frameAtual;//frame "exibido" no momento

  private ArrayList<Loop> loops;//array que armazena os loops
  private int qtdLoops;//variavel que armazena a quantidade de loops

  private PImage[] frames;//array para armazenar as imagens da anima\u00e7\u00e3o

  public Animacao (String prefix, int frameInicial, int totalFrames) {
    this.prefix = prefix; 
    this.totalFrames = totalFrames;
    this.frameInicial = frameInicial;
    this.frameAtual = frameInicial;
    
    frames = new PImage[totalFrames + 1];//inicializa o array, e sem o +1 da erro de ArrayOutOfBoundsExeption
                                         //j\u00e1 que o indice das imagens tambem come\u00e7a do zero                                                  
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
  }//fun\u00e7\u00e3o que le as imagens da pasta com um la\u00e7o

  public void criarLoop(String nome, int n, int i, int f, String e, boolean s, boolean a) {
    Loop l = new Loop(nome, n, i, f, s, a);//instancia um novo loop na memoria   
    loops.add(l);//add o loop no array de loops    
    l.setFundo(e);//define o fundo para o endere\u00e7o correspondente a string "e"   
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
//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
public class Animacoes {
  private ArrayList<Animacao> animacoes;
  int qtdAni = 0;
  int aniAtual = 0;
  int loopAtual = 0;

  Animacoes() {
    animacoes = new ArrayList<Animacao>();
  }//construtor

  public void addAni(Animacao nome) {
    animacoes.add(nome);
    qtdAni++;
    println("elemento " + animacoes.size() + " add ao array animacoes");
  }//fun\u00e7\u00e3o pra add anima\u00e7\u00e3o
//991657881
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  //---------------------------------------------- A ________ \u00c9 AQUI \/ ----------------------------------------------------------------------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  public void executar() {
    //\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bcIMPRESS\u00c3O\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc
    //PARTE 1: EFEITO ANIMA\u00c7\u00c3O I
      image(animacoes.get(aniAtual).loops.get(loopAtual).getFundo(), 0, 0);//imprime o fundo
      image(animacoes.get(aniAtual).frames[animacoes.get(aniAtual).frameAtual], 0, 0);//imprime o gif com transparencia 
      //imprime ai vai
      println("  imprimiu" + animacoes.get(aniAtual).frameAtual);
    //PARTE 1: EFEITO ANIMA\u00c7\u00c3O I
    
    //PARTE 2: HABILITA OS BOT\u00d5ES
      if (animacoes.get(aniAtual).loops.get(loopAtual).temBotao) {
      //  puxa a variavel temBotal do loop atual da anima\u00e7\u00e3o atual
        for (int i = 0; i < animacoes.get(aniAtual).loops.get(loopAtual).botoes.size(); i++) {
          //   come\u00e7a igual| enquanto i < a qtd de botoes do loop atua da anima\u00e7cao atual| incrementa i 
          //   a zero      |                                                             | 
          //
          animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(i).on();
          //dispoe o dito bot\u00e3o do loop atual da anima\u00e7\u00e3o atual
        }//FIM DO FOR "DISP\u00d5E OS BOTOES"
      }// FIM DO SE O "LOOP TIVER BOTOES, DISPONHA-OS NA TELA"
    //PARTE 2: HABILITA OS BOT\u00d5ES

    //PARTE 3: EFEITO ANIMA\u00c7\u00c3O II
      if (animacoes.get(aniAtual).frameAtual < animacoes.get(aniAtual).loops.get(loopAtual).ff) {
        //puxa o frame atual da anima\u00e7\u00e3o   | < |puxa o ultimo frame do loop atual da anima\u00e7\u00e3o atual
        //atual                            |   |
        //
        animacoes.get(aniAtual).frameAtual++;//incrementa o frame atual da anima\u00e7\u00e3o atual
        //
        print("incrementou para " + animacoes.get(aniAtual).frameAtual);
      }//FIM DO SE "AINDA EXISTEM FRAMES PARA EXECUTAR NO LOOP, INCREMENTE"    
    //PARTE 3: EFEITO ANIMA\u00c7\u00c3O II
    //\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2

    //\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac


    //\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bcVERIFICA\u00c7\u00c3O\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc\u25bc
    //PARTE 4: VERIFICA\u00c7\u00c3O
      if (animacoes.get(aniAtual).frameAtual == animacoes.get(aniAtual).loops.get(loopAtual).ff) {
        //  puxa o frame atua da animacao atual == puxa o ultimo frame do loop atual da anima\u00e7\u00e3o atual
        //
        println("chegou no fim da linha ");
        //SE J\u00c1 CHEGOU AO FIM DO LOOP

        if (loopAtual < animacoes.get(aniAtual).qtdLoops) {
        //  puxa  o  | < |puxa a quantidade de loops da 
        //  loopAtual|   |anima\u00e7ao atual
        //  
        //E SE AINDA TEM LOOPS PENDENTES

        //\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022 CASO 1 (SE FOR UM LOOP EST\u00c1TICO E AUTOM\u00c1TICO) \u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022
          if (animacoes.get(aniAtual).loops.get(loopAtual).statico & animacoes.get(aniAtual).loops.get(loopAtual).auto) {           
          //  puxa a variavel statico do loop atual da animacao  | & |puxa a variavel auto do loop atual da animacao 
          //  atua                                               |   |atual
          //
          println("caso 1"); 
          //SE FOR UM LOOP ESTATICO E AUTOMATICO

          //\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591ESSE CODIGO SE REPETE, CUIDADO PRA N\u00c3O BUGAR YOUR MIND\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591
          if (click) {
            println("cliquei " + mousex + " " + mousey + "/" + animacoes.get(aniAtual).loops.get(loopAtual).botoes.size());
            //SE HOUVE CLIQUE

            if (animacoes.get(aniAtual).loops.get(loopAtual).temBotao) {
              for (int b = 1; b < animacoes.get(aniAtual).loops.get(loopAtual).botoes.size() + 1; b++) {   
                //   b come\u00e7a | enquanto b < a quantidade de bot\u00f5es do loop atual da anima\u00e7ao | incremente b
                //   igua a 0 | atual (se n\u00e3o tiver o +1 os bot\u00f5es de avan\u00e7ar n\u00e3o funcionam)   
                println("tem botao aqui" + b);
                //VERIFIQUE SE H\u00c1 BOT\u00d5ES NO LOOP

                if (mousex > animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).x & mousex < animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).xf &
                  mousey > animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).y & mousey < animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).yf) {
                  //  se mousex e mousey estiverem dentro da area de um dos botoes.
                  //  mousex e mousey =  valor de x e y no momento do clique.
                  //  b-1 \u00e9 usado pra compensar a diferen\u00e7a do indice do array para o tamanho do array
                  println("cliquei no " + animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).nome);
                  println("q" + animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).home);
                  // SE O CLIQUE FOR DENTRO DE ALGUM BOT\u00c3O

                  //\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8
                  if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).home) {
                    int an = animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).an;
                    int lo = animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).lo;
                    aniAtual = an;//defina a anima\u00e7\u00e3o da home
                    loopAtual = lo;//defina o loop da home
                    animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).inicio;
                    animacoes.get(aniAtual).loops.get(loopAtual);//execute-o
                    click = false;//n\u00e3o esquecer de atualizar o status do clique                       
                    println("loop incrementado e rodando");
                    break;//feito
                  } else if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).next) {
                    //puxa o booleano next do loop anterior
                    //
                    //SE O BOT\u00c3O FOR DE AVAN\u00c7AR

                    if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) <= animacoes.get(aniAtual).loops.size()) {
                      //   puxa o numero do loop atual da anima\u00e7\u00e3o atual + 1  |<= |puxa aquantidade de looops na animacao atual
                      //
                      println("restam loops");
                      //SE EXISTIR MAIS LOOPS DEPOIS DESSE (NA ANIMA\u00c7\u00c3O ATUAL)                     

                      for (int i = loopAtual; i < animacoes.get(aniAtual).loops.size(); i++) {              
                        //   i come\u00e7a igual ao| enquanto i < a quantidade de loops na   | incremente i
                        //   valor d'loopAtual| anima\u00e7\u00e3o atual                       
                        println("tamanho do loop: " + animacoes.get(aniAtual).loops.size());
                        println("i = " + i);
                        println("loopAtual = " + loopAtual);
                        //VASCULHE ESSE ARRAY

                        if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) & i < animacoes.get(aniAtual).loops.size()) {
                          //  puxa o numero do proximo looop da anima\u00e7\u00e3o| == |puxa o numero do loop da anima\u00e7\u00e3o atua + 1             | & | i | < |a quantidade total de loops na
                          //  atual                                     |    |                                                       |     v     | anima\u00e7\u00e3o atual
                          //                                                                                                               v 
                          //                                                                                                         (proximo loop)
                          //SE FOR ACHADO O PROXIMO LOOP

                          loopAtual++;//v\u00e1 para o pr\u00f3ximo loop
                          animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).inicio;//define o frame atual como frame inicial do proximo loop
                          animacoes.get(aniAtual).loops.get(i);//e execute-o
                          click = false;//n\u00e3o esquecer de atualizar o status do clique                       
                          println("loop incrementado e rodando");
                          break;//feito
                        }//FIM DO SE "FOR ACHADO O PROXIMO LOOP"
                      }//FIM DO FOR "VASCULHE ESSE ARRAY"
                    }//FIM DO SE "EXISTIR MAIS LOOPS DEPOIS DESSE"
                    else if (aniAtual < qtdAni - 1) {
                      //      puxa  a  |<|puxa a       |- 1
                      //      anima\u00e7ao | |quantidade   |
                      //      atual    | |de anima\u00e7\u00f5es |
                      //
                      //SEN\u00c3O TIVER MAIS LOOP NA ANIMA\u00c7\u00c3O ATUAL E SE AINDA TIVER ANIMA\u00c7\u00c3O

                      aniAtual++;//v\u00e1 para a pr\u00f3xima anima\u00e7\u00e3o
                      loopAtual = 0;//e come\u00e7e pelo primeiro loop
                      println("pr\u00f3xima anima\u00e7\u00e3o irru");
                    }//FIM DO SEN\u00c3O "TIVER MAIS LOOP NA ANIMA\u00c7\u00c3O ATUAL", E SE "AINDA TIVER ANIMA\u00c7\u00c3O"
                  }//FIM DO IF(SE O BOT\u00c3O FOR DE AVAN\u00c7AR)

                  //\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8

                  else if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).back) {
                    //SE O BOT\u00c3O FOR DE VOLTAR
                    if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() - 1) >= animacoes.get(0).loops.get(0).getN()) {
                      //   puxa o numero do loop atual da anima\u00e7\u00e3o atual + 1  |<= |puxa o numero do primeiro loop da primeira anima\u00e7\u00e3o
                      //
                      println("existem loops anteriores" + loopAtual);
                      //SE EXISTIR LOOPS ANTERIORES A ESSE                      

                      for (int i = loopAtual; i > -1; i--) {              
                        //   i come\u00e7a igual ao| enquanto i > a o primeiro loop da     | decremente i
                        //   valor d'loopAtual| primeira animacao                       
                        //
                        //VASCULHE ESSE ARRAY                    
                        println("i = " + i);
                        println("loopAtual = " + loopAtual);

                        if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() - 1)) {
                          //  puxa o numero do ultimo looop da         | == |puxa o numero do loop atual da anima\u00e7\u00e3o atual - 1   
                          //  anima\u00e7\u00e3o atual                           |    |                                                                                                                                                                   
                          //                                                                                                     
                          //SE FOR ACHADO O LOOP ANTERIOR

                          loopAtual--; 
                          //v\u00e1 para o loop anterior
                          animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).inicio;//defina o frameAtual para o frame inicial do 
                          animacoes.get(aniAtual).loops.get(i);//execute-o
                          click = false;//n\u00e3o esque\u00e7a de att o status de clique
                          println("loop decrementado e rodando");
                          break;//feito
                        }//FIM DO SE "FOR ACHADO O LOOP ANTERIOR"
                      }//FIM DO FOR "VASCULHE ESSE ARRAY"
                    }//FIM DO SE "EXISTIR LOOPS ANTERIORES A ESSE"
                    else if (aniAtual > 0) {
                      //      puxa  a  |<|puxa a       |- 1
                      //      anima\u00e7ao | |quantidade   |
                      //      atual    | |de anima\u00e7\u00f5es |
                      //
                      //SE N\u00c3O EXISTIR LOOPS ANTERIORES, E SE TIVER UMA ANIMA\u00c7\u00c3O ANTERIOR

                      aniAtual--;//v\u00e1 para a anima\u00e7\u00e3o anterior
                      loopAtual = animacoes.get(aniAtual).qtdLoops;//e come\u00e7e pelo ultimo loop
                      println("anima\u00e7\u00e3o anterior irru " + aniAtual);
                    }//FIM DO SE N\u00c3O "EXISTIR LOOPS ANTERIORES" , E SE "TIVER UMA ANIMA\u00c7\u00c3O ANTERIOR"
                  }//FIM DO SE O "BOT\u00c3O FOR DE VOLTAR"

                  //\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8
                }//FIM DO SE O "CLIQUE FOI DENTRO DE UM BOT\u00c3O" 
                //\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591                
                else {
                  //SE O CLIQUE N\u00c3O FOI DENTRO DE UM BOTAO CONTINUE A RODAR O LOOP
                  //
                  animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).inicio;
                  println("n\u00e3o clicaram em um bot\u00e3o");
                  //puxa o frame atual da anima\u00e7\u00e3o  |=|puxa o frame inicial do loop atual da anima\u00e7\u00e3o atual
                  //atual
                }//FIM DO SE O "CLIQUE N\u00c3O FOI DENTRO DE UM BOTAO CONTINUE A RODAR O LOOP"
              }//FIM FO FOR "VERIFIQUE SE H\u00c1 BOTOES NO LOOP"
            }//FIM DO SE "TEM BOT\u00c3O"
          }//FIM DO SE  HOUVE CLIQUE
           else {
            //SE N\u00c3O HOUVE CLIQUE REPITA O LOOP
            //
            animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).inicio;
            //puxa o frame atual da anima\u00e7\u00e3o  |=|puxa o frame inicial do loop atual da anima\u00e7\u00e3o atual
            //atual
            println("n\u00e3o clicaram em um bot\u00e3o");                 
           }//FIM DO SE N\u00c3O "HOUVE CLIQUE REPITA O LOOP"
        }//\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022 FIM DO CASO 1 (SE FOR UM LOOP EST\u00c1TICO E AUTOM\u00c1TICO) \u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022        

        //\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022 CASO 2 (SE FOR UM LOOP EST\u00c1TICO E N\u00c3O FOR AUTOM\u00c1TICO) \u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022
        else if (animacoes.get(aniAtual).loops.get(loopAtual).statico & animacoes.get(aniAtual).loops.get(loopAtual).auto == false) {
          //     puxa a variavel statico do loop atual da animacao  | & |puxa a variavel auto do loop atual da animacao atual
          //     atual                                              |   
          //SE FOR UM LOOP EST\u00c1TICO E N\u00c3O AUTOM\u00c1TICO
          println("caso 2");

          //\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591ESSE CODIGO SE REPETE, CUIDADO PRA N\u00c3O BUGAR YOUR MIND\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591
          if (click) {
            println("cliquei " + mousex + " " + mousey + "/" + animacoes.get(aniAtual).loops.get(loopAtual).botoes.size());
            //SE HOUVE CLIQUE 

            if (animacoes.get(aniAtual).loops.get(loopAtual).temBotao) {
              for (int b = 1; b < animacoes.get(aniAtual).loops.get(loopAtual).botoes.size() + 1; b++) {   
                //   b come\u00e7a | enquanto b < a quantidade de bot\u00f5es do loop atual da anima\u00e7ao | incremente b
                //   igua a 0 | atual (se n\u00e3o tiver o +1 os bot\u00f5es de avan\u00e7ar n\u00e3o funcionam)   
                println("tem botao aqui" + b);
                //VERIFIQUE SE H\u00c1 BOT\u00d5ES NO LOOP

                if (mousex > animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).x & mousex < animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).xf &
                  mousey > animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).y & mousey < animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).yf) {
                  //  se mousex e mousey estiverem dentro da area de um dos botoes.
                  //  mousex e mousey =  valor de x e y no momento do clique.
                  //  b-1 \u00e9 usado pra compensar a diferen\u00e7a do indice do array para o tamanho do array
                  println("cliquei no " + animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).nome);
                  // SE O CLIQUE FOR DENTRO DE ALGUM BOT\u00c3O

                  //\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8
                  if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).home) {
                    int an = animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).an;
                    int lo = animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).lo;
                    aniAtual = an;//defina a anima\u00e7\u00e3o da home
                    loopAtual = lo;//defina o loop da home
                    animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).inicio;
                    animacoes.get(aniAtual).loops.get(loopAtual);
                    click = false;//n\u00e3o esquecer de atualizar o status do clique                       
                    println("home ativado");
                    break;//feito
                  } //
                  else if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).next) {
                    //puxa o booleano next do loop anterior
                    //
                    //SE O BOT\u00c3O FOR DE AVAN\u00c7AR

                    if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) <= animacoes.get(aniAtual).loops.size()) {
                      //   puxa o numero do loop atual da anima\u00e7\u00e3o atual + 1  |<= |puxa aquantidade de looops na animacao atual
                      //
                      println("restam loops");
                      //SE EXISTIR MAIS LOOPS DEPOIS DESSE (NA ANIMA\u00c7\u00c3O ATUAL)                     

                      for (int i = loopAtual; i < animacoes.get(aniAtual).loops.size(); i++) {              
                        //   i come\u00e7a igual ao| enquanto i < a quantidade de loops na   | incremente i
                        //   valor d'loopAtual| anima\u00e7\u00e3o atual                       
                        println("tamanho do loop: " + animacoes.get(aniAtual).loops.size());
                        println("i = " + i);
                        println("loopAtual = " + loopAtual);
                        //VASCULHE ESSE ARRAY

                        if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) & i < animacoes.get(aniAtual).loops.size()) {
                          //  puxa o numero do proximo looop da anima\u00e7\u00e3o| == |puxa o numero do loop da anima\u00e7\u00e3o atua + 1             | & | i | < |a quantidade total de loops na
                          //  atual                                     |    |                                                       |     v     | anima\u00e7\u00e3o atual
                          //                                                                                                               v 
                          //                                                                                                         (proximo loop)
                          //SE FOR ACHADO O PROXIMO LOOP

                          loopAtual++;//v\u00e1 para o pr\u00f3ximo loop
                          animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).fi;//define o frame atual como frame inicial do proximo loop
                          animacoes.get(aniAtual).loops.get(i);//e execute-o
                          click = false;//n\u00e3o esquecer de atualizar o status do clique                       
                          println("loop incrementado e rodando");
                          break;//feito
                        }//FIM DO SE "FOR ACHADO O PROXIMO LOOP"
                      }//FIM DO FOR "VASCULHE ESSE ARRAY"
                    }//FIM DO SE "EXISTIR MAIS LOOPS DEPOIS DESSE"
                    else if (aniAtual < qtdAni - 1) {
                      //      puxa  a  |<|puxa a       |- 1
                      //      anima\u00e7ao | |quantidade   |
                      //      atual    | |de anima\u00e7\u00f5es |
                      //
                      //SEN\u00c3O TIVER MAIS LOOP NA ANIMA\u00c7\u00c3O ATUAL E SE AINDA TIVER ANIMA\u00c7\u00c3O

                      aniAtual++;
                      //v\u00e1 para a pr\u00f3xima anima\u00e7\u00e3o
                      loopAtual = 0;              
                      //e come\u00e7e pelo primeiro loop
                      println("pr\u00f3xima anima\u00e7\u00e3o irru");
                    }//FIM DO SEN\u00c3O "TIVER MAIS LOOP NA ANIMA\u00c7\u00c3O ATUAL", E SE "AINDA TIVER ANIMA\u00c7\u00c3O"
                  }//FIM DO IF(SE O BOT\u00c3O FOR DE AVAN\u00c7AR)

                  //\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8

                  else if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).back) {
                    //SE O BOT\u00c3O FOR DE VOLTAR
                    if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() - 1) >= animacoes.get(0).loops.get(0).getN()) {
                      //   puxa o numero do loop atual da anima\u00e7\u00e3o atual + 1  |<= |puxa o numero do primeiro loop da primeira anima\u00e7\u00e3o
                      //
                      println("existem loops anteriores" + loopAtual);
                      //SE EXISTIR LOOPS ANTERIORES A ESSE                      

                      for (int i = loopAtual; i > -1; i--) {              
                        //   i come\u00e7a igual ao| enquanto i > a o primeiro loop da     | decremente i
                        //   valor d'loopAtual| primeira animacao                       
                        //
                        //VASCULHE ESSE ARRAY                    
                        println("i = " + i);
                        println("loopAtual = " + loopAtual);

                        if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() - 1)) {
                          //  puxa o numero do ultimo looop da         | == |puxa o numero do loop atual da anima\u00e7\u00e3o atual - 1   
                          //  anima\u00e7\u00e3o atual                           |    |                                                                                                                                                                   
                          //                                                                                                     
                          //SE FOR ACHADO O LOOP ANTERIOR

                          loopAtual--; 
                          //v\u00e1 para o loop anterior
                          animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).inicio;//defina o frameAtual para o frame inicial do 
                          animacoes.get(aniAtual).loops.get(i);//execute-o
                          click = false;//n\u00e3o esque\u00e7a de att o status de clique
                          println("loop decrementado e rodando");
                          break;//feito
                        }//FIM DO SE "FOR ACHADO O LOOP ANTERIOR"
                      }//FIM DO FOR "VASCULHE ESSE ARRAY"
                    }//FIM DO SE "EXISTIR LOOPS ANTERIORES A ESSE"
                    else if (aniAtual > 0) {
                      //      puxa  a  |<|puxa a       |- 1
                      //      anima\u00e7ao | |quantidade   |
                      //      atual    | |de anima\u00e7\u00f5es |
                      //
                      //SE N\u00c3O EXISTIR LOOPS ANTERIORES, E SE TIVER UMA ANIMA\u00c7\u00c3O ANTERIOR

                      aniAtual--;//v\u00e1 para a anima\u00e7\u00e3o anterior
                      loopAtual = animacoes.get(aniAtual).qtdLoops;//e come\u00e7e pelo ultimo loop
                      println("anima\u00e7\u00e3o anterior irru " + aniAtual);
                    }//FIM DO SE N\u00c3O "EXISTIR LOOPS ANTERIORES" , E SE "TIVER UMA ANIMA\u00c7\u00c3O ANTERIOR"
                  }//FIM DO SE O "BOT\u00c3O FOR DE VOLTAR"

                  //\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8\u25d8
                }//FIM DO SE O "CLIQUE FOI DENTRO DE UM BOT\u00c3O" 
                //\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591                               
              }//FIM FO FOR "VERIFIQUE SE H\u00c1 BOTOES NO LOOP"
            }//FIM DO SE "TEM BOT\u00c3O"
          }//FIM DO SE "HOUVE CLIQUE"
        }//\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022 FIM DO CASO 2 (SE FOR UM LOOP EST\u00c1TICO E N\u00c3O FOR AUTOM\u00c1TICO) \u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022

        //\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022 CASO 3 (SE N\u00c3O FOR UM LOOP EST\u00c1TICO E NEM AUTOM\u00c1TICO) \u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022 
        else if (animacoes.get(aniAtual).loops.get(loopAtual).statico == false & animacoes.get(aniAtual).loops.get(loopAtual).auto == false) {
          //se n\u00e3o for um loop est\u00e1tico e nem autom\u00e1fico
          println("caso 3");

          //\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591ESSE CODIGO SE REPETE, CUIDADO PRA N\u00c3O BUGAR YOUR MIND\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591 OLD VERSION \u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591
          if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) <= animacoes.get(aniAtual).loops.size()) {
            //   puxa o numero do loop atual da anima\u00e7\u00e3o atual + 1  |<= |puxa aquantidade de looops na animacao atual
            //
            println("restam loops");
            //SE EXISTIR MAIS LOOPS NO ARRAY DA ANIMA\u00c7\u00c3O DEPOIS DESSE

            for (int i = loopAtual; i < animacoes.get(aniAtual).loops.size(); i++) {              
              //   i come\u00e7a igual ao| enquanto i < a quantidade de loops na   | incremente i
              //   valor d'loopAtual| anima\u00e7\u00e3o atual                       
              //
              //VASCULHE ESSE ARRAY
              println("qtd loops: " + animacoes.get(aniAtual).loops.size());
              println("i = " + i);
              println("loopAtual = " + loopAtual);

              if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) & i < animacoes.get(aniAtual).loops.size()) {
                //  puxa o numero do proximo looop da anima\u00e7\u00e3o| == |puxa o numero do loop da anima\u00e7\u00e3o atua + 1             | & | i | < |a quantidade total de loops na
                //  atual                                     |    |                                                       |     v     | anima\u00e7\u00e3o atual
                //                                                                                                               v 
                //                                                                                                         (proximo loop)
                //SE FOR ACHADO O PROXIMO LOOP

                loopAtual++; 
                //v\u00e1 para o pr\u00f3ximo loop
                animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).fi;
                animacoes.get(aniAtual).loops.get(i);
                // e execute-o
                println("loop incrementado e rodando");
                break;
                //feito
              }
            }
          } else if (aniAtual < qtdAni - 1) {
            //      puxa  a  |<|puxa a       |- 1
            //      anima\u00e7ao | |quantidade   |
            //      atual    | |de anima\u00e7\u00f5es |
            //
            //SE N\u00c3O TIVER MAIS LOOP NA ANIMA\u00c7\u00c3O ATUAL E SE AINDA TIVER ANIMA\u00c7\u00c3O

            aniAtual++;
            //v\u00e1 para a pr\u00f3xima anima\u00e7\u00e3o
            loopAtual = 0;
            animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).fi;
            println("a" + animacoes.get(aniAtual).frameAtual);
            //e come\u00e7e pelo primeiro loop
            println("pr\u00f3xima anima\u00e7\u00e3o irru");
          }
          //\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591
        }//\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022 FIM DO CASO 3 (SE N\u00c3O FOR UM LOOP EST\u00c1TICO E NEM AUTOM\u00c1TICO) \u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022
      }//FIM DO SE "AINDA RESTAM LOOPS
    }//\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2FIM DO SE 'JA CHEGOU AO FIM DO LOOP" --- VERIFICACAO \u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2\u25b2
     //PARTE 4: VERIFICA\u00c7\u00c3O
  }///FIM DO EXECUTAR
}//FIM DA CLASSE ANIMACOES
//14
//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
//DESENVOLVIDO POR 5IGHTS

//NOTAS ALEATORIAS PARA MARCA\u00c7\u00c3O
//1\u263a 2\u263b 3\u2665 4\u2666 5\u2663 6\u2660 7\u2022 8\u25d8 9\u25cb 10\u25d9 11\u2642 12\u2640 13\u266a 14\u266b 15\u263c 16\u25ba 17\u25c4 18\u2195 19\u203c 20\u00b6 21\u00a7 22\u25ac 23\u21a8 24\u2191 25\u2193 26\u2192 27\u2190 28\u221f 29\u2194 30\u25b2 31\u25bc 
//180\u2524 179\u2502 178\u2593 177\u2592 176\u2591 \u2563 \u00a9 

public void settings() {
  size(360, 540);
}//------------------------------------- tamanho da tela ----------------------------------------------------------

//-------------------------------------- local de instancializa\u00e7\u00e3o ------------------------------------------------
boolean click;
int mousex;
int mousey;

//Animacao nome = new Animacao(prefixo, frameInicial, totalFrames);
Animacao sights = new Animacao("animacoes/inicio/5ights/logo_", 0, 100);//pasta inicio
Animacao dote = new Animacao("animacoes/inicio/dote/logo_", 101, 176);//pasta inicio
Animacao aurora = new Animacao("animacoes/inicio/aurora/aurora_", 177, 326);//pasta inicio


Animacao menucomeco = new Animacao("animacoes/menu/fadin_aurora_ft_comecar_to_menu/menu_", 327, 339);//pasta menu
Animacao menufadin = new Animacao("animacoes/menu/fadin_menu/menu_", 340, 354);//pasta menu
Animacao menuestatico = new Animacao("animacoes/menu/menu_estatico/menu_", 355, 357);//pasta menu
Animacao infoclicado = new Animacao("animacoes/menu/info_clicado/menu_", 357, 361);//pasta menu
Animacao suicidioclicado = new Animacao("animacoes/menu/suicidio_clicado/menu_", 362, 366);//pasta menu
Animacao menufadout = new Animacao("animacoes/menu/fadout_menu/menu_", 367, 374);//pasta menu


Animacao indobolaum = new Animacao("animacoes/sessao_1/indo/menu_", 362, 374);//pasta sessao_1
Animacao tutorial = new Animacao("animacoes/sessao_1/tutorial/tutorial_", 375, 400);//pasta sessao_1
Animacao bolaum = new Animacao("animacoes/sessao_1/baloes/balao_", 0, 1396);//pasta sessao_1

Animacao faqvoltabolaum = new Animacao("animacoes/faq/volta_bolaum/faq_", 400, 414);//pasta faq
Animacao faqdesbloq = new Animacao("animacoes/faq/faq_desbloqueando/faq_", 415, 445);//pasta faq
Animacao faqestatico = new Animacao("animacoes/faq/faq_estatico/faq_", 445, 447);//pasta faq
Animacao faqclicado = new Animacao("animacoes/faq/faq_clicado/faq_", 448, 452);//pasta faq

Animacao smfadin = new Animacao("animacoes/faq/saibamais/saibamais_fadin/smfadin_", 453, 469);//pasta faq>saibamais
Animacao smfadout = new Animacao("animacoes/faq/saibamais/volta_saibamais/smfadout_", 470, 495);//pasta faq>saibamais



Animacoes prog = new Animacoes();
//------------------------------------------------------------------------------------------------------------------

public void setup() {
  frameRate(25);
  background(220);

  //objeto.criarLoop("nome do loop", numero do loop, frameInicial, frameFinal, fundo, statico, auto);
  //objeto.addBotao(loop do botao, nome, x, y, xf, yf, voltar, avan\u00e7ar);

  sights.importIm(); 
  
  sights.criarLoop("5ights", 1, 0, 100, "animacoes/inicio/f5ights.png", false, false);

  dote.importIm(); 
  dote.criarLoop("dote", 1, 101, 176, "animacoes/inicio/fundote.png", false, false);

  aurora.importIm();  
  aurora.criarLoop("aurora", 1, 177, 284, "animacoes/inicio/fundo.png", false, false);
  aurora.criarLoop("aurora ft.come\u00e7ar", 2, 284, 326, "animacoes/inicio/fundo.png", true, true);
  //aurora.criarLoop("comecar_clicado", 3, 251, 274, "animacoes/inicio/fundo.png", false, false);  
  aurora.addHome(2, "come\u00e7ar", 124, 465, 256, 492, true, 3, 0);//menu 1  -> fadin menu
  
  // ---------------------------------------------------------------------------------------------------
  
  menucomeco.importIm();
  menucomeco.criarLoop("fadin aurora ft.come\u00e7ar > menu", 1, 327, 339, "animacoes/inicio/fundo.png", false, false);
  
  menufadin.importIm();
  menufadin.criarLoop("fadin menu", 1, 340, 354, "animacoes/inicio/fundo.png", false, false);
  
  menuestatico.importIm();
  menuestatico.criarLoop("menu estatico", 1, 355, 356, "animacoes/inicio/fundo.png", true, false);
  menuestatico.addHome(1, "suic\u00eddio?", 43, 90, 135, 180, true, 9, 0);//fadeb 1 -> saida menu
  //menuestatico.addHome(1, "info", 310, 15, 350, 50, true, 7, 0);//menu 3 -> lugarnenhum ops
  
  infoclicado.importIm();
  infoclicado.criarLoop("info clicado", 1, 357, 361, "animacoes/inicio/fundo.png", false, false); 
  
  suicidioclicado.importIm();
  suicidioclicado.criarLoop("suicidio clicado", 1, 362, 366, "animacoes/inicio/fundo.png", false, false);
  
  menufadout.importIm();
  menufadout.criarLoop("fadout menu", 1, 367, 374, "animacoes/inicio/fundo.png", false, false);  

 //-----------------------------------------------------------------------------------------------------------------
 
  indobolaum.importIm();
  indobolaum.criarLoop("saida menu", 1, 362, 374, "animacoes/inicio/fundo.png", false, false);
  
  tutorial.importIm();
  tutorial.criarLoop("fadin tutorial", 1, 375, 390, "animacoes/sessao_1/fundo1.png", false, false);
  tutorial.criarLoop("tutorial estatico", 2, 391, 392, "animacoes/sessao_1/fundo1.png", true, false);
  tutorial.criarLoop("fadout tutorial", 3, 393, 400, "animacoes/sessao_1/fundo1.png", false, false);
  tutorial.addBotao(2, "b1", 0, 60, 360, 540, false, true);//bolaum -> fadinloop1
  tutorial.addHome(2, "voltar", 0, 0, 50, 60, true, 4, 0);//menu 1 -> fadin menu
  tutorial.addHome(2, "vai logo", 80, 0, 100, 60, true, 12, 0);//faq 1 -> volta menu
  
  bolaum.importIm();
  bolaum.criarLoop("fadinloop1", 1, 1, 101, "animacoes/sessao_1/fundo1.png", true, false); 
  bolaum.addBotao(1, "b1", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop2", 2, 102, 214, "animacoes/sessao_1/fundo1.png", true, false);  
  bolaum.addBotao(2, "b2", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop3", 3, 215, 321, "animacoes/sessao_1/fundo1.png", true, false);
  bolaum.addBotao(3, "b3", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop4", 4, 322, 424, "animacoes/sessao_1/fundo1.png", true, false);
  bolaum.addBotao(4, "b4", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop5", 5, 425, 530, "animacoes/sessao_1/fundo1.png", true, false);  
  bolaum.addBotao(5, "b5", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop6", 6, 531, 635, "animacoes/sessao_1/fundo1.png", true, false); 
  bolaum.addBotao(6, "b6", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop7", 7, 636, 743, "animacoes/sessao_1/fundo1.png", true, false);
  bolaum.addBotao(7, "b7", 0, 60, 360, 540, false, true);
 
  bolaum.criarLoop("fadinloop8", 8, 744, 847, "animacoes/sessao_1/fundo1.png", true, false);
  bolaum.addBotao(8, "b8", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop9", 9, 848, 950, "animacoes/sessao_1/fundo1.png", true, false);  
  bolaum.addBotao(9, "b9", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop10", 10, 951, 1055, "animacoes/sessao_1/fundo1.png", true, false);
  bolaum.addBotao(10, "b10", 0, 60, 360, 540, false, true);
 
  bolaum.criarLoop("fadinloop11", 11, 1056, 1160, "animacoes/sessao_1/fundo1.png", true, false);
  bolaum.addBotao(11, "b11", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop12", 12, 1161, 1268, "animacoes/sessao_1/fundo1.png", true, false);
  bolaum.addBotao(12, "b12", 0, 60, 360, 540, false, true);
  
  bolaum.criarLoop("fadinloop13", 13, 1269, 1396, "animacoes/sessao_1/fundo1.png", true, false); 
  bolaum.criarLoop("fadout13", 14, 1388, 1396, "animacoes/sessao_1/fundo1.png", false, false); 
  bolaum.addHome(13, "desbloquear faq", 0, 60, 360, 540, true ,12, 0);//faq 1 -> termino balao 1 volta pro menu
  
  for (int i = 1; i < 14; i++) {
    bolaum.addHome(i, "voltar", 0, 0, 50, 60, true, 4, 0);//menu 1 -> fadin menu
    bolaum.addHome(i, "vai logo", 80, 0, 100, 60, true, 12, 0);//faq 1 -> termino balao 1 volta pro menu
  }
 //-----------------------------------------------------------------------------------------------------------------
 
 faqvoltabolaum.importIm();
 faqvoltabolaum.criarLoop("volta > menu ", 1, 400, 414, "animacoes/inicio/fundo.png", false, false);
 
 faqdesbloq.importIm();
 faqdesbloq.criarLoop("faq desbloqueando", 1, 415, 444, "animacoes/inicio/fundo.png", false, false);
 faqdesbloq.addHome(1, "suic\u00eddio?", 43, 90, 135, 180, true, 9, 0);//indobolaum -> saida menu
 faqdesbloq.addHome(1, "saibamais", 150, 430, 230, 520, true, 12, 1);//faq -> faq click
 
 faqestatico.importIm();
 faqestatico.criarLoop("faq estatico", 1, 445, 446, "animacoes/inicio/fundo.png", true, false);
 faqestatico.addHome(1, "suic\u00eddio?", 43, 90, 135, 180, true, 9, 0);//indobolaum -> saida menu
 faqestatico.addHome(1, "saibamais", 150, 430, 230, 520, true, 15, 0);//faq -> faq click
 
 faqclicado.importIm();
 faqclicado.criarLoop("faq clicado", 1, 448, 452, "animacoes/inicio/fundo.png", false, false);
   
 
 //---------------------------------------------------------------------------------------------------------------- 

  smfadin.importIm();
  smfadin.criarLoop("fadout menu", 1, 453, 459, "animacoes/inicio/fundo.png", false, false);
  smfadin.criarLoop("fadin saibamais", 2, 460, 468, "animacoes/inicio/fundo.png", true, false);
  smfadin.addHome(2, "voltar", 0, 0, 50, 60, true, 17, 0);//menu 1 -> fadin menu

  smfadout.importIm();
  smfadout.criarLoop("voltar clicado", 1, 470, 479, "animacoes/inicio/fundo.png", false, false);
  smfadout.criarLoop("fadout saibamais", 2, 480, 487, "animacoes/inicio/fundo.png", false, false);
  smfadout.criarLoop("fadin menu", 3, 488, 494, "animacoes/inicio/fundo.png", true, false);
  smfadout.addHome(3, "suic\u00eddio?", 43, 90, 135, 180, true, 9, 0);//indobolaum -> saida menu
  smfadout.addHome(3, "saibamais", 150, 430, 230, 520, true, 15, 0);//faq -> faq click
  
 //---------------------------------------------------------------------------------------------------------------- 

  prog.addAni(sights);//0
  prog.addAni(dote);//1
  prog.addAni(aurora);//2
  
  prog.addAni(menucomeco);//3
  prog.addAni(menufadin);//4
  prog.addAni(menuestatico);//5
  prog.addAni(infoclicado);//6
  prog.addAni(suicidioclicado);//7
  prog.addAni(menufadout);//8
  
  prog.addAni(indobolaum);//9
  prog.addAni(tutorial);//10
  prog.addAni(bolaum);//11
  
  prog.addAni(faqvoltabolaum);//12
  prog.addAni(faqdesbloq);//13
  prog.addAni(faqestatico);//14
  prog.addAni(faqclicado);//15
  
  prog.addAni(smfadin);//16
  prog.addAni(smfadout);//17
  //prog.addAni(smvoltasaibamais);//17
  //prog.addAni(smfadout);//18
  
  
  
}

public void draw() {
  prog.executar();
}

public void mouseClicked() {
  if (mouseButton == LEFT) {
    click = true;
    mousex = mouseX;
    mousey = mouseY;
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "aba1_botao" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
