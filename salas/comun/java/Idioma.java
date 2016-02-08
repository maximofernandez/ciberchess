
public class Idioma
{
	
int idiomaUsado; // 0-español  1-inglés

String frases[][]=
{
{"Rendirse","Surrender"},
{"Proponer tablas","Proposing draw"},
{"Figs. comidas","Captured pieces"},
{"Cerrar","Quit"},
{"SISTEMA","SYSTEM"},
{"Movimiento no válido.","No legal move."},
{"No se puede enrocar, ya ha movido el rey.","Unable castling, the king was moved."},
{"No se puede enrocar, ya ha movido la torre.","Unable castling, the rook was moved."},
{"No se puede enrocar con figuras en medio.","Unable castling with pieces in the middle."},
{"No puede enrocar con el rey en jaque o pasando por una posición amenazada.","Unable castling when check or threatened square in the king's way."},
{"Solo el caballo puede saltar otras figuras.","Only the knight can jump other pieces."},
{"Enhorabuena, has ganado porque el rival se ha desconectado.","Congratulations, you won because the opponent has been disconnected."},
{"Gana","Win"},
{"por desconexión de","by disconnection of"},
{"La partida termina en tablas por finalización del tiempo y al rival solo le queda el rey.","Draw, time completed and the opponent has only the king."},
{"Pactan TABLAS","Draw, mutual agreement"},
{"Lo siento, has perdido por finalización del tiempo.","Sorry, you lose by completion time."},
{"por finalizar el tiempo","by completion time"},
{"Lo siento, tu pierdes.","Sorry, you lose."},
{"por rendirse","by surrender"},
{"Ya has propuesto tablas antes.","You propose draw before."},
{"Enviada proposición de tablas.","Draw proposal sent."},
{"La partida termina en tablas.","The game ends in draw."},
{"Enviada notificación al rival de DENEGACIÓN de tablas.","Message sent for REFUSING draw."},
{"Tu ganas, al rival se le acabó el tiempo.","You win, the opponent has finished his time."},
{"Tu ganas, el rival se rinde.","You win, the opponent surrenders."},
{"El rival ACEPTA tablas.","The opponent ACCEPT draw."},
{"Por tanto, la partida termina en tablas.","Therefore, the game ends in a draw."},
{"El rival NO acepta tablas.","The opponent NOT accept draw."},
{"JAQUE MATE, lo siento has perdido.","CHECKMATE, sorry you lose."},
{"Tablas por AHOGADO.","Draw by stalemate."},
{"Solo se permite una reconexión en cada partida.","Only a reconnection is allowed in each game."},
{"Lo siento, has perdido por superar 1 minuto de desconexión.","Sorry, you have lost by disconnection overcome 1 minute."},
{"Tablas, solo quedan los dos reyes.","Draw, only are the two kings."},
{"Tablas, tres movimientos iguales.","Draw, three moves equal."},
{"El rival se ha autodesconectado con lo cual ganas la partida.","Congratulations, you won the game by disconnect the opponent."},
{"Tablas, solo te queda el rey y al rival se le acabó el tiempo.","Draw, you have only the king and the opponent finished his time."},
{"Enhorabuena, JAQUE MATE.","Congratulations, CHECKMATE."},
{"por MATE","because of CHECKMATE."},
{"Tablas, has AHOGADO al rival.","Draw, stalemate."},
{"AHOGA a","Stalemate"},
{"Jugar con invitados no modifica la puntuación.","Playing with guests does not change the score."},
{"Partidas con cuatro movimientos o menos no puntuan.","Games with four moves or less not change the score."},
{"puntos.","points."},
{"Automático","Automatic"},
{"Detener","Stop"},
{"Ayuda","Help"},
{"Ver Blancas","White side"},
{"Ver Negras","Black side"},
{"Descargar Partidas","Download Games"},
{"Selecciona una partida para descargar","Select a chess game to download"},
{"Solo se pueden descargar partidas terminadas","Only finished games can be dowload"},
{"No se pueden seleccionar más de 100 partidas","Unable to select more than 100 chess games"},
{"Error al buscar fichero para descargar la partida.","Failed to search file to download the game."},
{"Selecciona una partida para ver","Select a game to see"},
{"ACTIVA","ACTIVE"},
{"TABLAS","DRAW"},
{"ABANDONADA","ABANDONED"},
{"GANADOR","WINNER"},
{"Fecha","Date"},
{"Blancas","Whites"},
{"Negras","Blacks"},
{"Movs","Moves"},
{"Ganador","Winner"},
{"Cargando configuración.","Loading configuration."},
{"Cargando web inicial.","Loading initial site."},
{"Cargando imagen arte.","Loading art image."},
{"Cargando colores.","Loading colors."},
{"Cargando piezas.","Loading pieces."},
{"Cargando logotipo.","Loading logo."},
{"Cargando banner.","Loading banner."},
{"Cargando icono.","Loading icon."},
{"Cargando banderas.","Loading flags."},
{"Cargando imagenes.","Loading images."},
{"Error cargando imagenes","Failed loading images."},
{"Recortando piezas.","Cutting pieces."},
{"Recortando banderas.","Cutting flags."},
{"Poner icono.","Put icon."},
{"Crear objetos.","Create objects."},
{"Aceptar","Accept"},
{"Cancelar","Cancel"},
{"Crear pestañas.","Create tabs."},
{"Crear menú.","Create menu."},
{"Carga del programa completa.","Program loaded."},
{"Bienvenido a www.CiberChess.com","Wellcome to www.CiberChess.com"},
{"Este chat es público para todos los jugadores.","This chat is public for everybody."},
{"La hora que muestra el reloj es GMT+1 sincronizada con el servidor.","The clock shows the time in GMT +1 synchronized with the server."},
{"Usuario","User"},
{"Configurar sala de juego","Setting game room"},
{"Configuración","Settings"},
{"Un INVITADO no tiene configuración","A GUEST does not have settings"},
{"Selección de colores","Choosing colors"},
{"Error al abrir URL:","Failed to open URL:"},
{"Admitir Retos","Accepting Challenges"},
{"Salir","Exit"},
{"Ver","See"},
{"Ver Partidas y ELO","See Games and ELO"},
{"Partidas","Games"},
{"Ver Partidas","See Games"},
{"Correo","Mail"},
{"Envía correo electrónico","Send e-mail"},
{"Administrador","Administrator"},
{"Amigo","Friend"},
{"Ayuda y Acerca de","Help and About"},
{"Acerca de","About"},
{"No te puedes retar a ti mismo","You can not challenge yourself"},
{"Selecciona un rival a quien retar de la lista","Select an opponent from the list to challenge"},
{"Confirmas retar a","Confirm challenge"},
{"con incremento","with increase"},
{"seg","sec"},
{"sin incremento","without increase"},
{"blancas","whites"},
{"negras","blacks"},
{"te reta a una partida de","challenges you to a game of"},
{"jugando tu con","playing you with"},
{"intentó retarte","tried to challenge you"},
{"no acepta el reto","do not accept challenge"},
{"No se puede crear partida","Can not to create game"},
{"ADMINISTRADOR","ADMINISTRATOR"},
{"Se ha cortado la conexión.","Without connection."},
{"Confirmas rendirte contra","Do you confirm surrender against"},
{"te propone tablas. ¿Aceptas?","proposes a draw to you, do you accept?"},
{"modificas puntuación ELO: ganando","changing ELO rating: winning"},
{"perdiendo","losing"},
{"tablas","draw"},
{"puntos","points"},
{"Conexión establecida.","Connection established."},
{"Una idea de","One idea of"},
{"Posible gracias a","Posible thanks to"},
{"Jugador","Player"},
{"Nueva Clase","New Class"},
{"Fondo","Background"},
{"Frente","Foreground"},
{"Aleatorio","Random"},
{"Configuración de usuario","User Settings"},
{"Enviar correo al administrador","Send e-mail to the administrator"},
{"Enviar correo a un amigo","Send e-mail to a friend"},
{"Lista ELO","ELO list"},
{"TABLAS por falta de material.","DRAW for lack of material."},
{"Tablas, se han hecho tres movimientos iguales.","Draw, three moves equal."},
{"Histórico","History"},
{"En blanco muestra todos","Empty shows everybody"},
{"Más datos","More data"},
{"INVITADO","GUEST"}
};


public Idioma(String idiomaUsado)
{
  if(idiomaUsado.equals("spanish"))
    this.idiomaUsado=0;
  else if (idiomaUsado.equals("english"))
    this.idiomaUsado=1;
}


public String traducir(String frase)
{
  int i;

  if(idiomaUsado==1) //se é español xa non entra aquí e devolve o mesmo
  {
    for(i=0;i<frases.length;i++)
      if(frase.equals(frases[i][0]))
        return frases[i][1];
  }
  return frase;  
}


}