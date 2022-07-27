abstract class Humain{                                                          // La classe est Abstraite

  // attributs
   protected int posX; // position x
   protected int posY; // position y



  // Constructeurs
  protected Humain(int posX , int posY )                                        // constructeur protected pour ne pas pouvoir instancier d'humain hors classe herité
  {
        this.posX = posX;
        this.posY = posY;
  }

  private Humain(){}                                                            // on interdit l'intanciation sans arguments

  // Accesseurs
  public int getX(){ return this.posX;}
  public int getY(){ return this.posY;}

  // Methodes

  protected int distance(int x, int y)
  {
    return (int) Math.sqrt( Math.pow(x-this.posX,2) + Math.pow(y-this.posY,2) );
  }

  public void seDeplacer(int newX , int newY)
  {
    this.posX = newX;
    this.posY = newY;
  }

  public void seDeplacer()                                                      // effectuer un Deplacement Aleatoire
  {
    this.seDeplacer( (int)(Math.random()*(TestSimulation.MAX_X)), (int)(Math.random()*(TestSimulation.MAX_Y)) );
  }

  public String toString()
  {
    return "position : ("+this.posX+","+ this.posY+")";
  }


}
