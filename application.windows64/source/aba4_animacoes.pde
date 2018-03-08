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
  }//função pra add animação
//991657881
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  //---------------------------------------------- A ________ É AQUI \/ ----------------------------------------------------------------------------------------------------------------------------------------------
  //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  public void executar() {
    //▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼IMPRESSÃO▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
    //PARTE 1: EFEITO ANIMAÇÃO I
      image(animacoes.get(aniAtual).loops.get(loopAtual).getFundo(), 0, 0);//imprime o fundo
      image(animacoes.get(aniAtual).frames[animacoes.get(aniAtual).frameAtual], 0, 0);//imprime o gif com transparencia 
      //imprime ai vai
      println("  imprimiu" + animacoes.get(aniAtual).frameAtual);
    //PARTE 1: EFEITO ANIMAÇÃO I
    
    //PARTE 2: HABILITA OS BOTÕES
      if (animacoes.get(aniAtual).loops.get(loopAtual).temBotao) {
      //  puxa a variavel temBotal do loop atual da animação atual
        for (int i = 0; i < animacoes.get(aniAtual).loops.get(loopAtual).botoes.size(); i++) {
          //   começa igual| enquanto i < a qtd de botoes do loop atua da animaçcao atual| incrementa i 
          //   a zero      |                                                             | 
          //
          animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(i).on();
          //dispoe o dito botão do loop atual da animação atual
        }//FIM DO FOR "DISPÕE OS BOTOES"
      }// FIM DO SE O "LOOP TIVER BOTOES, DISPONHA-OS NA TELA"
    //PARTE 2: HABILITA OS BOTÕES

    //PARTE 3: EFEITO ANIMAÇÃO II
      if (animacoes.get(aniAtual).frameAtual < animacoes.get(aniAtual).loops.get(loopAtual).ff) {
        //puxa o frame atual da animação   | < |puxa o ultimo frame do loop atual da animação atual
        //atual                            |   |
        //
        animacoes.get(aniAtual).frameAtual++;//incrementa o frame atual da animação atual
        //
        print("incrementou para " + animacoes.get(aniAtual).frameAtual);
      }//FIM DO SE "AINDA EXISTEM FRAMES PARA EXECUTAR NO LOOP, INCREMENTE"    
    //PARTE 3: EFEITO ANIMAÇÃO II
    //▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

    //▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬


    //▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼VERIFICAÇÃO▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
    //PARTE 4: VERIFICAÇÃO
      if (animacoes.get(aniAtual).frameAtual == animacoes.get(aniAtual).loops.get(loopAtual).ff) {
        //  puxa o frame atua da animacao atual == puxa o ultimo frame do loop atual da animação atual
        //
        println("chegou no fim da linha ");
        //SE JÁ CHEGOU AO FIM DO LOOP

        if (loopAtual < animacoes.get(aniAtual).qtdLoops) {
        //  puxa  o  | < |puxa a quantidade de loops da 
        //  loopAtual|   |animaçao atual
        //  
        //E SE AINDA TEM LOOPS PENDENTES

        //••••••••••••••••••••••••••••••••••••••••••••• CASO 1 (SE FOR UM LOOP ESTÁTICO E AUTOMÁTICO) •••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••
          if (animacoes.get(aniAtual).loops.get(loopAtual).statico & animacoes.get(aniAtual).loops.get(loopAtual).auto) {           
          //  puxa a variavel statico do loop atual da animacao  | & |puxa a variavel auto do loop atual da animacao 
          //  atua                                               |   |atual
          //
          println("caso 1"); 
          //SE FOR UM LOOP ESTATICO E AUTOMATICO

          //░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ESSE CODIGO SE REPETE, CUIDADO PRA NÃO BUGAR YOUR MIND░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
          if (click) {
            println("cliquei " + mousex + " " + mousey + "/" + animacoes.get(aniAtual).loops.get(loopAtual).botoes.size());
            //SE HOUVE CLIQUE

            if (animacoes.get(aniAtual).loops.get(loopAtual).temBotao) {
              for (int b = 1; b < animacoes.get(aniAtual).loops.get(loopAtual).botoes.size() + 1; b++) {   
                //   b começa | enquanto b < a quantidade de botões do loop atual da animaçao | incremente b
                //   igua a 0 | atual (se não tiver o +1 os botões de avançar não funcionam)   
                println("tem botao aqui" + b);
                //VERIFIQUE SE HÁ BOTÕES NO LOOP

                if (mousex > animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).x & mousex < animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).xf &
                  mousey > animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).y & mousey < animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).yf) {
                  //  se mousex e mousey estiverem dentro da area de um dos botoes.
                  //  mousex e mousey =  valor de x e y no momento do clique.
                  //  b-1 é usado pra compensar a diferença do indice do array para o tamanho do array
                  println("cliquei no " + animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).nome);
                  println("q" + animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).home);
                  // SE O CLIQUE FOR DENTRO DE ALGUM BOTÃO

                  //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
                  if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).home) {
                    int an = animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).an;
                    int lo = animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).lo;
                    aniAtual = an;//defina a animação da home
                    loopAtual = lo;//defina o loop da home
                    animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).inicio;
                    animacoes.get(aniAtual).loops.get(loopAtual);//execute-o
                    click = false;//não esquecer de atualizar o status do clique                       
                    println("loop incrementado e rodando");
                    break;//feito
                  } else if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).next) {
                    //puxa o booleano next do loop anterior
                    //
                    //SE O BOTÃO FOR DE AVANÇAR

                    if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) <= animacoes.get(aniAtual).loops.size()) {
                      //   puxa o numero do loop atual da animação atual + 1  |<= |puxa aquantidade de looops na animacao atual
                      //
                      println("restam loops");
                      //SE EXISTIR MAIS LOOPS DEPOIS DESSE (NA ANIMAÇÃO ATUAL)                     

                      for (int i = loopAtual; i < animacoes.get(aniAtual).loops.size(); i++) {              
                        //   i começa igual ao| enquanto i < a quantidade de loops na   | incremente i
                        //   valor d'loopAtual| animação atual                       
                        println("tamanho do loop: " + animacoes.get(aniAtual).loops.size());
                        println("i = " + i);
                        println("loopAtual = " + loopAtual);
                        //VASCULHE ESSE ARRAY

                        if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) & i < animacoes.get(aniAtual).loops.size()) {
                          //  puxa o numero do proximo looop da animação| == |puxa o numero do loop da animação atua + 1             | & | i | < |a quantidade total de loops na
                          //  atual                                     |    |                                                       |     v     | animação atual
                          //                                                                                                               v 
                          //                                                                                                         (proximo loop)
                          //SE FOR ACHADO O PROXIMO LOOP

                          loopAtual++;//vá para o próximo loop
                          animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).inicio;//define o frame atual como frame inicial do proximo loop
                          animacoes.get(aniAtual).loops.get(i);//e execute-o
                          click = false;//não esquecer de atualizar o status do clique                       
                          println("loop incrementado e rodando");
                          break;//feito
                        }//FIM DO SE "FOR ACHADO O PROXIMO LOOP"
                      }//FIM DO FOR "VASCULHE ESSE ARRAY"
                    }//FIM DO SE "EXISTIR MAIS LOOPS DEPOIS DESSE"
                    else if (aniAtual < qtdAni - 1) {
                      //      puxa  a  |<|puxa a       |- 1
                      //      animaçao | |quantidade   |
                      //      atual    | |de animações |
                      //
                      //SENÃO TIVER MAIS LOOP NA ANIMAÇÃO ATUAL E SE AINDA TIVER ANIMAÇÃO

                      aniAtual++;//vá para a próxima animação
                      loopAtual = 0;//e começe pelo primeiro loop
                      println("próxima animação irru");
                    }//FIM DO SENÃO "TIVER MAIS LOOP NA ANIMAÇÃO ATUAL", E SE "AINDA TIVER ANIMAÇÃO"
                  }//FIM DO IF(SE O BOTÃO FOR DE AVANÇAR)

                  //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘

                  else if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).back) {
                    //SE O BOTÃO FOR DE VOLTAR
                    if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() - 1) >= animacoes.get(0).loops.get(0).getN()) {
                      //   puxa o numero do loop atual da animação atual + 1  |<= |puxa o numero do primeiro loop da primeira animação
                      //
                      println("existem loops anteriores" + loopAtual);
                      //SE EXISTIR LOOPS ANTERIORES A ESSE                      

                      for (int i = loopAtual; i > -1; i--) {              
                        //   i começa igual ao| enquanto i > a o primeiro loop da     | decremente i
                        //   valor d'loopAtual| primeira animacao                       
                        //
                        //VASCULHE ESSE ARRAY                    
                        println("i = " + i);
                        println("loopAtual = " + loopAtual);

                        if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() - 1)) {
                          //  puxa o numero do ultimo looop da         | == |puxa o numero do loop atual da animação atual - 1   
                          //  animação atual                           |    |                                                                                                                                                                   
                          //                                                                                                     
                          //SE FOR ACHADO O LOOP ANTERIOR

                          loopAtual--; 
                          //vá para o loop anterior
                          animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).inicio;//defina o frameAtual para o frame inicial do 
                          animacoes.get(aniAtual).loops.get(i);//execute-o
                          click = false;//não esqueça de att o status de clique
                          println("loop decrementado e rodando");
                          break;//feito
                        }//FIM DO SE "FOR ACHADO O LOOP ANTERIOR"
                      }//FIM DO FOR "VASCULHE ESSE ARRAY"
                    }//FIM DO SE "EXISTIR LOOPS ANTERIORES A ESSE"
                    else if (aniAtual > 0) {
                      //      puxa  a  |<|puxa a       |- 1
                      //      animaçao | |quantidade   |
                      //      atual    | |de animações |
                      //
                      //SE NÃO EXISTIR LOOPS ANTERIORES, E SE TIVER UMA ANIMAÇÃO ANTERIOR

                      aniAtual--;//vá para a animação anterior
                      loopAtual = animacoes.get(aniAtual).qtdLoops;//e começe pelo ultimo loop
                      println("animação anterior irru " + aniAtual);
                    }//FIM DO SE NÃO "EXISTIR LOOPS ANTERIORES" , E SE "TIVER UMA ANIMAÇÃO ANTERIOR"
                  }//FIM DO SE O "BOTÃO FOR DE VOLTAR"

                  //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
                }//FIM DO SE O "CLIQUE FOI DENTRO DE UM BOTÃO" 
                //░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░                
                else {
                  //SE O CLIQUE NÃO FOI DENTRO DE UM BOTAO CONTINUE A RODAR O LOOP
                  //
                  animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).inicio;
                  println("não clicaram em um botão");
                  //puxa o frame atual da animação  |=|puxa o frame inicial do loop atual da animação atual
                  //atual
                }//FIM DO SE O "CLIQUE NÃO FOI DENTRO DE UM BOTAO CONTINUE A RODAR O LOOP"
              }//FIM FO FOR "VERIFIQUE SE HÁ BOTOES NO LOOP"
            }//FIM DO SE "TEM BOTÃO"
          }//FIM DO SE  HOUVE CLIQUE
           else {
            //SE NÃO HOUVE CLIQUE REPITA O LOOP
            //
            animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).inicio;
            //puxa o frame atual da animação  |=|puxa o frame inicial do loop atual da animação atual
            //atual
            println("não clicaram em um botão");                 
           }//FIM DO SE NÃO "HOUVE CLIQUE REPITA O LOOP"
        }//•••••••••••••••••••••••••••••••••••••••••••••• FIM DO CASO 1 (SE FOR UM LOOP ESTÁTICO E AUTOMÁTICO) •••••••••••••••••••••••••••••••••••••••••••••••••••••••••••        

        //•••••••••••••••••••••••••••••••••••••••••••••• CASO 2 (SE FOR UM LOOP ESTÁTICO E NÃO FOR AUTOMÁTICO) •••••••••••••••••••••••••••••••••••••••••••••••••••••••••••
        else if (animacoes.get(aniAtual).loops.get(loopAtual).statico & animacoes.get(aniAtual).loops.get(loopAtual).auto == false) {
          //     puxa a variavel statico do loop atual da animacao  | & |puxa a variavel auto do loop atual da animacao atual
          //     atual                                              |   
          //SE FOR UM LOOP ESTÁTICO E NÃO AUTOMÁTICO
          println("caso 2");

          //░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ESSE CODIGO SE REPETE, CUIDADO PRA NÃO BUGAR YOUR MIND░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
          if (click) {
            println("cliquei " + mousex + " " + mousey + "/" + animacoes.get(aniAtual).loops.get(loopAtual).botoes.size());
            //SE HOUVE CLIQUE 

            if (animacoes.get(aniAtual).loops.get(loopAtual).temBotao) {
              for (int b = 1; b < animacoes.get(aniAtual).loops.get(loopAtual).botoes.size() + 1; b++) {   
                //   b começa | enquanto b < a quantidade de botões do loop atual da animaçao | incremente b
                //   igua a 0 | atual (se não tiver o +1 os botões de avançar não funcionam)   
                println("tem botao aqui" + b);
                //VERIFIQUE SE HÁ BOTÕES NO LOOP

                if (mousex > animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).x & mousex < animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).xf &
                  mousey > animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).y & mousey < animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).yf) {
                  //  se mousex e mousey estiverem dentro da area de um dos botoes.
                  //  mousex e mousey =  valor de x e y no momento do clique.
                  //  b-1 é usado pra compensar a diferença do indice do array para o tamanho do array
                  println("cliquei no " + animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).nome);
                  // SE O CLIQUE FOR DENTRO DE ALGUM BOTÃO

                  //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
                  if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).home) {
                    int an = animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).an;
                    int lo = animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).lo;
                    aniAtual = an;//defina a animação da home
                    loopAtual = lo;//defina o loop da home
                    animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).inicio;
                    animacoes.get(aniAtual).loops.get(loopAtual);
                    click = false;//não esquecer de atualizar o status do clique                       
                    println("home ativado");
                    break;//feito
                  } //
                  else if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).next) {
                    //puxa o booleano next do loop anterior
                    //
                    //SE O BOTÃO FOR DE AVANÇAR

                    if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) <= animacoes.get(aniAtual).loops.size()) {
                      //   puxa o numero do loop atual da animação atual + 1  |<= |puxa aquantidade de looops na animacao atual
                      //
                      println("restam loops");
                      //SE EXISTIR MAIS LOOPS DEPOIS DESSE (NA ANIMAÇÃO ATUAL)                     

                      for (int i = loopAtual; i < animacoes.get(aniAtual).loops.size(); i++) {              
                        //   i começa igual ao| enquanto i < a quantidade de loops na   | incremente i
                        //   valor d'loopAtual| animação atual                       
                        println("tamanho do loop: " + animacoes.get(aniAtual).loops.size());
                        println("i = " + i);
                        println("loopAtual = " + loopAtual);
                        //VASCULHE ESSE ARRAY

                        if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) & i < animacoes.get(aniAtual).loops.size()) {
                          //  puxa o numero do proximo looop da animação| == |puxa o numero do loop da animação atua + 1             | & | i | < |a quantidade total de loops na
                          //  atual                                     |    |                                                       |     v     | animação atual
                          //                                                                                                               v 
                          //                                                                                                         (proximo loop)
                          //SE FOR ACHADO O PROXIMO LOOP

                          loopAtual++;//vá para o próximo loop
                          animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).fi;//define o frame atual como frame inicial do proximo loop
                          animacoes.get(aniAtual).loops.get(i);//e execute-o
                          click = false;//não esquecer de atualizar o status do clique                       
                          println("loop incrementado e rodando");
                          break;//feito
                        }//FIM DO SE "FOR ACHADO O PROXIMO LOOP"
                      }//FIM DO FOR "VASCULHE ESSE ARRAY"
                    }//FIM DO SE "EXISTIR MAIS LOOPS DEPOIS DESSE"
                    else if (aniAtual < qtdAni - 1) {
                      //      puxa  a  |<|puxa a       |- 1
                      //      animaçao | |quantidade   |
                      //      atual    | |de animações |
                      //
                      //SENÃO TIVER MAIS LOOP NA ANIMAÇÃO ATUAL E SE AINDA TIVER ANIMAÇÃO

                      aniAtual++;
                      //vá para a próxima animação
                      loopAtual = 0;              
                      //e começe pelo primeiro loop
                      println("próxima animação irru");
                    }//FIM DO SENÃO "TIVER MAIS LOOP NA ANIMAÇÃO ATUAL", E SE "AINDA TIVER ANIMAÇÃO"
                  }//FIM DO IF(SE O BOTÃO FOR DE AVANÇAR)

                  //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘

                  else if (animacoes.get(aniAtual).loops.get(loopAtual).botoes.get(b-1).back) {
                    //SE O BOTÃO FOR DE VOLTAR
                    if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() - 1) >= animacoes.get(0).loops.get(0).getN()) {
                      //   puxa o numero do loop atual da animação atual + 1  |<= |puxa o numero do primeiro loop da primeira animação
                      //
                      println("existem loops anteriores" + loopAtual);
                      //SE EXISTIR LOOPS ANTERIORES A ESSE                      

                      for (int i = loopAtual; i > -1; i--) {              
                        //   i começa igual ao| enquanto i > a o primeiro loop da     | decremente i
                        //   valor d'loopAtual| primeira animacao                       
                        //
                        //VASCULHE ESSE ARRAY                    
                        println("i = " + i);
                        println("loopAtual = " + loopAtual);

                        if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() - 1)) {
                          //  puxa o numero do ultimo looop da         | == |puxa o numero do loop atual da animação atual - 1   
                          //  animação atual                           |    |                                                                                                                                                                   
                          //                                                                                                     
                          //SE FOR ACHADO O LOOP ANTERIOR

                          loopAtual--; 
                          //vá para o loop anterior
                          animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(i).inicio;//defina o frameAtual para o frame inicial do 
                          animacoes.get(aniAtual).loops.get(i);//execute-o
                          click = false;//não esqueça de att o status de clique
                          println("loop decrementado e rodando");
                          break;//feito
                        }//FIM DO SE "FOR ACHADO O LOOP ANTERIOR"
                      }//FIM DO FOR "VASCULHE ESSE ARRAY"
                    }//FIM DO SE "EXISTIR LOOPS ANTERIORES A ESSE"
                    else if (aniAtual > 0) {
                      //      puxa  a  |<|puxa a       |- 1
                      //      animaçao | |quantidade   |
                      //      atual    | |de animações |
                      //
                      //SE NÃO EXISTIR LOOPS ANTERIORES, E SE TIVER UMA ANIMAÇÃO ANTERIOR

                      aniAtual--;//vá para a animação anterior
                      loopAtual = animacoes.get(aniAtual).qtdLoops;//e começe pelo ultimo loop
                      println("animação anterior irru " + aniAtual);
                    }//FIM DO SE NÃO "EXISTIR LOOPS ANTERIORES" , E SE "TIVER UMA ANIMAÇÃO ANTERIOR"
                  }//FIM DO SE O "BOTÃO FOR DE VOLTAR"

                  //◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
                }//FIM DO SE O "CLIQUE FOI DENTRO DE UM BOTÃO" 
                //░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░                               
              }//FIM FO FOR "VERIFIQUE SE HÁ BOTOES NO LOOP"
            }//FIM DO SE "TEM BOTÃO"
          }//FIM DO SE "HOUVE CLIQUE"
        }//•••••••••••••••••••••••••••••••••••••••••••••• FIM DO CASO 2 (SE FOR UM LOOP ESTÁTICO E NÃO FOR AUTOMÁTICO) •••••••••••••••••••••••••••••••••••••••••••••••••••••••

        //••••••••••••••••••••••••••••••••••••••••••••••••••• CASO 3 (SE NÃO FOR UM LOOP ESTÁTICO E NEM AUTOMÁTICO) •••••••••••••••••••••••••••••••••••••••••••••••••••••••••• 
        else if (animacoes.get(aniAtual).loops.get(loopAtual).statico == false & animacoes.get(aniAtual).loops.get(loopAtual).auto == false) {
          //se não for um loop estático e nem automáfico
          println("caso 3");

          //░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ESSE CODIGO SE REPETE, CUIDADO PRA NÃO BUGAR YOUR MIND░░░░░░░░░░ OLD VERSION ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
          if ((animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) <= animacoes.get(aniAtual).loops.size()) {
            //   puxa o numero do loop atual da animação atual + 1  |<= |puxa aquantidade de looops na animacao atual
            //
            println("restam loops");
            //SE EXISTIR MAIS LOOPS NO ARRAY DA ANIMAÇÃO DEPOIS DESSE

            for (int i = loopAtual; i < animacoes.get(aniAtual).loops.size(); i++) {              
              //   i começa igual ao| enquanto i < a quantidade de loops na   | incremente i
              //   valor d'loopAtual| animação atual                       
              //
              //VASCULHE ESSE ARRAY
              println("qtd loops: " + animacoes.get(aniAtual).loops.size());
              println("i = " + i);
              println("loopAtual = " + loopAtual);

              if (animacoes.get(aniAtual).loops.get(i).getN() == (animacoes.get(aniAtual).loops.get(loopAtual).getN() + 1) & i < animacoes.get(aniAtual).loops.size()) {
                //  puxa o numero do proximo looop da animação| == |puxa o numero do loop da animação atua + 1             | & | i | < |a quantidade total de loops na
                //  atual                                     |    |                                                       |     v     | animação atual
                //                                                                                                               v 
                //                                                                                                         (proximo loop)
                //SE FOR ACHADO O PROXIMO LOOP

                loopAtual++; 
                //vá para o próximo loop
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
            //      animaçao | |quantidade   |
            //      atual    | |de animações |
            //
            //SE NÃO TIVER MAIS LOOP NA ANIMAÇÃO ATUAL E SE AINDA TIVER ANIMAÇÃO

            aniAtual++;
            //vá para a próxima animação
            loopAtual = 0;
            animacoes.get(aniAtual).frameAtual = animacoes.get(aniAtual).loops.get(loopAtual).fi;
            println("a" + animacoes.get(aniAtual).frameAtual);
            //e começe pelo primeiro loop
            println("próxima animação irru");
          }
          //░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
        }//•••••••••••••••••••••••••••••••••••••••••••••• FIM DO CASO 3 (SE NÃO FOR UM LOOP ESTÁTICO E NEM AUTOMÁTICO) •••••••••••••••••••••••••••••••••••••••••••••••••••••••
      }//FIM DO SE "AINDA RESTAM LOOPS
    }//▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲FIM DO SE 'JA CHEGOU AO FIM DO LOOP" --- VERIFICACAO ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
     //PARTE 4: VERIFICAÇÃO
  }///FIM DO EXECUTAR
}//FIM DA CLASSE ANIMACOES