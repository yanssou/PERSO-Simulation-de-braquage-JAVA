public class Simulation {

    private final int TWAIT = 500 ;
    public static int cptUpdate = 0 ;
    public static final String DEFAULT = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final double DIST_HELP = 2 ;

// Attributs

    private static Braqueur[] braqueurs;        // tableau contenant les braqueurs
    private static Otage[] otages;              // tableau contenant les otages
    private static Policier[] policiers;        // tableau contenant les policiers
    private static int sommeTot;
    private static int nb_total;
    private static Terrain terrain;


// Constructeurs

    public Simulation(int nb_otage, int nb_policier, int nb_braqueur, Terrain t, int somme)
    {
      sommeTot = somme;
      terrain = t;

      braqueurs = new Braqueur[nb_braqueur];

      otages = new Otage[nb_otage];

      policiers = new Policier[nb_policier];

      nb_total = nb_otage+nb_policier+nb_braqueur ;

        for (int i = 0; i< otages.length ; i++ ) otages[i]= new Otage();

        for (int i = 0; i< policiers.length ; i++ ) policiers[i]= new Policier();

        for (int i = 0; i< braqueurs.length ; i++ ) braqueurs[i]= new Braqueur();
      }
    private Simulation(){}
                                                    // On interdit l'instanciation sans arguments
// Accesseurs

    public static Braqueur[] get_tab_Braqueur(){ return braqueurs;}
    public static Otage[] get_tab_Otage(){ return otages;}
    public static Policier[] get_tab_Policier(){ return policiers;}
    public static int getSommeTot(){ return sommeTot; }

// Methodes

  public static void reloadSommeTot(int value)
  {
    sommeTot-=value;
  }

//---------------------------

  public void refresh()
  {
      Policier po = new Policier();
      Policier.cptP--;

      for( int p = 0 ; p < policiers.length ; p++)
      {
        if( policiers[p] == null )
        {
          policiers[p] = po.clone();
        }
      }
  }

//---------------------------

  public void fusillade()
  {

        for(int p=0; p< policiers.length ; p++)
        {                                                                       // On va parcourir le tableau de policiers
          int nb_PoliciersSurCase=1;                                            // On crée deux variables pour stocker le nombre de policiers et de braqueurs présents sur la case du policier actuel
          int nb_BraqueursSurCase=0;

          if( policiers[p] != null)
          {
        int x_pbase = policiers[p].getX();                                      // variables pour stocker la position du policier actuel
        int y_pbase = policiers[p].getY();

        for(int br=0 ; br<braqueurs.length ; br++)
        {

        }


        for(int p1=0; p1< policiers.length ; p1++)                              // on parcourt à nouveau la liste des policiers pour compter le nombre de policier ayant la même position
        {
              if(policiers[p1] != null)
              {
                if( x_pbase == policiers[p1].getX() &&  y_pbase == policiers[p1].getY() ) // Des qu'un policier ayant la même position est trouvé on incremente la variable de stockage
                 {
              nb_PoliciersSurCase++;
                  }
              }
        }
        if(nb_PoliciersSurCase > 1) {  nb_PoliciersSurCase--; }                 // la variable de stockage du nbr de policier étant définit à 1 il faut la décrementer car la liste est parcouru une deuxieme fois et un policier est cosideré deux fois

        for(int b=0; b<braqueurs.length ; b++)                                  // On parcourt le tableau des braqueurs
        {
                  if(braqueurs[b] != null)
                  {
                      if( braqueurs[b].getX() == x_pbase &&  braqueurs[b].getY() == y_pbase )
                      {
                        nb_BraqueursSurCase++;                                            // dès qu'un braqueur a la même position que le policier courant on incremente la variable de stockage
                      }
                    }
         }

        if(nb_BraqueursSurCase > 0)
        {

          if(nb_BraqueursSurCase> nb_PoliciersSurCase)                                         // cas 1 nbr policiers < nbr braqueurs : les policiers sont éliminés
              {
                System.out.print(WHITE+"Une confrontation a lieu à la position ("+ x_pbase +","+ y_pbase +") !!!!    \n");
                System.out.print("les "+BLUE+"policiers"+WHITE+" sont au nombre de : "+nb_PoliciersSurCase+"\nLes "+RED+"braqueurs"+WHITE+" sont au nombre de : "+nb_BraqueursSurCase+" \n");
                System.out.println(RED+"Les policiers sont en infériorité numérique !!!\n");
                        for(int po=0 ; po< policiers.length ; po++)                       // On parcourt la liste des policiers pour recuperer ceux ayant la même position
                            {
                                  if( policiers[po] != null )
                                  {
                                    if( policiers[po].getX() == x_pbase && policiers[po].getY() == y_pbase ) // on parcourt la liste des policiers pour supprimer tous les policiers présent sur la case
                                      {
                                        System.out.println(RED+"BAM !! \n "+"-Le policier n°"+ policiers[po].getId() + " est abbatu."+DEFAULT);
                                        policiers[p]=null;
                                        Policier.cptP--;                                          // On decrémente les compteurs
                                        nb_total--;
                                        for (int i=0; i < braqueurs.length ; i++ )
                                          {
                                            if( braqueurs[i] != null )
                                            {
                                            if( braqueurs[i] != null && braqueurs[i].getX() == x_pbase && braqueurs[i].getY() == y_pbase )
                                                {
                                                    braqueurs[i].augmentePolicierstues();
                                                }
                                            }
                                          }


                                      }
                                }
                          }

                  }


          if(nb_PoliciersSurCase > nb_BraqueursSurCase )                        // cas 2 : nbr  policier > nbr braqueur sur la position courante , les policiers ont l'avantage
          {

            System.out.println(WHITE+"-----------------------------------------------FUSILLADE--------------------------------------------------\n");

                System.out.print("Une confrontation a lieu à la position ("+ x_pbase +","+ y_pbase +") !!!!    \n");
                System.out.print("les "+BLUE+"policiers"+WHITE+" sont au nombre de : "+nb_PoliciersSurCase+"\nLes "+RED+"braqueurs"+WHITE+" sont au nombre de : "+nb_BraqueursSurCase+" \n");
                System.out.println(BLUE+"\nLes policiers ont un avantage !!!\n");

            for(int b=0 ; b<braqueurs.length ; b++)                             // On parcourt la liste des braqueurs étant dans la même position que les policiers pour les mettre à null
            {
                      if( braqueurs[b] != null ) {


            if( braqueurs[b].getX() == x_pbase && braqueurs[b].getY() == y_pbase )    // des qu'un braqueur dans la liste a la même position on le met à null
            {
                    System.out.println("BOUM !! \n"+"-Le braqueur n°"+ braqueurs[b].getId() + " est abbatu.\n");
                    braqueurs[b]=null;

                    for (int i=0; i < policiers.length ; i++ )
                    {
                        if( policiers[i] != null && policiers[i].getX() == x_pbase && policiers[i].getY() == y_pbase )
                        {
                            policiers[i].augmenteBraqueursTues();
                        }
                    }

                    System.out.println(YELLOW+"Incroyable ! le policier n°"+policiers[p].getId()+" a déja abbatu: "+policiers[p].getBraqueursTues()+" braqueurs.\n"+DEFAULT);
                    Braqueur.cptB--;                                            // on decrémente les deux compteurs static , propre aux braqueurs et total
                    nb_total--;
            }
                                              }
          }

                                                            }

          if(nb_PoliciersSurCase == nb_BraqueursSurCase ) {                     // cas 3 : nbr policier < nbr braqueur les brauqueurs ont l'avantage


                    System.out.println(WHITE+"-----------------------------------------------FUSILLADE--------------------------------------------------\n");

                    System.out.print("Une confrontation a lieu à la position ("+ x_pbase +","+ y_pbase+") !!!! \n");
                    System.out.print("les "+BLUE+"policiers"+WHITE+" sont au nombre de : "+nb_PoliciersSurCase+"\nLes "+RED+"braqueurs"+WHITE+" sont au nombre de : "+nb_BraqueursSurCase+" \n");
                    System.out.println(RED+"\nIl y a autant de policiers que de braqueurs !!!\n");

                    System.out.println(YELLOW+"Les policiers n'ont pas l'avantages ! ils demandent de l'aide.\n"+DEFAULT);

                    for (int x=0; x< policiers.length ; x++ )
                    {
                      if( policiers[x] != null )
                        {

                        if( policiers[x].getX() != x_pbase && policiers[x].getY() != y_pbase && policiers[x].distance(policiers[p].getX(),policiers[p].getY() ) <= DIST_HELP  )        // si la distance le permet les policiers autours viennent aider
                            {
                                nb_PoliciersSurCase++;
                              }
                         }
                    }

                  // si ils n'ont pas pu avoir de l'aide on fait bouger toutes les persones se trouvant sur la position
                    if( nb_PoliciersSurCase == nb_BraqueursSurCase)
                    {
                      System.out.println(BLUE+"\nLes policiers"+RED+" n'ont pas pu avoir d'aide..\n\n"+RED+"TOUT LE MONDE SE DÉPLACE !!"+DEFAULT);
                            // Cas braqueurs déplacement
                            for(int braq = 0 ;  braq < braqueurs.length ; braq ++)
                              {
                                  if( braqueurs[braq] != null )
                                    {
                                      if( braqueurs[braq].getX() == x_pbase && braqueurs[braq].getY() == y_pbase  )
                                        {
                                          braqueurs[braq].seDeplacer();
                                        }
                                      }
                                }

                            // Cas policiers déplacement
                            for(int poli = 0 ; poli < policiers.length ; poli ++)
                            {
                              if(policiers[poli] != null)
                              {
                                if( policiers[poli].getX() == x_pbase && policiers[poli].getY() == y_pbase  )
                                  {
                                    policiers[poli].seDeplacer();
                                  }
                                }
                              }
                            }


                    if( nb_PoliciersSurCase > nb_BraqueursSurCase )    // s'ils ont pu avoir de l'aide
                    {
                        System.out.println(YELLOW+"SUPER !!! les "+BLUE+"policiers"+YELLOW+" ont pu avoir de l'aide et sont maintenant au nombre de : "+nb_PoliciersSurCase+DEFAULT+"\n");
                            for(int b=0 ; b<braqueurs.length ; b++)                               // On parcourt la liste des braqueurs étant dans la même position que les policiers pour les mettre à null
                            {
                                    if( braqueurs[b] != null )
                                    {
                                      if( braqueurs[b].getX() == x_pbase && braqueurs[b].getY() == y_pbase ) // des qu'un braqueur dans la liste a la même position on le met à null
                                        {
                                          System.out.println(BLUE+"BOUM !! \n"+"-Le braqueur n°"+ braqueurs[b].getId() + " est abbatu.\n"+DEFAULT);
                                          braqueurs[b]=null;
                                          Braqueur.cptB--;                      // on decrémente les deux compteurs static , propre aux braqueurs et total
                                          nb_total--;

                                          for (int i=0; i < policiers.length ; i++ )
                                          {
                                            if( policiers[i] != null)
                                            {
                                            if( policiers[i].getX() == x_pbase && policiers[i].getY() == y_pbase )
                                              {
                                                policiers[i].augmenteBraqueursTues();
                                              }
                                            }
                                          }

                                          System.out.println(YELLOW+"Incroyable ! le policier n°"+policiers[p].getId()+" a déja abbatu: "+policiers[p].getBraqueursTues()+" braqueurs.\n"+DEFAULT);

                                        }
                              }
                            }
                      }
                    }
                          }


          }
        }

  }


//---------------------------

  public String toString()
  {
    return WHITE+"Il y a actuellement : "+this.nb_total+" personnes dont \n"+RED+"-Braqueurs : "+Braqueur.cptB+BLUE+"\n-Policiers : "+Policier.cptP+PURPLE+"\n-Otages : "+Otage.cptO+WHITE+"\nIl reste "+GREEN+this.getSommeTot()+"$"+WHITE+" dans la banque.\n";
  }

//---------------------------

  public void update(Terrain t){

System.out.println(CYAN+"-------------------------------------------------UPDATE---------------------------------------------------\n"+DEFAULT);

        cptUpdate++;

        // conditions d'arrets et sortie de la simulation

        // plus aucun otage
        if( Otage.cptO == 0 ){
          t.videCase((TestSimulation.MAX_X)-1, (TestSimulation.MAX_Y)-1); 
          System.out.println(DEFAULT+"                                              ! ! STOP ! !                                                \n");
          Sleep.sleep(TWAIT);
          System.out.println(WHITE+"                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n"+DEFAULT);
          Sleep.sleep(TWAIT);
          System.out.println("                                        IL N'Y PLUS AUCUN OTAGE....                                       \n");
          Sleep.sleep(TWAIT);
          System.out.println(BLUE+"                            LE FBI PEUT DONC INTERVENIR ET ENGAGER UN ASSAUT                              \n"+WHITE);
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println(YELLOW+"                            TOUS LES BRAQUEURS RESTANTS ONT ÉTÉ NEUTRALISÉS                               \n"+DEFAULT);
          Sleep.sleep(TWAIT);

          Statistiques.infos();

          System.out.println(BLUE+"                                        LA PARTIE EST TERMINÉE                                            \n"+DEFAULT);
          System.out.println(YELLOW+"                                                 THE END                                                  \n");
          System.out.println(WHITE+"\n----------------------------------------------------------------------------------------------------------\n"+DEFAULT);
          t.affiche();
          System.exit(1);

        }

        //--------------------------

        // plus aucun braqueur
        if( Braqueur.cptB == 0 ){

          System.out.println(DEFAULT+"                                              ! ! STOP ! !                                                \n");
          Sleep.sleep(TWAIT);
          System.out.println(WHITE+"                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println(BLUE+"                                    LES BRAQUEURS ONT TOUS ÉTÉ ÉLIMINÉS                                   \n"+WHITE);
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println(BLUE+"                                        L'ARGENT A PU ÊTRE RÉCUPÉRÉ                                       \n");
          Sleep.sleep(TWAIT);
          System.out.println(YELLOW+"                                            RETOUR À LA NORMALE                                           \n"+DEFAULT);
          Sleep.sleep(TWAIT);


          Statistiques.infos();

          System.out.println(BLUE+"                                         LA PARTIE EST TERMINÉE                                           \n "+DEFAULT);
          System.out.println(YELLOW+"                                                 THE END                                                  \n ");
          System.out.println(WHITE+"\n----------------------------------------------------------------------------------------------------------\n"+DEFAULT);
          t.affiche();
          System.exit(1);

        }

        //------------------------

        // plus d'argent dans la banque
        if( sommeTot <= 0 ){
          System.out.println(DEFAULT+"                                              ! ! STOP ! !                                                \n");
          Sleep.sleep(TWAIT);
          System.out.println(WHITE+"                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println(RED+"                       ON N'ENTEND PLUS AUCUNE BRUIT VENANT DE L'INTERIEUR DE LA BANQUE                   \n"+WHITE);
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                              .   .   .   .                                               \n"+RED);
          Sleep.sleep(TWAIT);
          System.out.println("                                        TOUT L'ARGENT A ÉTÉ DÉROBÉ                                        \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                 LES BRAQUEURS ONT TOUS PRIT LA FUITE !!                                  \n");
          Sleep.sleep(TWAIT);
          System.out.println("                                       QUE VA DEVENIR L'ÉCONOMIE !!!                                      \n"+DEFAULT);
          Sleep.sleep(TWAIT);

          Statistiques.infos();

          System.out.println("                                         LA PARTIE EST TERMINÉE                                           \n"+WHITE);
          System.out.println(YELLOW+"                                                 THE END                                                  \n");
          System.out.println(WHITE+"\n----------------------------------------------------------------------------------------------------------\n"+DEFAULT);
          t.affiche();
          System.exit(2);

        }

        // policier à zéro pas une condition de sortie
        if( Policier.cptP <= 0 ){
          System.out.println(BLUE+"--------------------------------------------------STOP----------------------------------------------------\n");
          Sleep.sleep(TWAIT);
          System.out.println("Il ne reste plus aucun policier ! mais la justice ne s'avoue jamais vaincue.\n");
          Sleep.sleep(TWAIT);
          System.out.println(YELLOW+"Une nouvelle troupe vient de débarquer et compte bien en finir avec ces malfrats\n"+DEFAULT);
          Sleep.sleep(TWAIT);
          this.refresh();
          System.out.println(this.toString());
          System.out.println(BLUE+"----------------------------------------------------------------------------------------------------------\n"+DEFAULT);
        }


        //Si tout se passe bien et qu'une condition d'arret ne s'est pas éxécutée

        System.out.println(GREEN+"--------------------------------------------------VOL-----------------------------------------------------\n");
        // update les braqueurs
        for (int i = 0; i < braqueurs.length ; i++)                             // parcourt liste braqueurs
        {
                if( braqueurs[i] != null )                                      // verification
                {

                  while( braqueurs[i].getX() == TestSimulation.MAX_X && braqueurs[i].getY() == TestSimulation.MAX_Y )  // On interdit le positionnement des braqueurs là où se trouve les otages
                  {
                    braqueurs[i].seDeplacer();
                  }

                  braqueurs[i].recolte(terrain);

                  if(sommeTot==0){                                              // déclanche condition d'arrêt de la simulation
                    update(t);
                  }

                  braqueurs[i].seDeplacer();                                    //pour chaque braqueur, on le fais se deplacer aleatoirement et ramasser de l'argent si il se trouve sur une case qui en contient
                }

        }
        System.out.println("----------------------------------------------------------------------------------------------------------\n"+DEFAULT);



        // ----------------------------

        System.out.println(YELLOW+"-----------------------------------------------SAUVETAGES-------------------------------------------------\n");
        // update policiers
        for (int j = 0; j < policiers.length; j++)                              //on parcourt la liste des policiers
        {
              if( policiers[j] != null ){                                       //pour chaque policier, on le fais se deplacer aleatoirement et sauver un otage si il se trouve sur la meme case que ces derniers
                  policiers[j].seDeplacer();
                  policiers[j].sauver(otages,t);
                        }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------\n"+DEFAULT);

        this.fusillade();                                                       // Une fois les deux camps déplacés on déclanche une fusillade


          System.out.println(CYAN+"----------------------------------------------------------------------------------------------------------\n"+DEFAULT);

          t.affiche();


          return;
    }


}
