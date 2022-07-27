import java.util.Scanner;
public class TestSimulation{

  public static final String DEFAULT = "\u001B[0m";
  public static final String BLACK = "\u001B[30m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String WHITE = "\u001B[37m";
  public static final int MAX_X= 5;
  public static final int MAX_Y= 10;
  public static final int TWAIT_INIT = 1000;
  public static final int TWAIT_MSG = 1000;
  public static final int TWAIT_TRANSIT= 3000;
  public static final int TWAIT_UPDATE = 3000;
	static final int LIASSE = 1000;                                              // valeur d'une ressource
	static final String DOLLAR = "  $";





  //----------------------------------------------------------------------------


	public static void main(String[] args) {

  //----------------------------------------------------------------------------

        // variables
        int nb_policier = 0;
        int nb_otage = 0;
        int nb_braqueur = 0;
        Scanner scan = new Scanner(System.in);
        int TOURS;

//----------------------------------------------------------------------------
        // intro
        Setup.INTRO();


//----------------------------------------------------------------------------

        // on instancie un terrain
        Terrain t = new Terrain(MAX_X,MAX_Y);

        // on crée la ressource spéciale representant  les otages uniquement à visée esthétique que l'on place à l'extrémite droite et basse du terrain
        Ressource otages = new Ressource(("('-')"),0);
        t.setCase(MAX_X-1,MAX_Y-1,otages);


        //----------------------------------------------------------------------

        // On demandera à l'utilisateur de saisir la somme totale d'argent disponible dans la banque en prenant en compte une limite interne

        System.out.println(WHITE+"Le terrain a une taille de : "+RED+MAX_X+WHITE+" x "+RED+MAX_Y+DEFAULT);

        System.out.print(WHITE+"\nVeuillez saisir la somme d'argent qui sera disponible dans la banque : "+DEFAULT);

        final int TOTAL=Setup.init_Somme( (MAX_X*MAX_Y)-1 ); // La somme totale d'argent dans la banque est fixée

        Sleep.sleep(TWAIT_INIT);

        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");
        System.out.println(YELLOW+"\nPARFAIT"+WHITE+" ! la banque disposera donc d'un total de : "+GREEN+TOTAL+"$"+WHITE+".\n");

        Sleep.sleep(TWAIT_INIT);

        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");


        //----------------------------------------------------------------------

        // Remplissage du tableau
        int compteur = 0;

        while(compteur != TOTAL )
        {
            // on determine des coordonées aléatoire pour chaque ressource
              int x=(int)(Math.random()*(MAX_X)) ;
              int y=(int)(Math.random()*(MAX_Y)) ;
            // Création d'une liasse de Billet de valeur LIASSE
            if( TOTAL - compteur < LIASSE)                                      // Si la somme totale n'est pas atteinte mais qu'il faut moins d'une LIASSE pour l'atteidire
            {
              Ressource ressources = new Ressource(DOLLAR,TOTAL-LIASSE);        // on crée une ressource avec la difference manquante qu'on va placer

              if ( ! t.caseEstVide(x,y) )                                       // position déja utilisé
              {
                  t.getCase(x,y).setQuantite( t.getCase(x,y).getQuantite() + LIASSE );      // additions ressources
              }

              else{
                 t.setCase(x,y,ressources);                                     // placement position vide
                  }
            }

            Ressource ressources = new Ressource(DOLLAR,LIASSE);


            while( x == (MAX_X-1) && y == (MAX_Y-1) )                           // Afin d'éviter que de l'argent soit disponible sur l'endroit reservé au otages
            {
              x=(int)(Math.random()*(MAX_X)) ;                                  // tant que c'est le cas on tire de nouvelles coordonnées
              y=(int)(Math.random()*(MAX_Y)) ;
            }


            if ( ! t.caseEstVide(x,y) )                                         // si une ressource se trouve déja sur la positiion d'une autre on les sommes
            {
                t.getCase(x,y).setQuantite( t.getCase(x,y).getQuantite() + LIASSE );
                compteur+=LIASSE;
            }

            else{
               t.setCase(x,y,ressources);
               compteur+=LIASSE;
                }

        }

        if( compteur != TOTAL)
        {
          System.out.println("Probleme de somme veuillez relancez le programme s'il vous plaît ");      // Sécurité supplémentaire
          System.exit(1);
        }





        System.out.println(WHITE+"\nLes "+GREEN+TOTAL+"$"+WHITE+" ont été disposés dans la banque\n"+DEFAULT);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");

        // ---------------------------------------------------------------------

        // Choix du nombre de protagoniste de la simulation

        // nb Otages
        Setup.OTAGE();
        nb_otage = Setup.init_otages();
        Sleep.sleep(TWAIT_MSG);
        System.out.println(DEFAULT+"\n-----------------------------------------------------------------------------------------------------------\n");
        System.out.println(YELLOW+"\nPARFAIT !\n"+DEFAULT);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");
        Sleep.sleep(TWAIT_MSG);

        // nb Braqueurs
        Setup.BRAQUEUR();
        nb_braqueur = Setup.init_braqueur();
        Sleep.sleep(TWAIT_MSG);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");
        System.out.println(YELLOW+"\nPARFAIT !\n"+DEFAULT);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");
        Sleep.sleep(TWAIT_MSG);

        // nb Policiers
        Setup.POLICIER();
        nb_policier=Setup.init_policier(nb_braqueur);
        Sleep.sleep(TWAIT_MSG);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");
        System.out.println(YELLOW+"\nPARFAIT !\n"+DEFAULT);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");
        Sleep.sleep(TWAIT_MSG);

        //----------------------------------------------------------------------

        // nb Itérations


        TOURS = Setup.init_Iteration();
        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");
        System.out.println(YELLOW+"\nPARFAIT !\n"+DEFAULT);
        System.out.println("\n-----------------------------------------------------------------------------------------------------------\n");
        Sleep.sleep(TWAIT_MSG);

        // ---------------------------------------------------------------------

        // on instancie une simulation
        Simulation s = new Simulation(nb_otage,nb_policier,nb_braqueur,t,TOTAL);

        // information loading
        Setup.INIT_TERRAIN();

        // on affiche le terrain
        System.out.println("Informations sur le terrain:"+t+"\n"+DEFAULT);
        Sleep.sleep(TWAIT_MSG);
        t.affiche();
        Sleep.sleep(TWAIT_UPDATE);


        System.out.println(WHITE+"\n-----------------------------------------ETAT-DEBUT-SIMULATION--------------------------------------------\n\n"+DEFAULT);

        // information de départ de la simulation
        System.out.println(s.toString()+"\n");

        // lancement
        for(int i = 0; i< TOURS; i++ )
        {
          System.out.println("-----------------------------------------------Itération n°"+(i+1)+"----------------------------------------------\n");
          s.update(t);
          System.out.println(s.toString());
          Sleep.sleep(TWAIT_UPDATE);
        }

  }


}
