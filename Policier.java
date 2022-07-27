public class Policier extends Humain {

          public static final String YELLOW = "\u001B[33m";
          public static final String BLUE = "\u001B[34m";
          public static final String DEFAULT = "\u001B[0m";

          // Attributs
          public static int cptP = 0;
          private int id = 0;
          private int braqueurs_tues = 0;
          private int otages_sauves = 0;

          // Constructeurs

          private Policier(int posX, int posY)                                  // on interdit l'instanciation des policiers avec arguments
          {
            super(posX,posY);
            this.id = cptP+1;
            cptP++;
          }

          public Policier()                                                     // Chaque policier doit obligatoirement passer par l'entrée de notre terrain ici l'extremité haute et gauche [0][0]
          {
            this(0,0);
          }

          // Accesseurs

          public int getId(){ return this.id; }
          public int getBraqueursTues(){ return this.braqueurs_tues; }
          public int getOtagesSauves(){ return this.otages_sauves; }

          // Setteurs
          public void augmenteBraqueursTues()
          {
            this.braqueurs_tues++;
          }

          // Méthodes
          public Policier clone()
          {
              return new Policier(this.posX, this.posY);
          }

          public void sauver( Otage lo[] , Terrain t){
            if( this.posX == (TestSimulation.MAX_X)-1 && this.posY == (TestSimulation.MAX_Y)-1)  // Si le policier est à la bonne position de sauvetage alors..
            {

            for (int i = 0 ; i < lo.length ; i++ )                                         // on parcourt le tableau d'otages passé en arguments
                {
                  if( lo[i] != null )                                                      // si il existe une instance dans l'index actuel on libere UNIQUEMENT cet otage
                  {
                      Otage.cptO--;                                                         // on decremente de un la variable static du representant le nombre d'otage
                      t.getCase(this.posX,this.posY).setQuantite( t.getCase(this.posX,this.posY).getQuantite()-1);// on decremente également sur le terrain
                      System.out.println(BLUE+"L'otage n°"+ lo[i].getId() +" a été liberé !");
                      lo[i]=null;
                      this.otages_sauves++;                                                 // on incrémente de un le score de sauvetage du policier
                      System.out.println(YELLOW+"Incroyable ! le policier n°"+this.id+" a déja sauvé : "+this.otages_sauves+" otages.\n"+DEFAULT);
                      this.seDeplacer(0,0);   // le policier se déplace aléatoirement suite au sauvetage
                      return;                                                               // si un otage a été sauvé on sort de la fonction
                  }


                }
              }
          }

          public String toString()
          {
          return "Policier n°"+this.id+" qui a pour "+super.toString();
          }


}
