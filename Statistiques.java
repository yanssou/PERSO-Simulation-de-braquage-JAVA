public class Statistiques {

  public static final String DEFAULT = "\u001B[0m";
  public static final String BLACK = "\u001B[30m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String WHITE = "\u001B[37m";

  public static final int TWAIT_INIT = 1000;
  public static final int TWAIT_MSG = 1000;
  public static final int TWAIT_TRANSIT= 3000;
  public static final int TWAIT_UPDATE = 4000;


    // Constructeur

    private Statistiques(){}                                                    // On interdit l'instanciation


    //Methodes

    private static void MVP_policier()
    {
        int ID = 0;                                                             //On stock l'Id du policier qui aura effectué le meilleur score
        int plus_grand_tues = 0;                                                //Variable dans laquelle on stockera le plus grand nombre de braqueurs tués le meilleur policier
        int plus_grand_sauves = 0;                                              //et enfin le nombre d'otages qu'il a sauvés
        int score = 0;                                                          //on initialise le score qui equivaut au nombre de braqueurs tués additionne au nombre d'otages sauvés ( le score )

        for(int i = 0; i < Simulation.get_tab_Policier().length ; i++)          //on parcourt la liste des policiers
        {
          if( Simulation.get_tab_Policier()[i] != null )
          {
            if( (Simulation.get_tab_Policier()[i].getBraqueursTues() + Simulation.get_tab_Policier()[i].getOtagesSauves() ) >= score);         //si le policier existe et que son score est meilleur que le precedent score sauvegardé
            {
                ID = Simulation.get_tab_Policier()[i].getId();                                                     //son ID
                plus_grand_sauves  = Simulation.get_tab_Policier()[i].getOtagesSauves();                           //et le nombre d'otages qu'il a sauvé
                plus_grand_tues = Simulation.get_tab_Policier()[i].getBraqueursTues();                             //On garde son nombre de braqueur tué
                score = plus_grand_tues + plus_grand_sauves;
            }
          }
        }
        if( plus_grand_tues == 0 && plus_grand_sauves == 0) { return; }
        System.out.println(WHITE+"Le "+BLUE+"policier n°"+ID+DEFAULT+" est le meilleur "+BLUE+"policier"+WHITE+" encore en vie de cette simulation avec :\n-"+RED+plus_grand_tues+" braqueurs"+DEFAULT+" tués.\n-"+plus_grand_sauves+PURPLE+" otages "+DEFAULT+"sauvés.\n\nImpressisonnant !\n");
    }

    private static void MVP_braqueur()
    {
        int ID = 0;
        int plus_grand_tues = 0;
        int plus_grand_butin = 0;
        int score = 0;
        for(int i = 0; i < Simulation.get_tab_Braqueur().length ; i++)
        {
          if( Simulation.get_tab_Braqueur()[i] != null )
          {
            if ( (Simulation.get_tab_Braqueur()[i].getPossession() + Simulation.get_tab_Braqueur()[i].getPolicierstues() ) >= score  )
              {
                ID = Simulation.get_tab_Braqueur()[i].getId();
                plus_grand_tues = Simulation.get_tab_Braqueur()[i].getPolicierstues();
                plus_grand_butin  = Simulation.get_tab_Braqueur()[i].getPossession();
                score = plus_grand_tues+plus_grand_butin;
              }
          }
        }
        if( plus_grand_tues == 0 && plus_grand_butin == 0){return;}
        System.out.println(WHITE+"Le"+RED+" braqueur n°"+ID+DEFAULT+" est le meilleur "+RED+"braqueur"+WHITE+" encore en vie de cette simulation avec :\n-"+GREEN+plus_grand_butin+"$"+DEFAULT+" volés.\n-"+plus_grand_tues+BLUE+" policiers "+DEFAULT+"tués.\n\nSurprenant !\n");
    }

    public static void infos()
    {
        System.out.println(WHITE+"                                  La simulation a duré : "+Simulation.cptUpdate+" actualisations                                \n"+DEFAULT);
        Sleep.sleep(TWAIT_MSG);
        System.out.println(YELLOW+"\n-------------------------------------------TABLEAU DES SCORES---------------------------------------------\n"+DEFAULT);
        MVP_braqueur();
        System.out.println(WHITE+"----------------------------------------------------------------------------------------------------------\n"+DEFAULT);
        MVP_policier();
        System.out.println(YELLOW+"----------------------------------------------------------------------------------------------------------\n"+DEFAULT);
        System.out.println("\n\n                                            "+RED+"L"+WHITE+"A "+RED+"C"+WHITE+"A"+RED+"S"+WHITE+"A "+RED+"D"+WHITE+"E "+RED+"P"+WHITE+"I"+RED+"X"+WHITE+"E"+RED+"L"+DEFAULT+"\n\n");
    }

}
