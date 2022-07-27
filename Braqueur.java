public class Braqueur extends Humain{

  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String DEFAULT = "\u001B[0m";

  // attributs
  private int possession = 0;                                                   // possessions du braqueur
  public static int cptB = 0;                                                   // compteur du nombre d'instances
  private int id;                                                               // id de l'instance
  private static int butin = 0;
  private int policierstues = 0;


  // Constructeurs

  private Braqueur(int posX , int posY)                                         // On interdit l'instanciation avec arguments afin de favoriser le caractere aléatoire
  {
    super(posX, posY);
    this.id = cptB+1;
    cptB++;
  }

  public Braqueur()                                                             // Constructeur avec positionnement aléatoire
  {
    this( (int)( Math.random()*(TestSimulation.MAX_X-1) ) , (int)( Math.random()*(TestSimulation.MAX_Y-1 ) ) );
  }

  public Braqueur clone()
  {
      return new Braqueur(this.posX, this.posY);
  }

  // Accesseurs

  public int getPossession(){ return this.possession; }

  public int getId(){ return this.id; }

  public int getPolicierstues(){ return this.policierstues; }

  public static int getButin(){ return butin; }

  // Méthodes

  public void seDeplacer()
  {
    super.seDeplacer();
    while ( this.getX() == (TestSimulation.MAX_X)-1 && this.getY() == (TestSimulation.MAX_Y)-1 ) // On interdit le positionnement des braqueurs là où se trouve les otages
      {
          seDeplacer();
      }
  }

  public void recolte(Terrain t)
  {

    if( t.caseEstVide(this.posX, this.posY) )
    {                                 // si la case ou se trouve le braqueur est vide on sort de la fonction
      return;
    }

    else
      {
        int value = t.getCase(this.posX,this.posY).getQuantite();
        System.out.println(value);

        this.possession += value ;                                              // la possession du braqueur augmente de la valeur de la CASE
        butin+= value ;                                                         // le butin des voleur également
        System.out.println( RED+"Le braqueur n°"+ this.id+ " a ramasse "+value+GREEN+"$."+DEFAULT );
        t.videCase(this.posX,this.posY);                                        // on vide la case de ses ressources
        Simulation.reloadSommeTot( value );                                     // Mettre a jour l'attribut static somme représentant la somme restante dans la banque
        System.out.println( "BUTIN TOTAL : " + butin + "$.\n" );                // on affiche le butin total réunis par les voleurs
        return;                                                                 // on sort de la fonction
      }
    }

  public void augmentePolicierstues()
  {
      this.policierstues++;
  }

  public String toString()
  {
    return "Braqueur n°"+this.id+" qui a pour "+super.toString()+" et qui a en sa possession "+this.possession+"$\n";
  }



}
