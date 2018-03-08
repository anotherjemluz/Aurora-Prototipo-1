//14
//NAO IDENTE ESSE CODIGO (CTRL + T) SE QUISER ENTENDER OS COMENTARIOS
//DESENVOLVIDO POR 5IGHTS

//NOTAS ALEATORIAS PARA MARCAÇÃO
//1☺ 2☻ 3♥ 4♦ 5♣ 6♠ 7• 8◘ 9○ 10◙ 11♂ 12♀ 13♪ 14♫ 15☼ 16► 17◄ 18↕ 19‼ 20¶ 21§ 22▬ 23↨ 24↑ 25↓ 26→ 27← 28∟ 29↔ 30▲ 31▼ 
//180┤ 179│ 178▓ 177▒ 176░ ╣ © 

void settings() {
  size(360, 540);
}//------------------------------------- tamanho da tela ----------------------------------------------------------

//-------------------------------------- local de instancialização ------------------------------------------------
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

void setup() {
  frameRate(25);
  background(220);

  //objeto.criarLoop("nome do loop", numero do loop, frameInicial, frameFinal, fundo, statico, auto);
  //objeto.addBotao(loop do botao, nome, x, y, xf, yf, voltar, avançar);

  sights.importIm(); 
  
  sights.criarLoop("5ights", 1, 0, 100, "animacoes/inicio/f5ights.png", false, false);

  dote.importIm(); 
  dote.criarLoop("dote", 1, 101, 176, "animacoes/inicio/fundote.png", false, false);

  aurora.importIm();  
  aurora.criarLoop("aurora", 1, 177, 284, "animacoes/inicio/fundo.png", false, false);
  aurora.criarLoop("aurora ft.começar", 2, 284, 326, "animacoes/inicio/fundo.png", true, true);
  //aurora.criarLoop("comecar_clicado", 3, 251, 274, "animacoes/inicio/fundo.png", false, false);  
  aurora.addHome(2, "começar", 124, 465, 256, 492, true, 3, 0);//menu 1  -> fadin menu
  
  // ---------------------------------------------------------------------------------------------------
  
  menucomeco.importIm();
  menucomeco.criarLoop("fadin aurora ft.começar > menu", 1, 327, 339, "animacoes/inicio/fundo.png", false, false);
  
  menufadin.importIm();
  menufadin.criarLoop("fadin menu", 1, 340, 354, "animacoes/inicio/fundo.png", false, false);
  
  menuestatico.importIm();
  menuestatico.criarLoop("menu estatico", 1, 355, 356, "animacoes/inicio/fundo.png", true, false);
  menuestatico.addHome(1, "suicídio?", 43, 90, 135, 180, true, 9, 0);//fadeb 1 -> saida menu
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
  
  bolaum.criarLoop("fadinloop13", 13, 1269, 1387, "animacoes/sessao_1/fundo1.png", true, false); 
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
 faqdesbloq.addHome(1, "suicídio?", 43, 90, 135, 180, true, 9, 0);//indobolaum -> saida menu
 faqdesbloq.addHome(1, "saibamais", 150, 430, 230, 520, true, 12, 1);//faq -> faq click
 
 faqestatico.importIm();
 faqestatico.criarLoop("faq estatico", 1, 445, 446, "animacoes/inicio/fundo.png", true, false);
 faqestatico.addHome(1, "suicídio?", 43, 90, 135, 180, true, 9, 0);//indobolaum -> saida menu
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
  smfadout.addHome(3, "suicídio?", 43, 90, 135, 180, true, 9, 0);//indobolaum -> saida menu
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

void draw() {
  prog.executar();
}

public void mouseClicked() {
  if (mouseButton == LEFT) {
    click = true;
    mousex = mouseX;
    mousey = mouseY;
  }
}